package com.example.bambi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path brandUploadDir = Paths.get("./bambi-photos");
        String brandUploadPath = brandUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/bambi-photos/**").addResourceLocations("file:/" + brandUploadPath + "/");

    }
}
