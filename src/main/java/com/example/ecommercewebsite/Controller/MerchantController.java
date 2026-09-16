package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Api.ApiResponse;
import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity<?>getMerchants(){
        ArrayList<Merchant>merchants=merchantService.getMerchants();
        return ResponseEntity.status(200).body(merchants);
    }

    @PostMapping("/add")
    public ResponseEntity<?>addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean addMerchants=merchantService.addMerchants(merchant);
        if(!addMerchants){
            return ResponseEntity.status(400).body("This Id Is Already Exist,Change it");
        }
        return ResponseEntity.status(200).body(new ApiResponse("merchant is added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateMerchant(@PathVariable String id,@Valid @RequestBody Merchant merchant,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updateMerchants=merchantService.updateMerchants(id, merchant);
        if(!updateMerchants){
            return ResponseEntity.status(400).body(new ApiResponse("Id Not Found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchants Has Been Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteMerchant(@PathVariable String id){
        boolean deleteMerchants=merchantService.deleteMerchants(id);
        if(!deleteMerchants){
            return ResponseEntity.status(400).body(new ApiResponse("Id Not Found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Has Been Delete it"));
    }






















}
