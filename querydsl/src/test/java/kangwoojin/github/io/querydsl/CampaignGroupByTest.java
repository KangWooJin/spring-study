package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.CampaignDto;
import kangwoojin.github.io.querydsl.event.model.Event;
import kangwoojin.github.io.querydsl.event.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;

@DataJpaTest
@TestConstructor(autowireMode = AutowireMode.ALL)
@RequiredArgsConstructor
class CampaignGroupByTest {
    private final CampaignRepository campaignRepository;
    private final TestEntityManager testEntityManager;

    @Test
    void groupByLeftJoinIsEmptyThenAggregationValueIsNullTest() {
        Campaign campaign = new Campaign();
        long campaignAmount = 5L;
        String campaignName = "test";
        campaign.setAmount(campaignAmount);
        campaign.setName(campaignName);
        testEntityManager.persist(campaign);
        testEntityManager.flush();
        testEntityManager.clear();

        List<CampaignDto> actual = campaignRepository.getAmountSum(0L);

        assertThat(actual).isNotEmpty();
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).getCampaignName()).isEqualTo(campaignName);
        assertThat(actual.get(0).getAmountSum()).isEqualTo(campaignAmount);
        assertThat(actual.get(0).getEventAmountSum()).isNull();
    }

    private Event getEvent(String evnet, long amount, Campaign campaign) {
        Event event = new Event();
        event.setName(evnet);
        event.setAmount(amount);
        event.setCampaign(campaign);
        return event;
    }
}
