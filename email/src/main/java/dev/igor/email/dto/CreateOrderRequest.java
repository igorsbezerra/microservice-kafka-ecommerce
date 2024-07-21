package dev.igor.email.dto;

import java.util.List;

public record CreateOrderRequest(
        String orderCode,
        List<CreateOrderItem> items
) {
}
