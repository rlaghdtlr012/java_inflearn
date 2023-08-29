package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        System.out.println("username = " + username + " age = " + age);

        response.getWriter().write("ok");
    }
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {
        System.out.println("memberName = " + memberName + " memberAge = " + memberAge);
        return "ok"; // Controller면서 return 값이 ok면 해당 이름의 View를 찾게 됨. -> 따라서 @ResponseBody 어노테이션 쓰면 됨
        // @ResponseBody 사용히면 뷰 조회 하지 않고, Http 응답에 "ok"를 박아서 응답함
    }

    @ResponseBody
    @RequestMapping("/request-param-v3") // @RequestMapping에서 이름과 변수명이 같다면 괄호 생략 가능하다
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        System.out.println("username = " + username + " age = " + age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4") // String, int 등의 단순 타입이면 @RequestMapping조차 생략가능(대신 이름 같아야 함)
    public String requestParamV4(String username, int age) {
        System.out.println("username = " + username + " age = " + age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required") // Param이 필수적인지 아닌지, username은 필수이고, age는 필수 아니다(true가 default임)
    public String requestParamRequired(@RequestParam(required = true) String username, @RequestParam(required = false) Integer age) {
//        System.out.println("username = " + username + " age = " + age);
        return "ok";
    }

    /**
     * @RequestParam
     * - defaultValue 사용
     *
     * 참고: defaultValue는 빈 문자의 경우에도 적용
     * /request-param-default?username=
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        System.out.println("username + age = " + username + age);
        return "ok";
    }

    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),
                paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1") // modelAttribute는 자동으로 helloData 객체를 생성해준다
    // modelAttribute는 요청 파라미터 이름으로 HelloData 객체의 프로퍼티를 찾고, 해당 프로퍼티의 setter를 호출해서 파라미터의 값을
    // 자동으로 바인딩 해준다.
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        System.out.println("helloData = " + helloData);
        return "ok";
    }

    /**
     * @ModelAttribute 생략 가능
     * String, int 같은 단순 타입 = @RequestParam
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribute
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());
        return "ok";
    }

}
