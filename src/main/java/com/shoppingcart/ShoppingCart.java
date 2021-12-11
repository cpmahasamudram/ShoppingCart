package com.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Set;

public class ShoppingCart {


    public Set<Product> getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(Set<Product> productCatalog) {
        this.productCatalog = productCatalog;
    }

    private Set<Product> productCatalog;
    private HashMap<Product, Integer> cart;

    public Double getSalesTaxRate() {
        return salesTaxRate;
    }

    public void setSalesTaxRate(Double salesTaxRate) {
        this.salesTaxRate = salesTaxRate;
    }

    private Double salesTaxRate;

    public Double getSalesTax() {
        return salesTax;

    }

    public void setSalesTax(Double salesTax) {
        this.salesTax = salesTax;
    }

    private Double salesTax;
    private Double total;

    ShoppingCart() {
        cart = new HashMap<>();
        total = 0.0;
        salesTaxRate = 0.0;
    }

    public Double getTotal() {
        for (Product k :cart.keySet()) {
            total += k.getPrice()*cart.get(k);
        }
        total += calculateSalesTax();
        System.out.println("total --- "+ total);
        return round(total, 2);
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    void addToCart(Product product, int quantity) {

        if (!productCatalog.contains(product) || !(quantity > 0))
            throw new IllegalArgumentException("Not a valid item or quantity");

//        if an item exists in the cart, increment the quantity
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + quantity);
//            else add a new entry with quantity
        } else {
            cart.put(product, quantity);
        }

    }


    Double calculateSalesTax() {

//        calculate sales tax for this item and quantity
        salesTax = salesTaxRate * total;
        salesTax = round(salesTax, 2);
//        this.setSalesTax(salesTax);
        return salesTax;

    }

    private static double round(double value, int places) {

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void remove(Product product, int quantity) {
        if (cart.containsKey(product)) {
            if(cart.get(product) >= quantity) {
                cart.put(product, cart.get(product) - quantity);
            } else {
                    cart.put(product, 0);
            }

        }
        System.out.println(cart.get(product));
    }
}
