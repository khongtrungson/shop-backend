package com.khongtrungson.shopapp.mappers;

import com.khongtrungson.shopapp.dtos.requests.CartItemDTO;
import com.khongtrungson.shopapp.entities.Order;
import com.khongtrungson.shopapp.entities.OrderDetail;
import com.khongtrungson.shopapp.entities.Product;

public class OrderDetailMapper {
    public static void save(OrderDetail orderDetail, Order order, Product product, CartItemDTO card) {
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setNumberOfProducts(card.getProductId());
        orderDetail.setTotalMoney(card.getQuantity()* product.getPrice());
    }
}
