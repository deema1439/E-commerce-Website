package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

  ArrayList<User>users=new ArrayList<>();

  public ArrayList<User>getUsers(){
      return users;
  }

  public boolean addUsers(User user){
      for(User u:users){
          if(u.getUserId().equals(user.getUserId())){
              return false;
          }
      }
      users.add(user);
      return true;
  }

  public boolean updateUsers(String id, User user){
      for(int i =0;i<users.size();i++){
          if(users.get(i).getUserId().equals(id)){
              users.set(i,user);
              return true;
          }
      }
      return false;
  }

  public boolean deleteUser(String id){
      for(int i = 0 ; i<users.size();i++){
          if(users.get(i).getUserId().equals(id)){
              users.remove(i);
              return true;
          }
      }
      return false;
  }



  public boolean checkUserId(String id){
      for(User u:users){
          if(u.getUserId().equals(id)){
              return true;
          }
      }
      return false;
  }


  public Integer addBalanceToUser(String userId,Double amount){

      for(User u:users){
          if (u.getUserId().equals(userId)) {
              if (amount <= 0) {
                  return 1;
              }
              if(amount>10000){
                  return 4;
              }
              u.setBalance(u.getBalance() + amount);
              return 3;
          }
      }
      return 2;
  }


































}
