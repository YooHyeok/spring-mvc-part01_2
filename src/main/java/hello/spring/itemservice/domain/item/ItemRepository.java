package hello.spring.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    /**
     * 상품 저장
     * @param item
     * @return
     */
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
}
