package kangwoojin.github.io.querydsl.eventlistener;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
@EntityListeners(PersonEntityListener.class)
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime createdTime;
}
