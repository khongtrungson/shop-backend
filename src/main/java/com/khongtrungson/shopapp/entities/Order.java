package com.khongtrungson.shopapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;
    private Date orderDate;

    private String status;

    private Double totalMoney;

    private String shippingMethod;

    private LocalDate shippingDate;

    private String trackingNumber;

    private String paymentMethod;

    private boolean active;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order",fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
