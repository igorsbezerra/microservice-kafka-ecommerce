package dev.igor.email.listener;

import com.google.gson.Gson;
import dev.igor.email.dto.CreateOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderListener {
    private static final Logger logger = LoggerFactory.getLogger(CreateOrderListener.class);
    private final Gson gson;

    public CreateOrderListener(Gson gson) {
        this.gson = gson;
    }

    @KafkaListener(topics = "ECOMMERCE_SEND_EMAIL")
    public void sendEmailNewOrder(String message) {
        CreateOrderRequest r = gson.fromJson(message, CreateOrderRequest.class);
        double total = r.items().stream()
                .mapToDouble(i -> Double.parseDouble(i.amount()) * Double.parseDouble(i.value()))
                .reduce(0, Double::sum);
        logger.info("Send email - Order [{}] - total order [{}]", r.orderCode(), total);
    }
}
