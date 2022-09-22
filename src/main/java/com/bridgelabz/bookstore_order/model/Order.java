package com.bridgelabz.bookstore_order.model;

import com.bridgelabz.bookstore_order.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", nullable = false)
    private Long orderId;
    private long userId;
    private String address;

    private long bookId;
    private long quantities;
    private double price;
    private LocalDate purchaseDate;
    private boolean isCanceled;

    public Order(long userId, long bookId, String address, long quantities, double price, LocalDate purchaseDate, boolean isCanceled) {
        this.address = address;
        this.bookId = bookId;
        this.userId = userId;
        this.quantities = quantities;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.isCanceled = isCanceled;
    }

    public Order(Long userId, Long bookId) {
    }

    public Order(OrderDTO orderDTO) {
    }

    public String getDate() {
        return null;
    }

    public String getBook() {
        return null;
    }

    public String getQuantity() {
        return null;
    }

    public String getUser() {
        return null;
    }
}
