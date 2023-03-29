package hello.spring.basic.request;

import lombok.extern.slf4j.Slf4j;
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
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
    public void requestBodyString(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}",messageBody);
        writer.write("ok");
    }
}
