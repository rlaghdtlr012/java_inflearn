package hello.core;

import lombok.Getter;
import lombok.Setter;

// Lombok 라이브러리를 사용하여 게터세터 메서드를 사용하지 않고도 @Getter, @Setter만 사용함을 통해 get, set 메서드를 만든 것과 같은 효과를 낸다
@Getter
@Setter
public class HelloLombok {
    
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("alsjkdlkasd");
        String name = helloLombok.getName();
        
        helloLombok.setAge(13);
        int age = helloLombok.getAge();
        System.out.println("helloLombok.name = " + name);
        System.out.println("helloLombok.age = " + age);
        
    }
}
