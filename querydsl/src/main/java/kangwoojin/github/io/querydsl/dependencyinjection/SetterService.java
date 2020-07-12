package kangwoojin.github.io.querydsl.dependencyinjection;

import org.springframework.stereotype.Service;

@Service
public class SetterService {
    public SetterService() {
        System.out.println("SetterService Constructor");
    }
}
