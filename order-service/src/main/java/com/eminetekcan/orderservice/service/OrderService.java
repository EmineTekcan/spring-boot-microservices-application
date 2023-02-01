package com.eminetekcan.orderservice.service;

import com.eminetekcan.orderservice.dto.OrderLineItemsDto;
import com.eminetekcan.orderservice.dto.OrderRequest;
import com.eminetekcan.orderservice.model.Order;
import com.eminetekcan.orderservice.model.OrderLineItems;
import com.eminetekcan.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

       List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemsDtos().stream()
                .map(orderLineItemsDto -> mapToOrderLineItems(orderLineItemsDto)).collect(Collectors.toList());
       order.setOrderLineItems(orderLineItems);

       orderRepository.save(order);
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItems.getPrice());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        orderLineItems.setSkuCode(orderLineItems.getSkuCode());
        return orderLineItems;
    }

}
