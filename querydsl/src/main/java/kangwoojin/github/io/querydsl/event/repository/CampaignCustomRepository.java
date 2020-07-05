package kangwoojin.github.io.querydsl.event.repository;

import java.util.List;

import com.querydsl.core.Tuple;

import kangwoojin.github.io.querydsl.event.model.Campaign;
import kangwoojin.github.io.querydsl.event.model.CampaignDto;

public interface CampaignCustomRepository {

    Campaign findByName(String name);

    List<CampaignDto> getAmountSum(Long eventId);

    Tuple selectUseQModelFields(Long id);

    Tuple selectUseQModelOnly(Long id);
}
