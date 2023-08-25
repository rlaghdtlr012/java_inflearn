package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    // Lombok(@Slf4j)을 사용하면 바로 밑 13 line의 코드를 이미 작성한 것과 똑같은 효과를 볼 수 있음 
//    private final Logger log = LoggerFactory.getLogger(getClass());
    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        // 밑으로 갈 수록 레벨이 높아지고 중요도가 높아지는거
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);
        return "ok";
    }
}
