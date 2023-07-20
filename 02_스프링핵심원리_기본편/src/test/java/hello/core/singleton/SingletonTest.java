package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 3. 참조값이 다른 것을 확인 요청이 올 때마다 객체를 생성하는 것이니까 각 객체의 참조값이 달라야 한다.
        // 계속 객체가 생성이 됨으로써 JVM 메모리에 계속 객체가 생기고, 적재됨
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletionServiceTest() {
        // SingletonService 새로운 객체 생성 -> 안됨
        // new SingletonService();

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // 싱글톤 패턴으로 만든 인스턴스들은 같은 참조값을 가진다.
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }
}
