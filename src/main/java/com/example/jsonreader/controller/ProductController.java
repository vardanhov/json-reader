package com.example.jsonreader.controller;

import com.example.jsonreader.domain.Product;
import com.example.jsonreader.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;


@Controller
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name = "sort", required = false, defaultValue = "id") String sort,
                           @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
                           @RequestParam(name = "category", required = false) String category,
                           Model model) {
        List<Product> productList = null;
        try {
            productList = productService.getAllProducts(sort, order, category);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("productList", productList);
        return "products";
    }
}
