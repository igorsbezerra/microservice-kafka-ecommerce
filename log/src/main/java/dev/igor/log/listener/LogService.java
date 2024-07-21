package dev.igor.log.listener;

import com.google.gson.Gson;
import dev.igor.log.dto.CreateOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);
    private final Gson gson;

    public LogService(Gson gson) {
        this.gson = gson;
    }

    @KafkaListener(topicPattern = "ECOMMERCE.*")
    public void log(String message) {
        CreateOrderRequest request = gson.fromJson(message, CreateOrderRequest.class);
        logger.info("new stage carried out to order {}", request.orderCode());
    }
}
