package dev.igor.order.service.impl;

import com.google.gson.Gson;
import dev.igor.order.controller.request.CreateOrderRequest;
import dev.igor.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public OrderServiceImpl(KafkaTemplate<String, String> kafkaTemplate, Gson gson) {
        this.kafkaTemplate = kafkaTemplate;
        this.gson = gson;
    }

    @Override
    public void processOrder(CreateOrderRequest request) {
        logger.info("Create new order {}", request.toString());
        kafkaTemplate.send("ECOMMERCE_NEW_ORDER", UUID.randomUUID().toString(), gson.toJson(request));
        kafkaTemplate.send("ECOMMERCE_SEND_EMAIL", UUID.randomUUID().toString(), gson.toJson(request));
    }
}
