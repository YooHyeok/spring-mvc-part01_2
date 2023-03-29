package hello.spring.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.spring.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello", "age":20}
 * content-type : application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * HttpMsgBody-JSON-V1
     * inputStream, ObjectMapper, HttpServletRequest, HttpServletResponse 활용
     */
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);//json문자를 java객체로 변환
        log.info("username = {} , age = {}", helloData.getUsername(), helloData.getAge());
        response.getWriter().write("ok");
    }

    /**
     * HttpMsgBody-JSON-V2
     * @RequestBody, ObjectMapper, @ResponseBody 활용
     */
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);//json문자를 java객체로 변환
        log.info("username = {} , age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * HttpMsgBody-JSON-V3
     * @RequestBody, @ResponseBody 활용
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("messageBody = {}", helloData);
        log.info("username = {} , age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
