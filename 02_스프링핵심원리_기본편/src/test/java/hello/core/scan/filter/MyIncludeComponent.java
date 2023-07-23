package hello.core.scan.filter;

import java.lang.annotation.*;

// 이것은 컴포넌트 스캔에 포함
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
