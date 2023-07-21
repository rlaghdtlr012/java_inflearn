package hello.core.singleton;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingleton {
    @Test
    @DisplayName("스프링은 CGLIB이라는 라이브러리를 통해 바이트 코드를 조작하여 생성되는 객체가 이전에 생성된 적이 없다면 새롭게 객체를" +
            "생성해주고 만약에 이전에 생성된 적이 있는 객체라면 그 생성되었던 객체를 반환함으로써 싱글톤을 보장해준다!!!")
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
    }
}
