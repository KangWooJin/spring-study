package kangwoojin.github.io.querydsl.dependencyinjection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService implements InitializingBean {
    private final ConstructorService constructorService;
    private SetterService setterService;
    @Autowired
    private AutowiredService autowiredService;

    public MainService(ConstructorService constructorService) {
//        System.out.println("MainService Constructor");
//        System.out.println("MainService.constructorService : " + constructorService);
//        System.out.println("MainService.setterService : " + setterService);
//        System.out.println("MainService.autowiredService : " + autowiredService);
        this.constructorService = constructorService;
    }

    @Autowired
    public void setSetterService(SetterService setterService) {
//        System.out.println("MainService setSetterService");
//        System.out.println("MainService.constructorService : " + constructorService);
//        System.out.println("MainService.setterService : " + setterService);
//        System.out.println("MainService.autowiredService : " + autowiredService);
        this.setterService = setterService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println("MainService afterPropertiesSet");
//        System.out.println("MainService.constructorService : " + constructorService);
//        System.out.println("MainService.setterService : " + setterService);
//        System.out.println("MainService.autowiredService : " + autowiredService);
    }

    @PostConstruct
    public void init() {
//        System.out.println("MainService PostConstruct");
//        System.out.println("MainService.constructorService : " + constructorService);
//        System.out.println("MainService.setterService : " + setterService);
//        System.out.println("MainService.autowiredService : " + autowiredService);
    }
}
