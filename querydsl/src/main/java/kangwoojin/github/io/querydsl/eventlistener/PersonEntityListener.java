package kangwoojin.github.io.querydsl.eventlistener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;

public class PersonEntityListener {
    @Autowired
    private PersonRepository personRepository;

    public PersonEntityListener() {
        System.out.println("PersonEntityListener Constructor");
    }

    @PrePersist
    public void prePersist(Person person) {
        System.out.println("prePersist : " + personRepository);
        person.setCreatedTime(LocalDateTime.now());
    }
}
