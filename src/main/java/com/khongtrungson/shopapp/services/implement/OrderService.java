package com.khongtrungson.shopapp.services.implement;
import com.khongtrungson.shopapp.dtos.requests.CartItemDTO;
import com.khongtrungson.shopapp.dtos.requests.OrderDTO;
import com.khongtrungson.shopapp.dtos.responses.OrderResponse;
import com.khongtrungson.shopapp.entities.Order;
import com.khongtrungson.shopapp.entities.OrderDetail;
import com.khongtrungson.shopapp.entities.Product;
import com.khongtrungson.shopapp.entities.User;
import com.khongtrungson.shopapp.exceptions.DataNotFoundException;
import com.khongtrungson.shopapp.exceptions.InvalidParamException;
import com.khongtrungson.shopapp.mappers.OrderDetailMapper;
import com.khongtrungson.shopapp.mappers.OrderMapper;
import com.khongtrungson.shopapp.repositories.OrderDetailRepository;
import com.khongtrungson.shopapp.repositories.OrderRepository;
import com.khongtrungson.shopapp.repositories.ProductRepository;
import com.khongtrungson.shopapp.repositories.UserRepository;
import com.khongtrungson.shopapp.services.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        var listCard = orderDTO.getCartItems();
        List<OrderDetail> orderDetails = new ArrayList<>();
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(()-> new DataNotFoundException("user not found"));
        Order order = new Order();
        orderRepository.save(order);


       for(CartItemDTO card : listCard){
           Product product = productRepository.findById(card.getProductId()).
                   orElseThrow(()->new DataNotFoundException("product not found"));
           if(card.getQuantity() > product.getQuantity()){
               throw new InvalidParamException("quantity must be less than product'number");
           }
           product.setQuantity(product.getQuantity()- card.getQuantity());
           OrderDetail orderDetail = new OrderDetail();
           OrderDetailMapper.save(orderDetail,order,product,card);
           orderDetailRepository.save(orderDetail);
           orderDetails.add(orderDetail);
       }


        OrderMapper.save(order,user,orderDetails,orderDTO);
    }

    @Override
    public OrderResponse getOrder(Long id) {
        var order = orderRepository.findById(id).orElseThrow(()->new DataNotFoundException("order not exist"));
        OrderResponse orderResponse = OrderMapper.entityToResponse(order);
        return orderResponse;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponse> getOrderby(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new DataNotFoundException("user is not exist"));
        var orderResponses = orderRepository.findOrderByUser(user).
                orElseThrow(()->new DataNotFoundException("this user did not make orders"));
        return orderResponses.stream().map(order -> OrderMapper.entityToResponse(order)).collect(Collectors.toList());
    }
}
