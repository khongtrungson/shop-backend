package com.khongtrungson.shopapp.repositories;


import com.khongtrungson.shopapp.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
     Optional<User> findUserByPhoneNumber(String phoneNumber);

     boolean existsByPhoneNumber(String phoneNumber);
}
