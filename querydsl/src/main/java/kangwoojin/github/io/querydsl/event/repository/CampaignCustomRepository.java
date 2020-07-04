package kangwoojin.github.io.querydsl.event.repository;

import kangwoojin.github.io.querydsl.event.model.Campaign;

public interface CampaignCustomRepository {

    Campaign findByName(String name);
}
