package com.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class ShoppingCart {

    HashMap<String, Double> itemPrice;
    HashMap<String, Integer> cart;
    Double salesTaxRate;
    Double salesTax;
    Double total;

    ShoppingCart(HashMap<String, Double> itemPrice, Double salesTaxRate) {
        this.itemPrice = itemPrice;
        this.salesTaxRate = salesTaxRate;
        cart = new HashMap<>();
        total = 0.0;
        salesTax = 0.0;
    }

    public Double getTotal() {
        return total;
    }

    public HashMap<String, Integer> getCart() {
        return cart;
    }

    void addToCart(String item, int quantity) {

        if (!itemPrice.containsKey(item) || !(quantity > 0))
            throw new IllegalArgumentException("Not a valid item or quantity");

//        if an item exists in the cart, increment the quantity
        if (cart.containsKey(item)) {
            cart.put(item, cart.get(item) + quantity);
//            else add a new entry with quantity
        } else {
            cart.put(item, quantity);
        }

//         add price for the item and quantity
        total += (itemPrice.get(item) * quantity);
        total = round(total, 2);
    }

    void addSalesTax() {

//        calculate sales tax for this item and quantity
        salesTax = salesTaxRate * total;

        total += salesTax;
        total = round(total, 2);
    }

    private static double round(double value, int places) {

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
