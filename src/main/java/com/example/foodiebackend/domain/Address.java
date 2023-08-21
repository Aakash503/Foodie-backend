package com.example.foodiebackend.domain;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Getter
@Setter
public class Address {
    private String city="Moradabad";
    private String street="shastrinagar near mansarovar colony";
    private int pincode=244001;
}
