package com.khongtrungson.shopapp.mappers;

import com.khongtrungson.shopapp.dtos.requests.OrderDTO;
import com.khongtrungson.shopapp.dtos.responses.OrderResponse;
import com.khongtrungson.shopapp.entities.Order;
import com.khongtrungson.shopapp.entities.OrderDetail;
import com.khongtrungson.shopapp.entities.User;

import java.util.Date;
import java.util.List;

public class OrderMapper {
    public static void save(Order order, User user, List<OrderDetail> orderDetails,OrderDTO orderDTO){
        order.setOrderDate(new Date());
        order.setOrderDetails(orderDetails);
        order.setNote(orderDTO.getNote());
        order.setActive(true);
        order.setStatus("PENDING");
        order.setUser(user);
        order.setPaymentMethod("offline");
        order.setShippingDate(null);
        double totalMoney = 0;
        for (OrderDetail orderDetail: orderDetails) {
            totalMoney += orderDetail.getTotalMoney();
        }
        order.setTotalMoney(totalMoney);
        order.setTrackingNumber("tracking number");
    }

    public static OrderResponse entityToResponse(Order order) {
        var orderResponse = new OrderResponse();
        orderResponse.setActive(order.isActive());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setOrderDetails(order.getOrderDetails());
        orderResponse.setNote(order.getNote());
        orderResponse.setId(order.getId());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setShippingDate(order.getShippingDate());
        orderResponse.setShippingMethod(order.getShippingMethod());
        orderResponse.setTotalMoney(order.getTotalMoney());
        return orderResponse;
    }
}
