package com.khongtrungson.shopapp.repositories;


import com.khongtrungson.shopapp.entities.Order;
import com.khongtrungson.shopapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
     Optional<List<Order>> findOrderByUser(User user);


}
