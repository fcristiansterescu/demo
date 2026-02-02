package com.example.demo.entity;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;

}
