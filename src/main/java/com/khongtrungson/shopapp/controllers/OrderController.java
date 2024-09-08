package com.khongtrungson.shopapp.controllers;

import com.khongtrungson.shopapp.dtos.requests.OrderDTO;
import com.khongtrungson.shopapp.dtos.responses.OrderResponse;
import com.khongtrungson.shopapp.dtos.responses.ResponseData;
import com.khongtrungson.shopapp.entities.Order;
import com.khongtrungson.shopapp.services.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.List;
@Tag(
        name = "CRUD APIs for Order"
)
@RestController
@RequestMapping(value = "/order",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final IOrderService orderService;
    @Operation(summary = "create order api")
    @PostMapping()
    public ResponseData<String> createOrder(
            @RequestBody @Valid OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
        return ResponseData.<String>builder()
                .data("create order successfully")
                .message("create order action")
                .status(HttpStatus.CREATED.value())
                .build();
    }
    @Operation(summary = "get an order by id api")
    @GetMapping("/{id}")
    public ResponseData<OrderResponse> getOrder(
            @PathVariable @Positive Long id){
       var order =  orderService.getOrder(id);
       return ResponseData.<OrderResponse>builder()
               .data(order)
               .message("order with id: " +id)
               .status(HttpStatus.CREATED.value())
               .build();
    }

    @Operation(summary = "delete an order by id api")
    @DeleteMapping("/{id}")
    public ResponseData<String> deleteOrder(@PathVariable @Positive Long id){
        orderService.deleteOrder(id);
        return ResponseData.<String>builder()
                .data("delete order successfully")
                .message("delete order action")
                .status(HttpStatus.CREATED.value())
                .build();
    }
    @Operation(summary = "get all orders of student has id ? api")
    @GetMapping("/allof/user/{userId}")
    public ResponseData<List<OrderResponse>> getOrders(@PathVariable @Positive Long userId){
        var orders = orderService.getOrderby(userId);
        return ResponseData.<List<OrderResponse>>builder()
                .data(orders)
                .message("the orders of user has id: "+userId)
                .status(HttpStatus.CREATED.value())
                .build();
    }


}
