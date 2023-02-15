package com.example.bambi.controller;

import com.example.bambi.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @GetMapping("/create")
    public String createProductForm() {
        return "create_product";
    }

}
