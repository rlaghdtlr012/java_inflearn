package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
