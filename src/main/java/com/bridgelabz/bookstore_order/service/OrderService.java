package com.bridgelabz.bookstore_order.service;

import com.bridgelabz.bookstore_order.dto.OrderDTO;
import com.bridgelabz.bookstore_order.email.EmailService;
import com.bridgelabz.bookstore_order.exception.CustomException;
import com.bridgelabz.bookstore_order.model.Order;
import com.bridgelabz.bookstore_order.model.User;
import com.bridgelabz.bookstore_order.model.Book;
import com.bridgelabz.bookstore_order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    private RestTemplate restTemplate;

    private Order order;

    @Override
    public Order placeOrderByCart(OrderDTO orderDTO) {
        User userObject = restTemplate.getForObject("http://localhost:8081/user/id/"+orderDTO.getUser(), User.class);
        Book bookObject = restTemplate.getForObject("http://localhost:8082/book/id/"+orderDTO.getBook(), Book.class);
        System.out.println(userObject.getFirstName());
        System.out.println(bookObject.getBookName());
        if (userObject.equals(null) && bookObject.equals(null)) {
            throw new CustomException("User or Book Id is not present in database!");
        } else {
            Order order = new Order(orderDTO);
            orderRepository.save(order);
            emailService.sendEmail(userObject.getEmail(), "Order placed!", "Hii...."+userObject.getFirstName()+" ! \n\n Your order has been placed successfully! Order details are below: \n\n Order id:  " + order.getOrderId() + "\n Order date:  " + order.getDate() + "\n Order Price:  " + order.getPrice() + "\n Order quantity:  " + order.getQuantity() + "\n Order address:  " + order.getAddress() + "\n Order user id:  " + order.getUser() + "\n Order book id:  " + order.getBook() + "\n Order cancellation status:  " + order.isCanceled());
            return order;
        }
    }
    @Override
    public Order placeOrder(OrderDTO orderDTO) {
        LocalDate purchaseDate = LocalDate.now();
        Order order = new Order(orderDTO.getUserId(), orderDTO.getBookId());
        Order newOrder = orderRepository.save(order);
        return newOrder;
    }
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(long orderId) {
        Optional<Order> order=orderRepository.findById(orderId);
        if(order.isPresent()){
            Order order1=orderRepository.findById(orderId).get();
            return Optional.of(order1);
        }else {
            throw new CustomException("Sorry! We can not find order id: "+orderId);
        }
    }

    public String deleteById(long orderId, long userId){
        OrderDTO orderDTO=new OrderDTO();
        Optional<Order> order=orderRepository.findById(orderId);
        User userObject = restTemplate.getForObject("http://localhost:8081/user/id/"+userId, User.class);
        Book bookObject = restTemplate.getForObject("http://localhost:8082/book/id/"+orderDTO.getBook(), Book.class);
        if((order.isPresent()) && userObject.equals(null) && bookObject.equals(null)){
            throw new CustomException("Sorry! We can not find order id, user or book id!");
        }else {
            orderRepository.deleteById(orderId);
            emailService.sendEmail(userObject.getEmail(), "Order is deleted!","Hii.... "+userObject.getFirstName()+"! \n\n Your order has been deleted successfully! Order id: "+order.get().getOrderId());
            return "Details has been deleted!";
        }
    }

    public Order updateOrderById(long orderId, OrderDTO orderDTO) {
        long quantity = orderDTO.getQuantity();
        LocalDate purchaseDate = LocalDate.now();
        Order updatedOrder = orderRepository.save(order);
        return updatedOrder;
    }
}
