package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // 밑에 주석 처리 된 @Autowired 의존성 주입이랑 같은 기능 제공.
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
//    public String save(@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity, Model model) {
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
//
//        model.addAttribute("item", item);
//        return "basic/item";
//    }

    /**
     * @ModelAttribute("item") Item item
     * model.addAttribute("item", item); 자동 추가
     */
//    @PostMapping("/add")
//    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
//        itemRepository.save(item);
//        //model.addAttribute("item", item); //자동 추가, 생략 가능
//        return "basic/item";
//    }
//
//    /**
//     * @ModelAttribute name 생략 가능
//     * model.addAttribute(item); 자동 추가, 생략 가능
//     * 생략시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
//     */
//    @PostMapping("/add")
//    public String addItemV3(@ModelAttribute Item item) {
//        itemRepository.save(item);
//        return "basic/item";
//    }

    /**
     * @ModelAttribute 자체 생략 가능
     * model.addAttribute(item) 자동 추가
     */
//    @PostMapping("/add")
//    public String addItemV4(Item item) {
//        itemRepository.save(item);
//        return "redirect:/basic/items/" + item.getId(); // Post 요청을 하고 view 템플릿을 반환할 시, 만약 그 상태에서 새로고침을 하면
//        // Post 요청이 중복으로 계속해서 요청된다. 따라서 return 값을 view 이름을 할 것이 아니라 redirect를 사용해야 한다.
//        // Post -> Redirect -> Get(내가 지정한 특정 페이지로 Get 요청 후, 이동)
//    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId()); // url Encoding도 알아서 다 해줌
        redirectAttributes.addAttribute("status", true); // 밑에 return 문 안에 포함되어 있지 않은 값들은
        // ?status=true 쿼리 파라미터의 형태로 뒤에 붙어서 반환된다.
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
    // 테스트용 데이터 추가
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 100));
        itemRepository.save(new Item("itemB", 20000, 200));
    }

}
