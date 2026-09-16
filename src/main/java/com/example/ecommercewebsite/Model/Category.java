package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = "Category Id Should Not Be Empty")
    private String CategoryId;

    @NotEmpty(message = "The Category Name Should Not Be Empty")
    @Size(min = 4,message = "Category Length Name Size Should Be More Than 3")
    private String CategoryName;























}
