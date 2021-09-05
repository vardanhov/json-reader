package com.example.jsonreader.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Product {

    private long id;

    private String name;

    private Category category;

    private String date;

    private BigDecimal price;

}
