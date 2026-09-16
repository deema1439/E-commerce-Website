package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    @NotEmpty(message = "Merchant Id Can't Be Empty")
    private String MerchantId;


    @NotEmpty(message = "Merchant Name Should Not Be Empty")
    @Size(min = 4,message = "Merchant Length Name Size Should Be More Than 3")
    private String MerchantName;























}
