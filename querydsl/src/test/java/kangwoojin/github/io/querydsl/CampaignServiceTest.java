package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.Event;
import kangwoojin.github.io.querydsl.event.service.CampaignService;
import kangwoojin.github.io.querydsl.event.service.EventService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CampaignServiceTest {
    @Autowired
    private CampaignService campaignService;
    @Autowired
    private EventService eventService;

    @Test
    @Transactional
    void transactionTest() {
        Event event = new Event();
        event.setName("event");
        eventService.save(event);

        Campaign process = campaignService.process(event);

        assertThat(process).isNotNull();
    }
}
