package kangwoojin.github.io.querydsl.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kangwoojin.github.io.querydsl.event.model.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long>, CampaignCustomRepository {
}
