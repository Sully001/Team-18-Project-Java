package com.example.bambi;

import com.example.bambi.entity.Product;
import com.example.bambi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BambiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BambiApplication.class, args);
    }

}
