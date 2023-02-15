package com.example.bambi.service;

import com.example.bambi.entity.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
}
