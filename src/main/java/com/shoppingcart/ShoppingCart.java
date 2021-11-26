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
    private Double salesTax;
    private Double total;

    ShoppingCart() {
        cart = new HashMap<>();
        total = 0.0;
    }

    public Double getTotal() {
        return total;
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

//         add price for the item and quantity
        total += (product.getPrice() * quantity);
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
