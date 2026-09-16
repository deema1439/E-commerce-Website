package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

 ArrayList<MerchantStock>stocks=new ArrayList<>();

 public ArrayList<MerchantStock>gerMerchantStocks(){
     return stocks;
 }

 public boolean addMerchantStock(MerchantStock merchantStock){
     for(MerchantStock m:stocks){
         if(m.getMerchantStockId().equals(merchantStock.getMerchantStockId())){
             return false;
         }
     }
     stocks.add(merchantStock);
     return true;
 }

 public boolean updateMerchantStock(String id,MerchantStock merchantStock){
     for(int i =0 ; i<stocks.size() ; i++){
         if(stocks.get(i).getMerchantStockId().equals(id)){
             stocks.set(i,merchantStock);
             return true;
         }
     }
     return false;
 }

 public boolean deleteMerchantStock(String id){
     for (int i = 0 ;i<stocks.size();i++){
         if(stocks.get(i).getMerchantStockId().equals(id)){
             stocks.remove(i);
             return true;
         }
     }
     return false;
 }

 private final ProductService productService;
 private final MerchantService merchantService;
 private final UserService userService;

 public Integer buyProducts(String productId,String merchantId,String userId,Integer quantity) {
     if (!productService.checkProductId(productId)) {
         return 0;
     }
     if (!merchantService.checkMerchantId(merchantId)) {
         return 1;
     }
     if (!userService.checkUserId(userId)) {
         return 2;
     }
     if (quantity <= 0) {
         return 3;
     }

     MerchantStock foundStock=null;
     for(MerchantStock m : stocks){
         if(m.getProductId().equals(productId) && m.getMerchantId().equals(merchantId)){
             foundStock=m;
         }
     }

     if(foundStock==null){
         return 4;
     }
     if(foundStock.getStock()<quantity){
         return 5;
     }

     double productPrice=0;
     for (Product p : productService.getProducts()) {
         if (p.getProductId().equals(productId)) {
             productPrice = p.getProductPrice();
         }
     }

     User user = null;
     for (User u : userService.getUsers()) {
         if (u.getUserId().equals(userId)) {
             user = u;
         }
     }
     double totalPriceForProduct = productPrice * quantity;
     if (user.getBalance()<totalPriceForProduct) {
         return 6;
     }
     foundStock.setStock(foundStock.getStock()-quantity);
     user.setBalance(user.getBalance()-totalPriceForProduct);
     return 7;
 }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 public Integer addStock(String merchantId, String productId, Integer amount){
     if(!productService.checkProductId(productId)) {
         return 0;
     }
     if(!merchantService.checkMerchantId(merchantId)) {
         return 1;
     }
        if(amount<=0){
            return 2;
        }

     for(MerchantStock m : stocks){
         if(m.getProductId().equals(productId) && m.getMerchantId().equals(merchantId)){
           m.setStock(m.getStock()+amount);
           return 3;
         }
     }
     return 4;
 }




    public String getInventoryValueMessage(String merchantId){
       if(!merchantService.checkMerchantId(merchantId)){
            return "Merchant Not Found";
        }

        double total = 0;
        for(MerchantStock m : stocks){
            if(m.getMerchantId().equals(merchantId)){
                for(Product p : productService.getProducts()){
                    if(p.getProductId().equals(m.getProductId())){
                        total = total + p.getProductPrice() * m.getStock();
                    }
                }
            }
        }
        double threshold = 10000;
        if(total > threshold){
            return "Your inventory value is " + total + " " +
                    " This is relatively high consider reviewing your stock levels " +
                    "or running a promotion";
        }

        return "Your inventory value is " + total ;
    }



    public ArrayList<Product> getProductByMerchant(String merchantName){
        Merchant foundMerchant = null;
        for(Merchant m : merchantService.getMerchants()){
            if(m.getMerchantName().equalsIgnoreCase(merchantName)){
                foundMerchant = m;
                break;
            }
        }
        if(foundMerchant == null){
            return null;
        }
        ArrayList<Product> result = new ArrayList<>();
        for(MerchantStock mms : stocks){
            if(mms.getMerchantId().equals(foundMerchant.getMerchantId())){
                for(Product p : productService.getProducts()){
                    if(p.getProductId().equals(mms.getProductId())){
                        result.add(p);
                    }
                }
            }
        }
        return result;
    }









































}
