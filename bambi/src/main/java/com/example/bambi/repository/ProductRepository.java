package com.example.bambi.repository;

import com.example.bambi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //Query matching name, brand, price, gender and category against SQL db
    @Query("SELECT products FROM Product products WHERE products.productName LIKE %?1% " +
            "or products.productBrand LIKE %?1% or CAST(products.productPrice as string) LIKE %?1% or " +
            "products.productGender LIKE %?1% or products.productCategory LIKE %?1%")
    List<Product> search(String keyword);
}