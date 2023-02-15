package com.example.bambi.entity;


import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price")
    private int productPrice;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_gender")
    private String productGender;

    @Column(name = "product_category")
    private String productCategory;


    //Constructors
    public Product(String productName, int productPrice, String productImage, String productDescription, String productGender, String productCategory) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productGender = productGender;
        this.productCategory = productCategory;
    }

    public Product() {}


    //All Setters

    public void setProductId(Long id) {
        this.productId = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductGender(String productGender) {
        this.productGender = productGender;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    //All Getters

    public Long getProductId() { return productId; }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductGender() {
        return productGender;
    }

    public String getProductCategory() {
        return productCategory;
    }



}
