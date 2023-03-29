package hello.spring.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    /**
     * 상품 저장
     * @param item
     * @return 상품정보(item)
     */
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    /**
     * 상품 단건 조회
     * @param id
     * @return store.get(id) (id로 store로부터 검색)
     */
    public Item findById(Long id) {
        return store.get(id);
    }

    /**
     * 상품 전체 조회
     * @return new ArrayList<>(store.values()) - store에 있는 모든 값 추출후 ArrayList에 저장
     */
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }
}
