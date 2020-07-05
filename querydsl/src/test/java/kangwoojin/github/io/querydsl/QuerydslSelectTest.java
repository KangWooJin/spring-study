package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

import com.querydsl.core.Tuple;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.QCampaign;
import kangwoojin.github.io.querydsl.event.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;

@DataJpaTest
@TestConstructor(autowireMode = AutowireMode.ALL)
@RequiredArgsConstructor
class QuerydslSelectTest {
    private static final QCampaign qCampaign = QCampaign.campaign;
    private final CampaignRepository campaignRepository;
    private final TestEntityManager testEntityManager;

    @Test
    void selectUseQModelFields() {
        long campaignAmount = 5L;
        Campaign campaign = new Campaign();
        campaign.setAmount(campaignAmount);
        String campaignName = "test";
        campaign.setName(campaignName);

        Campaign saved = testEntityManager.persist(campaign);
        testEntityManager.flush();
        testEntityManager.clear();

        Tuple tuple = campaignRepository.selectUseQModelFields(saved.getId());

        assertThat(tuple.get(qCampaign.id)).isEqualTo(saved.getId());
        assertThat(tuple.get(qCampaign.name)).isEqualTo(saved.getName());
        assertThat(tuple.get(qCampaign.amount)).isEqualTo(saved.getAmount());
    }

    @Test
    void selectUseQModelOnly() {
        long campaignAmount = 5L;
        Campaign campaign = new Campaign();
        campaign.setAmount(campaignAmount);
        String campaignName = "test";
        campaign.setName(campaignName);

        Campaign saved = testEntityManager.persist(campaign);
        testEntityManager.flush();
        testEntityManager.clear();

        Tuple tuple = campaignRepository.selectUseQModelOnly(saved.getId());

        assertThat(tuple.get(qCampaign.id)).isNull();
        assertThat(tuple.get(qCampaign.name)).isNull();
        assertThat(tuple.get(qCampaign.amount)).isNull();
        assertThat(tuple.get(qCampaign).getId()).isEqualTo(saved.getId());
        assertThat(tuple.get(qCampaign).getName()).isEqualTo(saved.getName());
        assertThat(tuple.get(qCampaign).getAmount()).isEqualTo(saved.getAmount());
        assertThat(tuple.get(qCampaign.amount.add(1L))).isEqualTo(saved.getAmount() + 1);
    }
}
