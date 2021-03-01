package jpabook.jshop.domain.sevice;

import jpabook.jshop.domain.Delivery;
import jpabook.jshop.domain.Member;
import jpabook.jshop.domain.Order;
import jpabook.jshop.domain.OrderItem;
import jpabook.jshop.domain.item.Item;
import jpabook.jshop.repository.ItemRepository;
import jpabook.jshop.repository.MemberRepository;
import jpabook.jshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

//        OrderItem orderItem1 = new OrderItem();
//        orderItem1.setOrderPrice();

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        // orderItem 과 delivery 에 casecade 가 되어 있어서 order가 persist 될 떄 함꼐 persist 됨
        // orderItem 과 delivery 는 order 에서만 참조하고 라이프 사이클이 동일하기 떄문에 현 로직에서는 casecade 사용
        // 이외는 자제. 추후 리팩토링으로 설계 추천
        orderRepository.save(order);

        return order.getId();

    }

    /**
     * 주문 취소
     */
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel(); // data 변경내역감지
    }

    //검색
//    public List<Order> findOrder(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }
}
