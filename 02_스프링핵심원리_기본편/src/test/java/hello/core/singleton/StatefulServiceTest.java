package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A 사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadA: B 사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용지A의 주문 금액 조회 10000원이 나오길 expected
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 실제로는 20000원이 나옴!!
        // statefulService는 싱글톤 패턴의 같은 객체를 쓰고 있기에, 서로 다른 스레드가 서로 간섭을 일으켜서 의도치 않게 공유되는 필드값의 변화가 일어날 수도 있다.
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(10000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}