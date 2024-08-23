package com.btg.java.newordemscreated.configs;

import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabitMqConfig {

        @Value("${spring.rabbitmq.addressess}")
        private String rabbitMqAddresses;

        public static final String STUDENT_CREATED_QUEUE = "ms-recebe-pedido";


        @Bean
        public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
            return new Jackson2JsonMessageConverter();
        }

        @Bean
        public Declarable orderCreatedQueue() {
            return new Queue(STUDENT_CREATED_QUEUE, true);
        }

        @Bean
        public CachingConnectionFactory connectionFactory() {
            CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
            connectionFactory.setUri(rabbitMqAddresses);
            return connectionFactory;
        }

        @Bean
        public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
            return new RabbitAdmin(connectionFactory);
        }

        @Bean
        public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(
                RabbitAdmin rabbitAdmin) {
            return event -> rabbitAdmin.initialize();
        }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }


    }

