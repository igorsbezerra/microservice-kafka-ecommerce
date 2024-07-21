package dev.igor.log.dto;

import java.util.List;

public record CreateOrderRequest(
        String orderCode,
        List<CreateOrderItem> items
) {
}
