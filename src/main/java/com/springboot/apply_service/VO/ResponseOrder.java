package com.springboot.apply_service.VO;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createdAt;

    private String orderId;
}
