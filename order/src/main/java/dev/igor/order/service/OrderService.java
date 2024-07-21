package dev.igor.order.service;

import dev.igor.order.controller.request.CreateOrderRequest;

public interface OrderService {
    void processOrder(CreateOrderRequest request);
}
