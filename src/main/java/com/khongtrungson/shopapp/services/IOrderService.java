package com.khongtrungson.shopapp.services;

import com.khongtrungson.shopapp.dtos.requests.OrderDTO;
import com.khongtrungson.shopapp.dtos.responses.OrderResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderDTO orderDTO);

    OrderResponse getOrder(Long id);

    void deleteOrder(Long id);

    List<OrderResponse> getOrderby(Long userId);
}
