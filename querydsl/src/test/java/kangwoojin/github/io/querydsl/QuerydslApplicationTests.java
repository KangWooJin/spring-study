package kangwoojin.github.io.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import kangwoojin.github.io.querydsl.user.QUser;
import kangwoojin.github.io.querydsl.user.User;
import kangwoojin.github.io.querydsl.user.UserService;

@AutoConfigureTestEntityManager
@Transactional
@SpringBootTest
class QuerydslApplicationTests {
    QUser qUser = QUser.user;
    @Autowired
    private TestEntityManager em;
    @Autowired
    private UserService userService;

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

        User actual = userService.findByNameAndId(saved.getName(), saved.getId());

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isEqualTo(actual.getId());
        assertThat(saved.getName()).isEqualTo(actual.getName());
    }
}
