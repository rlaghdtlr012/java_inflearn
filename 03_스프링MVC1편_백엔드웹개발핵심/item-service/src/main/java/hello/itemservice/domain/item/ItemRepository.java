package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>(); // static 사용. 실무에서 멀티 스레드에서 store에 접근하는 경우 해시맵 쓰면 안된다.
    private static long sequence = 0L; // static 사용

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item); // item.getId라는 고유한 식별자에 item이라는 객체를 넣어서 보관, 저장한다.
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        // FM은 ID를 제외한 ItemParameterDTO 객체를 새로 만드는 것이 낫다. 왜냐하면 ID가 있는데 쓰지 않으면 개발자에게 혼란을 줄 수 있어서
    }
}
