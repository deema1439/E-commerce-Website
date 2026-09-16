package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Api.ApiResponse;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

 private final ProductService productService;

 @GetMapping("/get")
 public ResponseEntity<?>getProduct(){
     ArrayList<Product> getProducts=productService.getProducts();
     return ResponseEntity.status(200).body(getProducts);
 }


 @PostMapping("/add")
 public ResponseEntity<?>addProduct(@Valid @RequestBody Product product, Errors errors){
     if(errors.hasErrors()){
         String message=errors.getFieldError().getDefaultMessage();
         return ResponseEntity.status(400).body(message);
     }
     boolean updateProduct=productService.addProduct(product);
     if(!updateProduct){
         return ResponseEntity.status(400).body(new ApiResponse("Id Not Found"));
     }
     return ResponseEntity.status(200).body(new ApiResponse("Product has been Added"));
 }

 @PutMapping("/update/{id}")
 public ResponseEntity<?>updateProduct(@PathVariable String id,@Valid @RequestBody Product product,Errors errors){
     if(errors.hasErrors()){
         String message=errors.getFieldError().getDefaultMessage();
         return ResponseEntity.status(400).body(message);
     }
     boolean updateProduct=productService.updateProduct(id,product);
     if(!updateProduct){
         return ResponseEntity.status(400).body(new ApiResponse("Id Not Found"));
     }

     return ResponseEntity.status(200).body(new ApiResponse("product Has Been Updated"));
 }


 @DeleteMapping("/delete/{id}")
 public ResponseEntity<?>deleteProduct(@PathVariable String id){
     boolean deleteProduct=productService.deleteProduct(id);
     if(!deleteProduct){
         return ResponseEntity.status(400).body(new ApiResponse("Id Not Found"));
     }
     return ResponseEntity.status(200).body(new ApiResponse("Product has Been Delete it"));
 }




    @GetMapping("/newReleases/{count}/{categoryId}")
    public ResponseEntity<?> getNewReleases(@PathVariable int count, @PathVariable String categoryId){

        if(count <= 0){
            return ResponseEntity.status(400).body(new ApiResponse("Count must be greater than zero"));
        }

        ArrayList<Product> newReleases = productService.getNewReleases(count, categoryId);

        if(newReleases == null){
            return ResponseEntity.status(400).body(new ApiResponse("Category Not Found"));
        }

        return ResponseEntity.status(200).body(newReleases);
    }




    @GetMapping("/related/{productId}/{priceDirection}")
    public ResponseEntity<?> getRelatedProductsByHigherOrLower(@PathVariable String productId
            , @PathVariable String priceDirection){
       ArrayList<Product> relatedProducts = productService.getRelatedProductsByHigherOrLower(
               productId, priceDirection);
       if(relatedProducts == null){
            return ResponseEntity.status(400).body(new ApiResponse(
                    "Invalid input. Please enter 'higher' or 'lower' for price direction, " +
                            "and make sure the Product Id exists"
            ));
        }
       return ResponseEntity.status(200).body(relatedProducts);
    }




    @GetMapping("/cheapest/{categoryId}")
    public ResponseEntity<?> cheapestProduct(@PathVariable String categoryId){

        Product cheapest = productService.cheapestProduct(categoryId);

        if(cheapest == null){
            return ResponseEntity.status(400).body(new ApiResponse("Category Not Found or" +
                    " No Products in This Category"));
        }

        return ResponseEntity.status(200).body(cheapest);
    }
































}
