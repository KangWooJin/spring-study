package kangwoojin.github.io.querydsl.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.Event;
import kangwoojin.github.io.querydsl.event.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CampaignService {
    private final CampaignRepository campaignRepository;

    @Transactional
    public Campaign save_create(Campaign campaign) {
        if (!campaign.getEvents().isEmpty()) {
//            throw new RuntimeException("runtimeException");
        }
        return campaignRepository.save(campaign);
    }

    @Transactional
    public Campaign process(Event event) {
        Campaign campaign = new Campaign();
        campaign.setEvents(List.of(event));
        campaign.setName("campaign");

        return save_create(campaign);
    }
}
