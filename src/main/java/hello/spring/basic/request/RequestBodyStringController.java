package hello.spring.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    /**
     * request객체를 통해 inputStream을 사용하여 messageBody 데이터 Read
     * [Postman] -> Body - raw:text
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}",messageBody);
        response.getWriter().write("ok");
    }


    /**
     * inputStream과 Writer를 매개변수로 사용
     * messageBody 데이터 Read, 및 Wtrie
     * [Postman] -> Body - raw:text
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}",messageBody);
        writer.write("ok");
    }

    /**
     * HttpEntity<String>활용
     * HttpHeader Body정보 직접 조회
     * 응답에서도 사용이 가능하다.
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}",messageBody);
        return new HttpEntity<>("ok");
    }

    /**
     * RequestEntity,ResponseEntity
     * HttpHeader : 요청에서 사용, Body정보 직접 조회
     * ResponseEntity : 응답에서 사용, 상태정보를 담을 수 있다. (Created - 201)
     */
    @PostMapping("/request-body-string-v4")
    public ResponseEntity<String> requestBodyStringV4(RequestEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}",messageBody);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }
}
