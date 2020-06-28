package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.querydsl.jpa.impl.JPAQuery;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.Event;
import kangwoojin.github.io.querydsl.event.model.QCampaign;
import kangwoojin.github.io.querydsl.event.model.QEvent;

@DataJpaTest
class FlushModeTest {
    QCampaign qCampaign = QCampaign.campaign;
    QEvent qEvent = QEvent.event;
    EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        entityManager = testEntityManager.getEntityManager();
    }

    private Campaign generateCampaign(String name, Long amount) {
        Campaign campaign = new Campaign();
        campaign.setName(name);
        campaign.setAmount(amount);
        Event event = new Event();
        event.setAmount(5L);
        campaign.setEvents(List.of(event));
        Campaign savedCampaign = testEntityManager.persist(campaign);
        event.setCampaign(campaign);
        return savedCampaign;
    }

    @Test
    void flushModeWhenFlushModeIsAutoTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = RandomUtils.nextLong();
        Campaign campaign = generateCampaign(name, amount);

        JPAQuery<Campaign> query = new JPAQuery(entityManager);
        List<Campaign> campaigns = query.select(qCampaign)
                                        .from(qCampaign)
                                        .join(qCampaign.events)
                                        .setFlushMode(FlushModeType.AUTO) // default
                                        .fetch(); // fetch시에 flush mode에 따라서 flush가 발생

        assertThat(campaigns).hasSize(1);
    }

    @Test
    void flushModeWhenFlushModeIsCommitTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = RandomUtils.nextLong();
        Campaign campaign = generateCampaign(name, amount);

        JPAQuery<Campaign> query = new JPAQuery(entityManager);
        List<Campaign> campaigns = query.select(qCampaign)
                                        .from(qCampaign)
                                        .join(qCampaign.events)
                                        .setFlushMode(FlushModeType.COMMIT)
                                        .fetch(); // fetch시에 flush mode에 따라서 flush가 발생

        assertThat(campaigns).hasSize(0);
    }
}
