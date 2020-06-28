package kangwoojin.github.io.querydsl.event.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    @JsonManagedReference
    private Campaign campaign;
}
