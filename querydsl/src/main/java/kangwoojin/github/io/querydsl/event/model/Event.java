package kangwoojin.github.io.querydsl.event.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
}
