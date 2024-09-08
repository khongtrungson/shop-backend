package com.khongtrungson.shopapp.repositories;

import com.khongtrungson.shopapp.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {

}
