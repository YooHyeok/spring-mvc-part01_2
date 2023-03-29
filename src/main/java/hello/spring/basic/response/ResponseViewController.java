package hello.spring.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    /**
     * ModelAndView 활용 응답
     * @return ModelAndView
     */
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("basic/response/hello").addObject("data", "hello!");
        return mav;
    }

    /**
     * Model 활용 String응답
     * @return String
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "basic/response/hello";
    }

    /**
     * Model 활용 void 반환 없음
     * /response/hello uri 매핑 주소와 View 논리 주소가 같으면 자동으로 반환해준다.
     */
    @RequestMapping("/basic/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
