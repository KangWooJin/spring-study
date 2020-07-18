package kangwoojin.github.io.querydsl.eventlistener;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class PersonEntityListener {
    @Autowired
    private PersonService personService;
    @Autowired
    private ApplicationContext applicationContext;

    public PersonEntityListener() {
        System.out.println("PersonEntityListener Constructor");
    }

    @PrePersist
    public void prePersist(Person person) {
        System.out.println("prePersist : " + personService);
        System.out.println("pre : " + applicationContext);

//        Optional<Person> person1 = personRepository.findById(1L);
        person.setCreatedTime(LocalDateTime.now());
    }
    @PostConstruct
    public void init() {
        System.out.println("1234");
    }
}
