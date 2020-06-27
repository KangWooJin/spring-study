package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.Event;
import kangwoojin.github.io.querydsl.event.model.QCampaign;
import kangwoojin.github.io.querydsl.event.model.QEvent;

@DataJpaTest
class CampaignQueryDslTest {
    QCampaign qCampaign = QCampaign.campaign;
    QEvent qEvent = QEvent.event;
    @Autowired
    private TestEntityManager testEntityManager;
    EntityManager entityManager;

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
        return testEntityManager.persist(campaign);
    }

    @Test
    void selectByIdTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = RandomUtils.nextLong();
        Campaign campaign = generateCampaign(name, amount);
        JPAQuery<Campaign> query = new JPAQuery(entityManager);

        Campaign actual = query.select(qCampaign)
                               .from(qCampaign)
                               .where(qCampaign.id.eq(campaign.getId()))
                               .fetchOne();

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getAmount()).isEqualTo(amount);
    }

    @Test
    void updateTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = RandomUtils.nextLong();
        Campaign campaign = generateCampaign(name, amount);

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        long count = jpaQueryFactory.update(qCampaign)
                                    .where(qCampaign.id.eq(campaign.getId()))
                                    .set(qCampaign.name, "changedName")
                                    .execute();

        assertThat(count).isEqualTo(1);

        testEntityManager.clear();

        JPAQuery<Campaign> query = new JPAQuery(entityManager);
        Campaign actual = query.select(qCampaign)
                               .from(qCampaign)
                               .where(qCampaign.id.eq(campaign.getId()))
                               .fetchOne();

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo("changedName");
        assertThat(actual.getAmount()).isEqualTo(amount);

    }

    @Test
    void deleteTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = RandomUtils.nextLong();
        Campaign campaign = generateCampaign(name, amount);

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        long count = jpaQueryFactory.delete(qCampaign)
                                    .where(qCampaign.id.eq(campaign.getId()))
                                    .execute();

        assertThat(count).isEqualTo(1);

        testEntityManager.clear();

        JPAQuery<Campaign> query = new JPAQuery(entityManager);
        Campaign actual = query.select(qCampaign)
                               .from(qCampaign)
                               .where(qCampaign.id.eq(campaign.getId()))
                               .fetchOne();

        assertThat(actual).isNull();
    }

    @Test
    void subQueryTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = 10L;
        Campaign campaign = generateCampaign(name, amount);

        JPAQuery<Campaign> query = new JPAQuery(entityManager);
        Campaign actual = query.select(qCampaign)
                               .from(qCampaign)
                               .where(qCampaign.amount.gt(
                                       JPAExpressions.select(qEvent.amount)
                                                     .from(qEvent)
                               ))
                               .fetchOne();

        assertThat(actual).isNotNull();
    }
}
