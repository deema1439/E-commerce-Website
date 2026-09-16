package com.example.ecommercewebsite.Service;
import com.example.ecommercewebsite.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class ProductService{






 ArrayList<Product> products = new ArrayList<>();


    public ArrayList<Product>getProducts(){
        return products;
    }

    public boolean addProduct(Product product){

       for(Product p:products){
           if(p.getProductId().equals(product.getProductId())){
               return false;
           }
       }
       products.add(product);
       return true;

    }

    public boolean updateProduct(String id,Product product){
        for(int i=0;i<products.size();i++){
            if(products.get(i).getProductId().equals(id)){
                products.set(i,product);
                return true;
            }
        }
        return false;
    }


    public boolean deleteProduct(String id){
        for(int i=0;i<products.size();i++){
            if(products.get(i).getProductId().equals(id)){
                products.remove(i);
                return true;
            }
        }
        return false;
    }



    public boolean checkProductId(String id){
        for(Product p:products) {
            if (p.getProductId().equals(id)) {
                return true;
            }

        }
        return false;
    }



    //Get discount
    private final CategoryService categoryService;
    private final UserService userService;




    public ArrayList<Product>getNewReleases(int countofLastAddedProduct,String categoryId) {
        ArrayList<Product> categoryProducts = new ArrayList<>();

        if(!categoryService.checkCategoryId(categoryId)){
            return null;
        }

        for (Product p : products) {
            if (p.getCategoryId().equals(categoryId)) {
                categoryProducts.add(p);
            }

        }
            ArrayList<Product> result = new ArrayList<>();

            int start = Math.max(0, categoryProducts.size() - countofLastAddedProduct);
            for (int i = start; i < categoryProducts.size(); i++) {
                result.add(categoryProducts.get(i));
            }
            return result;

    }


    public ArrayList<Product>getRelatedProductsByHigherOrLower(
            String productId,
            String productDirection){//السعر
        ArrayList<Product>result=new ArrayList<>();
        if(!productDirection.equalsIgnoreCase("higher") &&
                !productDirection.equalsIgnoreCase("lower")){
            return null;
        }
        boolean foundProduct=false;
        Double originalPrice=null;
        String categoryId=null;

        for(Product p:products){
            if(p.getProductId().equals(productId)){
                categoryId=p.getCategoryId();
                originalPrice=p.getProductPrice();
                foundProduct=true;

            }
        }
        if(!foundProduct){
            return null;
        }
        for(Product p:products){
            if(p.getCategoryId().equals(categoryId)&&!p.getProductId().equals(productId)){
                if(productDirection.equalsIgnoreCase(
                        "Higher")&&originalPrice<p.getProductPrice()){
                    result.add(p);
                }else if(productDirection.equalsIgnoreCase(
                        "Lower")&&originalPrice>p.getProductPrice()){
                    result.add(p);
                }
            }
        }
        return result;
    }



    public Product cheapestProduct(String categoryId){
        ArrayList<Product>result=new ArrayList<>();

        if(!categoryService.checkCategoryId(categoryId)){
            return null;
        }
        for(Product p:products){
            if(p.getCategoryId().equals(categoryId)){
                result.add(p);
            }
        }
        if(result.isEmpty()){
            return null;
        }
        Product cheapest=result.get(0);

        for(int i =0;i<result.size();i++){
            if(result.get(i).getProductPrice()<cheapest.getProductPrice()){
                cheapest=result.get(i);
            }
        }

        return cheapest;

    }










}
