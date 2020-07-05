package kangwoojin.github.io.querydsl.event.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.Tuple;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.CampaignDto;
import kangwoojin.github.io.querydsl.event.model.QCampaign;
import kangwoojin.github.io.querydsl.event.model.QEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CampaignCustomRepositoryImpl extends QuerydslRepositorySupport implements CampaignCustomRepository {
    private static final QCampaign qCampaign = QCampaign.campaign;
    private static final QEvent qEvent = QEvent.event;

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

    @Override
    public List<CampaignDto> getAmountSum(Long eventId) {
        return from(qCampaign)
                .select(qCampaign.id, qCampaign.name, qCampaign.amount.sum(), qEvent.amount.sum())
                .distinct()
                .leftJoin(qCampaign.events, qEvent).on(qEvent.id.eq(eventId))
                .groupBy(qCampaign.name)
                .fetch()
                .stream()
                .peek(tuple -> log.info("tuple {}", tuple))
                .map(tuple -> new CampaignDto().setCampaignId(tuple.get(qCampaign.id))
                                               .setCampaignName(tuple.get(qCampaign.name))
                                               .setAmountSum(tuple.get(qCampaign.amount.sum()))
                                               .setEventAmountSum(tuple.get(qEvent.amount.sum())))
                .collect(Collectors.toList());

    }

    @Override
    public Tuple selectUseQModelFields(Long id) {
        return from(qCampaign)
                .select(qCampaign.id, qCampaign.name, qCampaign.amount)
                .where(qCampaign.id.eq(id))
                .fetchOne();
    }

    @Override
    public Tuple selectUseQModelOnly(Long id) {
        return from(qCampaign)
                .select(qCampaign, qCampaign.amount.add(1L))
                .where(qCampaign.id.eq(id))
                .fetchOne();
    }

}
