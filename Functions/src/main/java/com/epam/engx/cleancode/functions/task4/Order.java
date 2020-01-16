package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.Iterator;
import java.util.List;

public class Order {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
    	
    	removeUnavailableProducts();
    	
        double orderPrice = 0.0;
        for (Product product : products)
            orderPrice += product.getProductPrice();
        return orderPrice;
    }
    
    public void removeUnavailableProducts() {
    	Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
           
            if (!iterator.next().isAvailable())
                iterator.remove();
        }
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
