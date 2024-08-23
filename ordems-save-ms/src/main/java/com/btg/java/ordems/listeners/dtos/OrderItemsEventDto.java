package com.btg.java.ordems.listeners.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.math.BigDecimal;

public record OrderItemsEventDto(
        @JsonAlias("produto")String item,
        @JsonAlias("quantidade")Integer quantity,
        @JsonAlias("preco") BigDecimal price) {
}
