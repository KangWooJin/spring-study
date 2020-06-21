package kangwoojin.github.io.querydsl.event.service;

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
    public Campaign save(Campaign campaign) {
        if (campaign.getEvent() != null) {
//            throw new RuntimeException("runtimeException");
        }
        return campaignRepository.save(campaign);
    }

    @Transactional
    public Campaign process(Event event) {
        Campaign campaign = new Campaign();
        campaign.setEvent(event);
        campaign.setName("campaign");

        return save(campaign);
    }
}
