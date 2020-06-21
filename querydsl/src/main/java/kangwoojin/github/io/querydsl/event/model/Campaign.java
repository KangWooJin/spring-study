package kangwoojin.github.io.querydsl.event.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Campaign {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @NotNull
    @OneToOne
    private Event event;
}
