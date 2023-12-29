package com.MinseoKangQ.jpashopapplication.service;

import com.MinseoKangQ.jpashopapplication.domain.item.Book;
import com.MinseoKangQ.jpashopapplication.domain.item.Item;
import com.MinseoKangQ.jpashopapplication.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional // 클래스 자체가 readOnly 이므로 작성
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 영속성 컨텍스트가 자동 변경
     * 변경 감지를 사용하자!! 병합은 필드를 선택할 수 없다!!
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); // findItem 은 영속 상태임

        // 실무에서는 아래와 같이 의미있는 메소드로 값을 변경하자. 이렇게 해야 변경 지점이 모두 엔티티로 간다.
        // findItem.change(price, name, stockQuantity);

        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
