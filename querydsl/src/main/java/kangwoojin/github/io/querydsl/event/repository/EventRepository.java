package kangwoojin.github.io.querydsl.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kangwoojin.github.io.querydsl.event.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
