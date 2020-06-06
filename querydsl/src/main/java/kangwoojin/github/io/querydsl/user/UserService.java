package kangwoojin.github.io.querydsl.user;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQuery;

import kangwoojin.github.io.querydsl.user.UserService.SearchCriteria.SearchCriteriaBuilder;
import lombok.Builder;
import lombok.Getter;

@Service
public class UserService {
    private final QUser qUser = QUser.user;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public User findByName(String name) {
        SearchCriteriaBuilder builder = SearchCriteria.builder();

        SearchCriteria searchCriteria = builder.name(name).build();

        return userRepository.findByName(searchCriteria.getName());

    }

    public User findByNameAndId(String name, Long id) {
        JPAQuery<User> query = new JPAQuery<>(entityManager);

        return query.from(qUser)
                    .where(qUser.name.eq(name).and(qUser.id.eq(id)))
                    .fetchOne();
    }

    @Builder
    @Getter
    public static class SearchCriteria {
        private String name;
    }
}
