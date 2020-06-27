package kangwoojin.github.io.querydsl.event.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.Event;
import kangwoojin.github.io.querydsl.event.service.CampaignService;
import kangwoojin.github.io.querydsl.event.service.EventService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CampaignController {
    private final CampaignService campaignService;
    private final EventService eventService;

    @PostMapping("/create")
    @Transactional
    public Campaign create(@RequestParam String name) {
        Event event = new Event();
        event.setName(name);
        return campaignService.process(eventService.save(event));
    }

    @PostMapping("/create2")
    @Transactional
    public Campaign create2(@RequestParam List<String> name) {
        name.forEach(names -> {
            names.toString();
        });
        Event event = new Event();
//        event.setName(name);
        return campaignService.process(eventService.save(event));
    }
}
