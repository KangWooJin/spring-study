package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kangwoojin.github.io.querydsl.eventlistener.Person;
import kangwoojin.github.io.querydsl.eventlistener.PersonRepository;

//@Transactional
//@AutoConfigureTestEntityManager
@SpringBootTest
class EventListenerTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void prePersistTest() {
        Person person = new Person();
        Person actual = personRepository.save(person);

        assertThat(actual.getCreatedTime()).isNotNull();
    }
}
