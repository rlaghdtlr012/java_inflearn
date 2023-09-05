package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
        return mav; // 데이터와 뷰 이름을 한 번에 설정, 반환 가능, 더 많은 제어를 제공
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!"); // 
        return "response/hello"; // View 이름을 반환. 컨트롤러 메소드에서 데이터만 추가, 뷰 이름은 메소드의 반환 값으로 지정
    }
}
