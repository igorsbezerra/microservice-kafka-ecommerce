package dev.igor.order.controller.request;

import java.util.List;

public record CreateOrderRequest(
        String orderCode,
        List<CreateOrderItem> items
) {
}
