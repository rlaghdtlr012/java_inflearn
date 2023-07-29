package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
    static class TestBean {
        // 주입할 스프링 빈이 없어도 동작해야 하는 경우
        // Autowired의 required를 false로 하면 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출이 안된다.
        // 의존관계가 없다면 밑에 메소드 자체가 호출이 안돼서 test case에도 적용이 되지 않는 모습
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 주입할 대상이 없다면 null이 입력된다.
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 자동 주입할 대상이 없다면 Optional.empty가 입력된다.
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
