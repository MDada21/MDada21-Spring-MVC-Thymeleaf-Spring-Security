package org.example.tp_spring_mvc;

import org.example.tp_spring_mvc.entities.Product;
import org.example.tp_spring_mvc.repository.ProductRepository;
import org.example.tp_spring_mvc.security.SecurityConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class TpSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpSpringMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(null, "PC", 9000, 10));
            productRepository.save(new Product(null, "Phone", 5000, 5));

            productRepository.findAll().forEach(p -> {
                System.out.println(p.getName());
            });
        };
    }

}
