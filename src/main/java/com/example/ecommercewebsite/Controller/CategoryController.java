package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Api.ApiResponse;
import com.example.ecommercewebsite.Model.Category;
import com.example.ecommercewebsite.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

 private final CategoryService categoryService;


 @GetMapping("/get")
 public ResponseEntity<?>getCategory(){
     ArrayList<Category>getCategories=categoryService.getCategories();
     return ResponseEntity.status(200).body(getCategories);
 }

 @PostMapping("/add")
 public ResponseEntity<?>addCategory(@Valid @RequestBody Category category, Errors errors){
     if(errors.hasErrors()){
         String message=errors.getFieldError().getDefaultMessage();
         return ResponseEntity.status(400).body(message);
     }
     boolean addCategory=categoryService.addCategory(category);
     if(!addCategory){
         return ResponseEntity.status(400).body(new ApiResponse("You use the same Id of another Category"));
     }
     return ResponseEntity.status(200).body(new ApiResponse("Category has Been Added"));
 }

 @PutMapping("update/{id}")
 public ResponseEntity<?>updateCategory(@PathVariable String id,@RequestBody @Valid Category category,Errors errors){
     if(errors.hasErrors()){
         String message=errors.getFieldError().getDefaultMessage();
         return ResponseEntity.status(400).body(message);
     }
     boolean updateCategories=categoryService.updateCategories(id,category);

     if(!updateCategories){
         return ResponseEntity.status(400).body(new ApiResponse("Id Not Found"));
     }
     return ResponseEntity.status(200).body(new ApiResponse("Category has been updated "));

 }


 @DeleteMapping("/delete/{id}")
 public ResponseEntity<?>RemoveCategory(@PathVariable String id){
     boolean deleteCategory=categoryService.deleteCategory(id);
     if(!deleteCategory){
         return ResponseEntity.status(400).body(new ApiResponse("Id Not Found"));
     }
     return ResponseEntity.status(200).body(new ApiResponse("Category has been deleted"));
 }




























































}
