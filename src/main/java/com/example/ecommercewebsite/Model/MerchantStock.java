package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "Merchant Stock Id Should Not Be Empty")
    private String MerchantStockId;


    @NotEmpty(message = "Product Id in MerchantStock should Not be Empty")
    private String ProductId;

    @NotEmpty(message ="Merchant Id in MerchantStock should Not be Empty")
    private String MerchantId;


    @Min(value = 11,message = "Stock Need To Be More Than 10 ")
    @NotNull(message = "Stock Should Not Be Empty")
    private Integer Stock;






























}
