package org.example.tp_spring_mvc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
@Builder
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 10,min = 2)
    private String name;

    @Min(1)
    private double price;

    @Min(0)
    private int quantity;
}