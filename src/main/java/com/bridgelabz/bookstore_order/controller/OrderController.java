package com.bridgelabz.bookstore_order.controller;

import com.bridgelabz.bookstore_order.dto.OrderDTO;
import com.bridgelabz.bookstore_order.service.OrderService;
import com.bridgelabz.bookstore_order.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")

public class OrderController {
    @Autowired
    OrderService orderService;
    private long userId;

    @PostMapping("/placeorder")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestHeader(name = "Authorization")  @RequestBody OrderDTO orderDTO){
        ResponseDTO responseDTO = new ResponseDTO("Order Placed Successfully", orderService.placeOrder(orderDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @PostMapping("/placeOrderByCart/{cartId}")
    public ResponseEntity<ResponseDTO> placeOrderByCart(@RequestHeader(name = "Authorization")  @RequestParam OrderDTO cartId){
        ResponseDTO responseDTO = new ResponseDTO("Order Placed Successfully", orderService.placeOrderByCart(cartId));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllOrders(@RequestHeader(name = "Authorization") String token){
        ResponseDTO responseDTO = new ResponseDTO("GET Call Success", orderService.getAllOrders().toString());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/get/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderById(@RequestHeader(name = "Authorization") String token,@PathVariable long orderId){
        ResponseDTO responseDTO = new ResponseDTO("GET Call Success", orderService.getOrderById(orderId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}/{userId}")
    public ResponseEntity<ResponseDTO> deleteOrderById(@RequestHeader(name = "Authorization") String token,@PathVariable long orderId){
        ResponseDTO responseDTO = new ResponseDTO("GET Call Success", orderService.deleteById(orderId, userId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<ResponseDTO> updateOrderById(@RequestHeader(name = "Authorization") String token,@PathVariable long orderId, @RequestBody OrderDTO orderDTO){
        ResponseDTO responseDTO = new ResponseDTO("GET Call Success", orderService.updateOrderById(orderId,orderDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
