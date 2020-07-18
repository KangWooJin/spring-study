package kangwoojin.github.io.gradledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class GradleDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradleDemoApplication.class, args);
    }

}
