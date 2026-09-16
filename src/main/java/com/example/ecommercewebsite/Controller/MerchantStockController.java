package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Api.ApiResponse;
import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Service.MerchantService;
import com.example.ecommercewebsite.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("ap1/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {
  private final MerchantStockService merchantStockService;

  @GetMapping("/get")
  public ResponseEntity<?>getMerchantStock(){
      ArrayList<MerchantStock> gerMerchantStocks=merchantStockService.gerMerchantStocks();
      return ResponseEntity.status(200).body(gerMerchantStocks);
  }

  @PostMapping("/add")
  public ResponseEntity<?>addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
      if(errors.hasErrors()){
          String message=errors.getFieldError().getDefaultMessage();
          return ResponseEntity.status(400).body(message);
      }
      boolean addMerchantStock=merchantStockService.addMerchantStock(merchantStock);
      if(!addMerchantStock){
          return ResponseEntity.status(400).body(new ApiResponse("Id is Already Exist Try another Id"));
      }
      return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock has Been Added"));
  }



  @PutMapping("/update/{id}")
  public ResponseEntity<?>updateMerchantStock(@PathVariable String id,@RequestBody @Valid MerchantStock merchantStock,Errors errors){
      if(errors.hasErrors()){
          String message=errors.getFieldError().getDefaultMessage();
          return ResponseEntity.status(400).body(message);
      }
      boolean updateMerchantStock=merchantStockService.updateMerchantStock(id, merchantStock);
      if(updateMerchantStock){
          return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Has Been Updated"));
      }
      return ResponseEntity.status(400).body(new ApiResponse("Id Not Found"));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?>deleteMerchantStock(@PathVariable String id){
      boolean deleteMerchantStock=merchantStockService.deleteMerchantStock(id);
      if(!deleteMerchantStock){
          return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock Id Not Found"));
      }
      return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock has been Deleted"));
  }

  @PutMapping("/updateStock/{merchantId}/{productId}/{amount}")
  public ResponseEntity<?>addStock(@PathVariable String merchantId, @PathVariable String productId, @PathVariable Integer amount ){
      Integer addStock=merchantStockService.addStock(merchantId,productId,amount);
      switch (addStock){
          case 3:
              return ResponseEntity.status(200).body(new ApiResponse("merchant has Been added the Stock"));
          case 0:
              return ResponseEntity.status(400).body(new ApiResponse("Product id Not found"));
          case 1:
              return ResponseEntity.status(400).body(new ApiResponse("Merchant Id Not found"));
          case 2:
              return ResponseEntity.status(400).body(new ApiResponse("The amount Must Be Greater than zero "));
          case 4:
              return ResponseEntity.status(400).body(new ApiResponse("Stock Not Found"));
          default:
              return ResponseEntity.status(400).body(new ApiResponse("Invalid response code"));
      }


  }

    @PostMapping("/buy/{userId}/{productId}/{merchantId}/{quantity}")
    public ResponseEntity<?> buyProducts(@PathVariable String userId, @PathVariable String productId, @PathVariable String merchantId, @PathVariable Integer quantity){
        Integer result = merchantStockService.buyProducts(productId, merchantId, userId, quantity);

        switch (result){
            case 0:
                return ResponseEntity.status(400).body(new ApiResponse("Product id Not found"));
            case 1:
                return ResponseEntity.status(400).body(new ApiResponse("Merchant Id Not found"));
            case 2:
                return ResponseEntity.status(400).body(new ApiResponse("User Id Not found"));
            case 3:
                return ResponseEntity.status(400).body(new ApiResponse("The amount Must Be Greater than zero"));
            case 4:
                return ResponseEntity.status(400).body(new ApiResponse("Stock record Not Found for this merchant and product"));
            case 5:
                return ResponseEntity.status(400).body(new ApiResponse("Not enough stock available"));
            case 6:
                return ResponseEntity.status(400).body(new ApiResponse("Insufficient balance"));
            case 7:
                return ResponseEntity.status(200).body(new ApiResponse("Purchase completed successfully"));
            default:
                return ResponseEntity.status(400).body(new ApiResponse("Invalid response code"));
        }
    }




    @GetMapping("/inventory-message/{merchantId}")
    public ResponseEntity<?> getInventoryValueMessage(@PathVariable String merchantId){

        String message=merchantStockService.getInventoryValueMessage(merchantId);

        if(message.equals("Merchant Not Found")){
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        return ResponseEntity.status(200).body(new ApiResponse(message));
    }




    @GetMapping("/products-by-merchant/{merchantName}")
    public ResponseEntity<?> getProductByMerchant(@PathVariable String merchantName){
        ArrayList<Product> products = merchantStockService.getProductByMerchant(merchantName);

        if(products == null){
            return ResponseEntity.status(400).body(new ApiResponse("Merchant Not Found"));
        }

        return ResponseEntity.status(200).body(products);
    }







}
