package com.MinseoKangQ.jpashopapplication.repository;

import com.MinseoKangQ.jpashopapplication.domain.item.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

//    @PersistenceContext
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { // id 값이 없다는 것은, 새로 생성한 객체임을 의미함
            em.persist(item);
        } else {
            em.merge(item); // merge 는 update 와 유사함
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
