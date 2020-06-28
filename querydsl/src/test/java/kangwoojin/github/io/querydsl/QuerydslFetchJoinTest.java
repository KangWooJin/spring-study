package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

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
class QuerydslFetchJoinTest {
    QCampaign qCampaign = QCampaign.campaign;
    QEvent qEvent = QEvent.event;
    EntityManager entityManager;
    int size = 1;
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        entityManager = testEntityManager.getEntityManager();
    }

    private Event generateEvent(Campaign campaign) {
        Event event = new Event();
        event.setAmount(RandomUtils.nextLong());
        event.setCampaign(campaign);

        return event;
    }

    private Campaign generateCampaign(String name, Long amount) {
        Campaign campaign = new Campaign();
        campaign.setName(name);
        campaign.setAmount(amount);
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            events.add(generateEvent(campaign));
        }
        campaign.setEvents(events);
        Campaign savedCampaign = testEntityManager.persist(campaign);

        return savedCampaign;
    }

    @Test
    void notUsedFetchJoinTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = RandomUtils.nextLong();
        Campaign campaign = generateCampaign(name, amount);

        testEntityManager.flush();
        testEntityManager.clear();

        JPAQuery<Campaign> query = new JPAQuery(entityManager);
        List<Campaign> campaigns = query.select(qCampaign)
                                        .from(qCampaign)
                                        .join(qCampaign.events)
                                        .fetch(); // fetch시에 flush mode에 따라서 flush가 발생

        assertThat(campaigns).hasSize(1);
        assertThat(campaigns.get(0).getEvents()).hasSize(size);
        assertThat(campaigns.get(0).getEvents().get(0).getId()).isNotNull();
    }

    @Test
    void fetchJoinTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = RandomUtils.nextLong();
        Campaign campaign = generateCampaign(name, amount);
        testEntityManager.flush();
        testEntityManager.clear();

        JPAQuery<Campaign> query = new JPAQuery(entityManager);
        List<Campaign> campaigns = query.select(qCampaign)
                                        .from(qCampaign)
                                        .join(qCampaign.events).fetchJoin()
                                        .fetch(); // fetch시에 flush mode에 따라서 flush가 발생

        assertThat(campaigns).hasSize(1);
        assertThat(campaigns.get(0).getEvents()).hasSize(size);
        assertThat(campaigns.get(0).getEvents().get(0).getId()).isNotNull();
    }

    @Test
    void joinUseOnTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = RandomUtils.nextLong();
        Campaign campaign = generateCampaign(name, amount);
        testEntityManager.flush();
        testEntityManager.clear();

        JPAQuery<Campaign> query = new JPAQuery(entityManager);
        List<Campaign> campaigns = query.select(qCampaign)
                                        .from(qCampaign)
                                        .join(qCampaign.events).fetchJoin()
                                        .join(qCampaign.events, qEvent).on(qEvent.campaign.id.eq(campaign.getId()))
                                        .fetch(); // fetch시에 flush mode에 따라서 flush가 발생

        assertThat(campaigns).hasSize(1);
        assertThat(campaigns.get(0).getEvents()).hasSize(size);
        assertThat(campaigns.get(0).getEvents().get(0).getId()).isNotNull();
    }

    @Test
    void joinWhereConditionTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = RandomUtils.nextLong();
        Campaign campaign = generateCampaign(name, amount);
        testEntityManager.flush();
        testEntityManager.clear();

        JPAQuery<Campaign> query = new JPAQuery(entityManager);
        List<Campaign> campaigns = query.select(qCampaign)
                                        .from(qCampaign)
                                        .join(qCampaign.events, qEvent).fetchJoin()
                                        .where(qEvent.campaign.id.isNull().or(qEvent.campaign.id.eq(campaign.getId())))
                                        .fetch(); // fetch시에 flush mode에 따라서 flush가 발생

        assertThat(campaigns).hasSize(1);
        assertThat(campaigns.get(0).getEvents()).hasSize(size);
        assertThat(campaigns.get(0).getEvents().get(0).getId()).isNotNull();
    }

    @Test
    void fakeFetchJoinTest() {
        String name = RandomStringUtils.randomAlphabetic(5);
        long amount = RandomUtils.nextLong();
        Campaign campaign = generateCampaign(name, amount);
        testEntityManager.flush();
        testEntityManager.clear();

        JPAQuery<Campaign> query = new JPAQuery(entityManager);
        List<Campaign> campaigns = query.select(qCampaign)
                                        .from(qCampaign)
                                        .join(qEvent).on(qEvent.campaign.eq(qCampaign)).fetchJoin()
                                        .fetch(); // fetch시에 flush mode에 따라서 flush가 발생

        assertThat(campaigns).hasSize(1);
        assertThat(campaigns.get(0).getEvents()).hasSize(size);
        assertThat(campaigns.get(0).getEvents().get(0).getId()).isNotNull();
    }
}
