package com.btg.java.ordems.listeners.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record OrderCreatedEventDto(
        @JsonAlias("codigoPedido")Long orderId,
        @JsonAlias("customerId")Long codigoCliente,
        @JsonAlias("itens")List<OrderItemsEventDto> items){
}
