package dev.igor.fraud.listener;

import com.google.gson.Gson;
import dev.igor.fraud.dto.CreateOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FraudService {
    private static final Logger logger = LoggerFactory.getLogger(FraudService.class);
    private final Gson gson;

    public FraudService(Gson gson) {
        this.gson = gson;
    }

    @KafkaListener(topics = "ECOMMERCE_NEW_ORDER")
    public void execute(String message) {
        CreateOrderRequest request = gson.fromJson(message, CreateOrderRequest.class);
        logger.info("performing fraud analysis for order {}", request.orderCode());
    }
}
