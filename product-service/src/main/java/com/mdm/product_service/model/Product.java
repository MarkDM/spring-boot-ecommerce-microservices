package com.mdm.product_service.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Customize equals and hashCode if needed
@ToString
//@Data // Uncomment if you want to use @Data instead of individual annotations
public class Product {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private String description;
    private double price;
}
