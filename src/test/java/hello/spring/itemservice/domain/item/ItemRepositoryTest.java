package hello.spring.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() { //itemRepository 매 test 실행 종료 초기화
        itemRepository.clearStore();
    }
}
