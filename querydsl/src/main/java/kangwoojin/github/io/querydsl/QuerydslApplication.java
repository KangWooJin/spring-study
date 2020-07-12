package kangwoojin.github.io.querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(repositoryImplementationPostfix = "Impl", bootstrapMode = BootstrapMode.DEFERRED)
public class QuerydslApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerydslApplication.class, args);
    }

}
