package dev.igor.fraud.dto;

import java.util.List;

public record CreateOrderRequest(
        String orderCode,
        List<CreateOrderItem> items
) {
}
