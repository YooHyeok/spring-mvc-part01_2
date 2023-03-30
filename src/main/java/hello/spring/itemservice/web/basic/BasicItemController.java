package hello.spring.itemservice.web.basic;

import hello.spring.itemservice.domain.item.Item;
import hello.spring.itemservice.domain.item.ItemRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    /**
     * 상품 목록
     * @param model
     * @return
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     * 상품 단건 조회
     * @param itemId
     * @param model
     * @return
     */
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

    /**
     * 상품 저장 - @ModelAttribute
     * ModealAttribute의 ("") 속성을 생략할 경우 ex 객체 클래스명의 첫글자를 소문자로 치환하여 Model에 담는다.
     * ModelAttribute 애노테이션을 생략하여도 자동으로 소문자로 치환하여 Model에 담아준다.
     */
    @PostMapping("/add")
    public String addItemV2(Item item) {

        itemRepository.save(item);
        return "basic/item";
    }

    public String addItemV1(@RequestParam String itemName,
                            @RequestParam Integer price,
                            @RequestParam Integer quantity,
                            Model model) {

//        Item item = new Item(itemName, price, quantity);
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        Item savedItem = itemRepository.save(item);
        model.addAttribute("item", savedItem);
        return "basic/item";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
