package com.example.bambi.controller;

import com.example.bambi.entity.Product;
import com.example.bambi.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    //handler methods
    @GetMapping("/products")
    public String listAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/products/new")
    public String addProductForm() {
        return "add_product";
    }

    @PostMapping("/products/new")
    public String addProductForm(@RequestParam("product_name") String name,
                               @RequestParam("product_price") int price,
                               @RequestParam("product_gender") String gender,
                               @RequestParam("product_category") String category,
                               @RequestParam("product_description") String description,
                               @RequestParam("product_image") String image) {
        Product product = new Product();
        product.setProductName(name);
        product.setProductPrice(price);
        product.setProductGender(gender);
        product.setProductCategory(category);
        product.setProductDescription(description);
        product.setProductImage(image);

        productService.saveProduct(product);
        return "redirect:/products";

    }
}
