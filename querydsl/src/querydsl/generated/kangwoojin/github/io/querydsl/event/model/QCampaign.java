package kangwoojin.github.io.querydsl.event.model;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

/**
 * QCampaign is a Querydsl query type for Campaign
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCampaign extends EntityPathBase<Campaign> {

    private static final long serialVersionUID = 2145470686L;

    public static final QCampaign campaign = new QCampaign("campaign");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final ListPath<Event, QEvent> events = this.<Event, QEvent>createList("events", Event.class, QEvent.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCampaign(String variable) {
        super(Campaign.class, forVariable(variable));
    }

    public QCampaign(Path<? extends Campaign> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCampaign(PathMetadata metadata) {
        super(Campaign.class, metadata);
    }

}

