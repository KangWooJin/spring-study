package kangwoojin.github.io.querydsl.event.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Campaign {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long amount;

    @OneToMany
    private List<Event> events;
}