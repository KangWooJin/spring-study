package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.querydsl.jpa.impl.JPAQuery;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.QCampaign;

@DataJpaTest
class CampaignQueryDslTest {
    QCampaign qCampaign = QCampaign.campaign;
    @Autowired
    private TestEntityManager testEntityManager;

    private Campaign generateCampaign(String name, Long amount) {
        Campaign campaign = new Campaign();
        campaign.setName(name);
        campaign.setAmount(amount);
        return testEntityManager.persistAndFlush(campaign);
    }

    @Test
    void selectByIdTest() {
        String name = "campaign";
        long amount = 1L;
        Campaign campaign = generateCampaign(name, amount);
        JPAQuery<Campaign> query = new JPAQuery(testEntityManager.getEntityManager());

        Campaign actual = query.select(qCampaign)
                               .from(qCampaign)
                               .where(qCampaign.id.eq(campaign.getId()))
                               .fetchOne();

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getAmount()).isEqualTo(amount);

    }
}
