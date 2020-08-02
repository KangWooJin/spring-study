package kangwoojin.github.io.springsecurity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private String role;

    public void encodePassowrd(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
