package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DataJpaTest
@TestConstructor(autowireMode = AutowireMode.ALL)
class CampaignCustomRepositoryTest {
    private final CampaignRepository campaignRepository;
    private final TestEntityManager testEntityManager;
//    private final CampaignService campaignService;

    @Test
    void findByNameTest() {
        Campaign campaign = new Campaign();
        String name = "campaign";
        campaign.setName(name);
        Campaign savedCampaign = testEntityManager.persist(campaign);

        testEntityManager.flush();
        testEntityManager.clear();

        Campaign actual = campaignRepository.findByName(name);

        assertThat(actual.getId()).isEqualTo(savedCampaign.getId());
        assertThat(actual.getName()).isEqualTo(savedCampaign.getName());
    }

    @Test
    void findByNameByCampaignService() {
        Campaign campaign = new Campaign();
        String name = "campaign";
        campaign.setName(name);
        Campaign savedCampaign = testEntityManager.persist(campaign);

        testEntityManager.flush();
        testEntityManager.clear();
//        Campaign actual = campaignService.findByName(name);
//
//        assertThat(actual.getId()).isEqualTo(savedCampaign.getId());
//        assertThat(actual.getName()).isEqualTo(savedCampaign.getName());
    }
}
