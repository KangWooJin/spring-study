package kangwoojin.github.io.querydsl.event.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kangwoojin.github.io.querydsl.event.model.Event;
import kangwoojin.github.io.querydsl.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    @Transactional
    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
