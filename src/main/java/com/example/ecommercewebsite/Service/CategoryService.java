package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CategoryService {

    ArrayList<Category>categories=new ArrayList<>();

    public ArrayList<Category>getCategories(){
        return categories;
    }

    public boolean addCategory(Category category){
      for(Category c:categories){
          if(c.getCategoryId().equals(category.getCategoryId())){
            return false;// ال id متكرر ما ينفع
          }
      }
      categories.add(category);
      return true;
    }

    public boolean updateCategories(String id,Category category){
        for(int i=0;i<categories.size();i++){
            if(categories.get(i).getCategoryId().equals(id)){
                categories.set(i,category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(String id){
        for(int i=0;i<categories.size();i++){
            if(categories.get(i).getCategoryId().equals(id)){
              categories.remove(i);
              return true;
            }
        }
        return false;
    }



    public boolean checkCategoryId(String categoryId ){
        for(Category c:categories){
            if(c.getCategoryId().equals(categoryId)){
                return true;
            }
        }
        return false;

    }

















}
