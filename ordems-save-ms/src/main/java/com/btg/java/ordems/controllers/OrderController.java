package com.btg.java.ordems.controllers;

import com.btg.java.ordems.controllers.dtos.ApiResponseDto;
import com.btg.java.ordems.controllers.dtos.OrderResponseDto;
import com.btg.java.ordems.controllers.dtos.PaginationResponseDto;
import com.btg.java.ordems.services.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<ApiResponseDto<OrderResponseDto>> findOrders(@PathVariable("customerId") Long customerId,
                                                                       @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {


       var orders =  orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);

        return ResponseEntity.ok(new ApiResponseDto<>(Map.of("totalOnOrders", totalOnOrders),
                orders.getContent(),
                PaginationResponseDto.convertPaganation(orders)
                ));
    }
}
