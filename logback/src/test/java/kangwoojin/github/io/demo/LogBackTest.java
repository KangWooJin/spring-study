package kangwoojin.github.io.demo;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class LogBackTest {

    @Test
    void logBackTest() {
        log.trace("logBackTest");
        log.debug("logBackTest");
        log.info("logBackTest");
        log.warn("logBackTest");
        log.error("logBackTest");
    }
}
