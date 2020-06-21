package kangwoojin.github.io.querydsl.event.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
