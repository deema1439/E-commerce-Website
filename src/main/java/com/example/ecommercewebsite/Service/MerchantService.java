package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantService {


    ArrayList<Merchant>merchants=new ArrayList<>();

    public ArrayList<Merchant>getMerchants(){
        return merchants;
    }

    public boolean addMerchants(Merchant merchant){
        for(Merchant m:merchants){
            if(m.getMerchantId().equals(merchant.getMerchantId())){
                return false;
            }
        }
        merchants.add(merchant);
        return true;
    }



    public boolean updateMerchants(String id,Merchant merchant){
        for(int i=0;i<merchants.size();i++){
            if(merchants.get(i).getMerchantId().equals(id)){
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchants(String id){
        for(int i=0;i<merchants.size();i++){
            if(merchants.get(i).getMerchantId().equals(id)){
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }


public boolean checkMerchantId(String id){
        for(Merchant m:merchants){
            if(m.getMerchantId().equals(id)){
                return true;
            }
        }
        return false;
}


















}
