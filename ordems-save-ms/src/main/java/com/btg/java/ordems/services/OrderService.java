package com.btg.java.ordems.services;

import com.btg.java.ordems.controllers.dtos.OrderResponseDto;
import com.btg.java.ordems.entitys.OrderEntity;
import com.btg.java.ordems.entitys.OrderItem;
import com.btg.java.ordems.listeners.dtos.OrderCreatedEventDto;
import com.btg.java.ordems.repositorys.OrderRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {


    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void saveOrder(OrderCreatedEventDto order) {

        var orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.orderId());
        orderEntity.setCustomerId(order.codigoCliente());
        orderEntity.setTotal(getTotal(order));
        orderEntity.setItems(getOrderItems(order));

        logger.info("The entity has been persisted: {}", orderEntity);
        orderRepository.save(orderEntity);
    }

    public Page<OrderResponseDto> findAllByCustomerId(Long customerId, PageRequest pageRequest) {

        var pageResponse = orderRepository.findAllByCustomerId(customerId, pageRequest);

        logger.info("Return of entity: {}", pageResponse.getContent());

        return pageResponse.map(OrderResponseDto::convertEntity);
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total"));

        var response = mongoTemplate.aggregate(aggregations, "tb_orders", Document.class);

        return new BigDecimal(String.valueOf(response.getUniqueMappedResult().getOrDefault("total", BigDecimal.ZERO)));
    }

    private BigDecimal getTotal(OrderCreatedEventDto order) {
        return order.items().stream()
                .map(i -> i.price().multiply(BigDecimal.valueOf(i.quantity())))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEventDto order) {
        return order.items().stream()
                .map(e ->
                        new OrderItem(
                                e.item(),
                                e.quantity(),
                                e.price())).toList();
    }

}
