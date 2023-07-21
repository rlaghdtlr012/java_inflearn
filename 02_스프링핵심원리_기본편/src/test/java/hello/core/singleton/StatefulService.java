package hello.core.singleton;

public class StatefulService {

    // stateful한 상태(지양해야하는 코드)
    private int price; // 상태를 유지하는 필드
    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제!!
    }

    // stateless하게 코드를 짜야한다
    public int order1(String name, int price) {
        return price; // 이렇게 stateless하게 코드를 작성해야 한다.
    }

    public int getPrice() {
        return price;
    }
}
