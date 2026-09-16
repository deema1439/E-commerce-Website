package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Api.ApiResponse;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Model.User;
import com.example.ecommercewebsite.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?>getUser(){
        ArrayList<User> getUsers=userService.getUsers();
        return ResponseEntity.status(200).body(getUsers);
    }

    @PostMapping("/add")
    public ResponseEntity<?>addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean addUsers=userService.addUsers(user);
        if(!addUsers){
            return ResponseEntity.status(400).body(new ApiResponse("Id is Already Exist Try Another Id"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("User Has Been Added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateUser(@PathVariable String id,@RequestBody @Valid User user,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updateUsers=userService.updateUsers(id, user);
        if(!updateUsers){
            return ResponseEntity.status(400).body(new ApiResponse("User Id Not Found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("User Has Been Updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable String id){
        boolean deleteUser=userService.deleteUser(id);
        if(!deleteUser){
            return ResponseEntity.status(400).body(new ApiResponse("User Id Not Found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("User has Been deleted"));
    }


    @PutMapping("/addBalance/{userId}/{balance}")
     public ResponseEntity<?>addBalanceToUser(@PathVariable String userId,
                                              @PathVariable Double balance){
        Integer addBalanceToUser=userService.addBalanceToUser(userId,balance);
        switch (addBalanceToUser){
            case 1:
                return ResponseEntity.status(400).body(
                        new ApiResponse("Amount Should Be greater than zero"));
            case 2:
                return ResponseEntity.status(400).body(
                        new ApiResponse("Id User Not Found"));
            case 3:
                return ResponseEntity.status(200).body(new ApiResponse(
                        "User amount Has Been Added To User Balance"));
            case 4:
                return ResponseEntity.status(400).body(new ApiResponse(
                        "User amount should be less than 10000"));
            default:
                return ResponseEntity.status(400).body(new ApiResponse("Error"));
        }
     }

















}
