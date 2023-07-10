package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired // 생성자에 autowired 어노테이션을 쓰면 Controller와 Service를 연결시켜준다.
    // 그러면 memberController가 생성이 될 때, spring bean에 등록되어 있는 memberService 객체를 넣어준다(주입한다.) == DI
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
