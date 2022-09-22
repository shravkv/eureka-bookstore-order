package com.bridgelabz.bookstore_order.service;

import com.bridgelabz.bookstore_order.dto.OrderDTO;
import com.bridgelabz.bookstore_order.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrderByCart(OrderDTO orderDTO);
    Order placeOrder(OrderDTO orderDTO);

    List<Order> getAllOrders();

    String deleteById(long orderId, long userId);

    Order updateOrderById(long orderId, OrderDTO orderDTO);
}
