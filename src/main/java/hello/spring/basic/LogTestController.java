package hello.spring.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //lombok에서 지원하는 Logger
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass()); //Slf4j
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class); //Slf4j
    @RequestMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        System.out.println("name = " + name);// name = Spring

        // 2023-03-28 22:40:21.052  INFO 24812 --- [io-8080-exec-10] hello.spring.basic.LogTestController     :  info log=Spring
        log.trace("trace log={}", name); //application.properties - logging.level.hello.spring=trace
        log.debug("debug log={}", name); //개발서버 debug레벨 : trace 생략
        log.info(" info log={}", name);  //운영서버  info레벨 : trace, debug 생략
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        /**
         * 출력시 {} 문법이 아닌 + 문자열 연산을 하게되면 logging level이 debug여도 연산이후에 출력하지않기때문에
         * 쓸대없는 resource를 잡아먹게 된다.
         * 만약 debug level에서 trace문법을 {}로 사용하였다면 단순히 파라미터만 넘겨받은 후 출력하지 않으므로
         * resource를 잡아먹지 않게 된다.
         * 따라서 log출력시 {}문법을 사용하고 + 연산은 사용하지 않는다
         *
         * 로그 레벨에 따라 개발서버에서는 모든 로그를 출력하고 운영 서버에서는 출력하지 않는 등 상황에 맞게 조절이 가능하다.
         * Logger는 console에만 출력하는 것이 아닌 파일로도 남길 수 있으며, 네트워크로 전송도 가능하다.
         * 파일로 남길 때 일별, 특정 용량에 따라 파일 분할이 가능하다.
         * System.out보다 성능이 좋으므로 (내부 버퍼링, 멀티쓰레드 이슈 등등) 실무에서 꼭 사용해야한다.
         */

        return "ok";
    }
}
