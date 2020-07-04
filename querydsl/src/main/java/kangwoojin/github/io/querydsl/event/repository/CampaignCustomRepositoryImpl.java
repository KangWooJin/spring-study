package kangwoojin.github.io.querydsl.event.repository;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.QCampaign;

public class CampaignCustomRepositoryImpl extends QuerydslCustomRepositorySupport implements CampaignCustomRepository {
    private static final QCampaign qCampaign = QCampaign.campaign;

    public CampaignCustomRepositoryImpl() {
        super(Campaign.class);
    }

    @Override
    public Campaign findByName(String name) {
        return from(qCampaign)
                .select(qCampaign)
                .where(qCampaign.name.eq(name))
                .fetchOne();
    }

//    @Override
//    public Campaign findByName(String name) {
//        return selectFrom(qCampaign).where(qCampaign.name.eq(name)).fetchOne();
//    }
}
