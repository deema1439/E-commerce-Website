package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "Product Id should Not be Empty")
    private String ProductId;

    @NotEmpty(message = "Product Name Should Not Be Empty")
    @Size(min = 4,message = "Product Length Name Size Should Be More Than 3")
    private String ProductName;

    @NotNull(message = "Price Should Not Be Empty ")
    private Double ProductPrice;

    @NotEmpty(message = "Category Id In Product Should Not Be Empty")
    private String CategoryId;















}
