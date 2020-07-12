package kangwoojin.github.io.querydsl.eventlistener;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }
}
