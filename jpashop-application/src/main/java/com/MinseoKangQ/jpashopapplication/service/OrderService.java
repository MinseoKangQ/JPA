package com.MinseoKangQ.jpashopapplication.service;

import com.MinseoKangQ.jpashopapplication.domain.Delivery;
import com.MinseoKangQ.jpashopapplication.domain.Member;
import com.MinseoKangQ.jpashopapplication.domain.Order;
import com.MinseoKangQ.jpashopapplication.domain.OrderItem;
import com.MinseoKangQ.jpashopapplication.domain.item.Item;
import com.MinseoKangQ.jpashopapplication.repository.ItemRepository;
import com.MinseoKangQ.jpashopapplication.repository.MemberRepository;
import com.MinseoKangQ.jpashopapplication.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장 - CascadeType.ALL
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {

        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소 - 변경 감지 (JPA 의 강점)
        order.cancel();
    }

    /**
     * 검색
     */
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }

}
