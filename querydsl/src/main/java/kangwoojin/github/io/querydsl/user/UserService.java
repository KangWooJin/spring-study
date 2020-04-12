package kangwoojin.github.io.querydsl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kangwoojin.github.io.querydsl.user.UserService.SearchCriteria.SearchCriteriaBuilder;
import lombok.Builder;
import lombok.Getter;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByName(String name) {
        SearchCriteriaBuilder builder = SearchCriteria.builder();

        SearchCriteria searchCriteria = builder.name(name).build();

        return userRepository.findByName(searchCriteria.getName());

    }

    @Builder
    @Getter
    public static class SearchCriteria {
        private String name;
    }
}
