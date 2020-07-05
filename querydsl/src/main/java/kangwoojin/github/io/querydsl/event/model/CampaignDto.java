package kangwoojin.github.io.querydsl.event.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CampaignDto {
    private Long campaignId;
    private String campaignName;
    private Long amountSum;
    private Long eventAmountSum;
}
