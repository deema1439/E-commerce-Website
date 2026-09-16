package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {


    @NotEmpty(message = "User Id should Not be Empty")
    private String UserId;

    @NotEmpty(message = "Username Should Not Be Empty")
    @Size(min=6,message = "User Name Length Should Be More Than 5 ")
    private String userName;



    @NotEmpty(message = "User Password Should Not Be Empty")
    @Size(min = 7,message = "The Password Length Should be more than 6")
    @Pattern(regexp = "^[a-zA-Z0-9]+$",message = "Only Digits and Characters")
    private String password;

    @NotEmpty(message = "Should Not Be Empty")
    @Email(message = "Invalid Email")
    private String email;

    @NotEmpty(message = "User Role Should Not Be Empty")
    @Pattern(regexp = "^(Admin|Customer)$",flags = Pattern.Flag.CASE_INSENSITIVE,
    message = "User Role Should Be Only An Admin Or Customer")
    private String role;

    @NotNull(message = "Balance Should Not Be Empty")
    private Double Balance;










































}
