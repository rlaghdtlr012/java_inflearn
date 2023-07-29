package hello.core.member;

import hello.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // AppConfig appConfig = new AppConfig();
        // appConfig.memberService를 통해 memberServiceImpl객체를 생성하면서 MemoryMemberRepository를 주입 받는다.
        // MemberService memberService = appConfig.memberService();

        // 스프링은 모든 것이 ApplicationContext로부터 시작된다. 이게 스프링 컨테이너라고 할 수 있다.
        // ApplicationContext가 AppConfig 안에 있는 모든 Bean(객체들)을 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 첫 번째 인자에 꺼내올 빈의 이름, 두 번째 인자에 반환 타입 정의
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember = " + findMember);
        System.out.println("new member = " + member);
    }
}
