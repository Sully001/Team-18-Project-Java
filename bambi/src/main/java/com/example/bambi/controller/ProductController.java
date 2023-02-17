package com.example.bambi.controller;

import com.example.bambi.entity.Product;
import com.example.bambi.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    //GET request to get a list of all the product currently in the database
    @GetMapping("/products")
    public String listAllProducts(Model model, @Param("keyword") String keyword) {
        List<Product> products = productService.getAllProducts(keyword);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "products";
    }

    //GET request to retrieve the Add Product Form
    @GetMapping("/products/new")
    public String addProductForm() {
        return "add_product";
    }

    //POST request to create a New Product entry
    @PostMapping("/products/new")
    public String addProductForm(@RequestParam("product_brand") String brand,
                                 @RequestParam("product_name") String name,
                                 @RequestParam("product_price") int price,
                                 @RequestParam("product_gender") String gender,
                                 @RequestParam("product_category") String category,
                                 @RequestParam("product_description") String description,
                                 @RequestParam("product_image")MultipartFile image) throws IOException {
        Product product = new Product();
        product.setProductBrand(brand);
        product.setProductName(name);
        product.setProductPrice(price);
        product.setProductGender(gender);
        product.setProductCategory(category);
        product.setProductDescription(description);

        String filename = image.getOriginalFilename();
        product.setProductImage(filename);

        productService.saveProduct(product);
        saveImageToFolder(image);
        return "redirect:/products";

    }

    //GETs the existing product and produces the data in relevant form fields
    @GetMapping("/product/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "edit_product";
    }

    @PostMapping("/product/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam String productBrand,
                                @RequestParam String productName,
                                @RequestParam int productPrice,
                                @RequestParam String productGender,
                                @RequestParam String productCategory,
                                @RequestParam String productDescription,
                                @RequestParam MultipartFile productImage) throws IOException {

        //Get existing product record
        Product product = productService.getProductById(id);
        product.setId(id);
        product.setProductBrand(productBrand);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductGender(productGender);
        product.setProductCategory(productCategory);
        product.setProductDescription(productDescription);

        //If a file has been uploaded delete the old image
        if (productImage.getOriginalFilename() != "") {
            deleteImage(product.getProductImage());
            saveImageToFolder(productImage);

            //Set existing products new image
            product.setProductImage(productImage.getOriginalFilename());
        } else {
            System.out.println("No File Available");
        }

        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        deleteImage(product.getProductImage());
        productService.deleteProductById(id);
        return "redirect:/products";
    }


    //Saves Image To Folder "./bambi-photos/"
    private void saveImageToFolder(MultipartFile image) throws IOException {
        String filename = image.getOriginalFilename();

        String uploadDir = "./bambi-photos/";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = image.getInputStream()) {
            Path filePath = uploadPath.resolve(filename);
            System.out.println(filePath.toFile().getAbsolutePath());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file " + filename);
        }
    }

    //Param - takes image name
    //Deletes image if it exists
    private void deleteImage(String filename) {
        File image = new File("./bambi-photos/" + filename);

        if(image.exists()) {
            image.delete();
        }
    }
}
