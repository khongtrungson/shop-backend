package com.khongtrungson.shopapp.config;

import com.khongtrungson.shopapp.entities.User;
import com.khongtrungson.shopapp.exceptions.DataNotFoundException;
import com.khongtrungson.shopapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new
                DataNotFoundException("User details not found for the user: " + phoneNumber));
        String role = user.getRole().getName();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails
                .User(user.getId().toString(), user.getPassword(),authorities);
    }
}
