package kangwoojin.github.io.querydsl.dependencyinjection;

import org.springframework.stereotype.Service;

@Service
public class AutowiredService {
    public AutowiredService() {
        System.out.println("AutowiredService Constructor");
    }
}
