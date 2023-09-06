package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Long id;
    private String itemName;
    private Integer price;  // Integer => 무조건 숫자가 들어갈 필요는 없음. null이 들어갈 수 있음
    private Integer quantity; // int => 무조건 숫자가 들어가야함. 0이라도 들어가야 함

    // 기본 생성자
    public Item() {
    }

    // id를 제외한 생성자
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
