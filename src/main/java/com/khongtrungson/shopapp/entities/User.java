package com.khongtrungson.shopapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
//implements UserDetails
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String type;
    private String address;
    private String password;
    private boolean isActive;
    private Date dateOfBirth;


    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Comment> comments ;

}
