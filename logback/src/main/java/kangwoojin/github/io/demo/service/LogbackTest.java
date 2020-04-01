package kangwoojin.github.io.demo.service;

import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LogbackTest {

    @GetMapping("/logback")
    public String test(@RequestParam int size) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("logging start");
        for (int i = 0; i < size; ++i) {
            log.trace("{}", Thread.currentThread().getName());
            log.debug("{}", Thread.currentThread().getName());
            log.info("{}", Thread.currentThread().getName());
            log.warn("{}", Thread.currentThread().getName());
            log.error("{}", Thread.currentThread().getName());
        }

        stopWatch.stop();

        log.info("seconds : {}", stopWatch.getTotalTimeSeconds());
        return String.valueOf(stopWatch.getTotalTimeSeconds());
    }
}
