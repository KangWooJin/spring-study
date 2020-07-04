package kangwoojin.github.io.querydsl.event.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public abstract class QuerydslCustomRepositorySupport extends QuerydslRepositorySupport {
    private JPAQueryFactory queryFactory;

    public QuerydslCustomRepositorySupport(Class<?> domainClass) {
        super(domainClass);
    }

    @Autowired
    @Override
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    protected <T> JPAQuery<T> select(Expression<T> expr) {
        return queryFactory.select(expr);
    }

    protected <T> JPAQuery<T> selectFrom(EntityPath<T> from) {
        return queryFactory.selectFrom(from);
    }

}
