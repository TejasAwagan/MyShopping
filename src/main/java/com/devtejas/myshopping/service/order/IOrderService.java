package com.devtejas.myshopping.service.order;

import com.devtejas.myshopping.dto.OrderDto;
import com.devtejas.myshopping.models.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);

    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
