package com.khongtrungson.shopapp.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float price;
    //trả Product về mà không muốn hiện quantity,description trong OderDetail

    private String description;


    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product",fetch = FetchType.EAGER)
    private List<ProductImage> productImages;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<Comment> comments;


}
