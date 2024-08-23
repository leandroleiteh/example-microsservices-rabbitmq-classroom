package com.btg.java.ordems.controllers.dtos;

import com.btg.java.ordems.entitys.OrderEntity;

import java.math.BigDecimal;

public record OrderResponseDto(Long orderId,
                               Long customerId,
                               BigDecimal total) {
    public static OrderResponseDto convertEntity(OrderEntity orderEntity) {
        return new OrderResponseDto(orderEntity.getOrderId(), orderEntity.getCustomerId(), orderEntity.getTotal());
    }
}
