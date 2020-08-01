package kangwoojin.github.io.springsecurity.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findbyUsername(String username);
}
