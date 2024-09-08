package com.khongtrungson.shopapp.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khongtrungson.shopapp.entities.OrderDetail;
import com.khongtrungson.shopapp.entities.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {

    private Long id;

    private String note;
    @JsonProperty("order_date")
    private Date orderDate;

    private String status;
    @JsonProperty("total_money")
    private Double totalMoney;
    @JsonProperty("shipping_method")
    private String shippingMethod;
    @JsonProperty("shipping_date")
    private LocalDate shippingDate;
    @JsonProperty("payment_method")
    private String paymentMethod;

    private boolean active;

    private List<OrderDetail> orderDetails;
}
