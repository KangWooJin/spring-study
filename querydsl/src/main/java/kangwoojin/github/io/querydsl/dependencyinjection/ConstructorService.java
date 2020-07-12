package kangwoojin.github.io.querydsl.dependencyinjection;

import org.springframework.stereotype.Service;

@Service
public class ConstructorService {
    public ConstructorService() {
        System.out.println("ConstructorService Constructor");
    }
}
