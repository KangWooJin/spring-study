package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.querydsl.jpa.impl.JPAQuery;

import kangwoojin.github.io.querydsl.user.QUser;
import kangwoojin.github.io.querydsl.user.User;

@DataJpaTest
class QuerydslApplicationTests {
    QUser qUser = QUser.user;
    @Autowired
    private TestEntityManager em;

    @Test
    void entityTest() {
        User user = new User();
        user.setName("woojin");

        User actual = em.persist(user);

        assertThat(actual).isNotNull();
    }

    @Test
    void queryDslTest() {
        User user = new User();
        user.setName("woojin");

        User saved = em.persist(user);

        JPAQuery<User> query = new JPAQuery<>(em.getEntityManager());

        User actual = query.from(qUser)
                           .where(qUser.name.eq("woojin").and(qUser.id.eq(saved.getId())))

                           .fetchOne();

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isEqualTo(actual.getId());
        assertThat(saved.getName()).isEqualTo(actual.getName());
    }
}
