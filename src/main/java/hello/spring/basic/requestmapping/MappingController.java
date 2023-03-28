package hello.spring.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 요청 매핑
 */
@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 매핑 주소를 배열로 지정하면 다중 설정이 가능하다.
     */
    @RequestMapping(value = {"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * method 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     * 요청방식 method를 설정하지 않으면 모든 요청 method를 허용한다. (Get으로 지정한 뒤 Post로 요청하면 405에러가 발생한다.)
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 사용
     *
     * @PathVariable("userId") String userId -> @PathVariable userId
     * 변수명이 같으면 ("userId")생략 가능
     * /mapping/userA
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     *
     * /mapping-param 입력시 400 BadRequest
     * /mapping-param?mode=debug 입력시 200 Ok
     * 즉 mode=debug값을 갖는 파라미터를 가져야만 호출이된다. (파라미터가 일치하더라도 값이 다르면 400 Error)
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(String mode) {
        log.info("mappingParam = {}",mode);
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     * header정보에 mode=debug 정보가 있어야만 호출된다.
     * [PostMan] - Headers 최하단 Key : mode / Value : debug 입력
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     * [PostMan] - Body -> raw -> JSON -> {"123":"123"} [Send]
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     * [PostMan] - Headers Accept값 수정 (체크 해제 후 최하단에 Accept직접 입력)
     * application/json 지정시 => Not Acceptable 406 Error 발생
     * text/html 지정시 => 200 Ok
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
