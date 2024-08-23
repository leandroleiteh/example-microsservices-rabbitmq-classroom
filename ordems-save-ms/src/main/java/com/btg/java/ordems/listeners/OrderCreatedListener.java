package com.btg.java.ordems.listeners;

import com.btg.java.ordems.listeners.dtos.OrderCreatedEventDto;
import com.btg.java.ordems.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.btg.java.ordems.configs.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService){
        this.orderService = orderService;
    }

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(@Payload Message<OrderCreatedEventDto> message) {
        logger.info("Message consumed: {}", message);

        orderService.saveOrder(message.getPayload());
    }
}