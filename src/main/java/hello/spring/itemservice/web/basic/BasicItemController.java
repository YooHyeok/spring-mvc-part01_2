package hello.spring.itemservice.web.basic;

import hello.spring.itemservice.domain.item.Item;
import hello.spring.itemservice.domain.item.ItemRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addItemV4(Item item, RedirectAttributes redirectAttributes) { //저장 Version4 - RedirecAttribute

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId()); //redirect의 매핑주소에 쿼리파라미터 형태로 작성된다.
        redirectAttributes.addFlashAttribute("itemId", savedItem.getId()); //session에 값을 담은뒤 messageBody에 담아서 넘긴다.
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}"; // [URL] : localhsot:8080/basic/items/3?satus=true
    }

    public String addItemV3(Item item) { //저장 Version3 - PRG 패턴 적용

        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }

    public String addItemV2(Item item) { //저장 Version2 - @ModelAttribute 생략

        itemRepository.save(item);
        return "basic/item";
    }

    public String addItemV1(@RequestParam String itemName,
                            @RequestParam Integer price,
                            @RequestParam Integer quantity,
                            Model model) { //저장 Version1 - @RequestParam

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
     * 상품 수정 폼
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    /**
     * 상품 수정 저장
     * return redirect:/상품상세
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {

       itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
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
