package com.bridgelabz.bookstore_order.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class OrderDTO {
    private Long userId;
    private Long bookId;
    private int quantity;
    private double price;
    private String address;
    private boolean isCanceled;

    public String getBook() {
        return null;
    }

    public String getUser() {
        return null;
    }
}
