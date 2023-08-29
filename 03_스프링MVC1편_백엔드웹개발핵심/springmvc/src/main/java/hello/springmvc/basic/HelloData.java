package hello.springmvc.basic;

import lombok.Data;

@Data // @Data를 하면 @Getter, @Setter. @ToString 등을 자동으로 적용해준다.
public class HelloData {
    private String username;
    private int age;
}
