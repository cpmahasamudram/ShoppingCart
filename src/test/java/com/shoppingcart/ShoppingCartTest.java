package com.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class ShoppingCartTest {
    ShoppingCart shoppingCart;
    HashMap<String, Double> itemPrice = new HashMap<>();

    @BeforeEach
    void init() {
        itemPrice.put("Dove Soap", 39.99);
        itemPrice.put("Axe Deo", 99.99);
        shoppingCart = new ShoppingCart(itemPrice, 0.125);
    }

    @Test
    public void whenSingleItemAddedToCartShowsItemWithTotal() {
        shoppingCart.addToCart("Dove Soap", 1);

        assertEquals(shoppingCart.getCart().size(), 1);
        assertEquals(shoppingCart.getCart().get("Dove Soap"), 1);
        assertEquals(39.99, shoppingCart.getTotal());
    }

    @Test
    public void whenInvalidItemAddedToCartThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addToCart("Dove Shampoo", 1);
        });

        String expectedMessage = "Not a valid item or quantity";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void whenInvalidQuantityAddedToCartThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addToCart("Dove Soap", 0);
        });

        String expectedMessage = "Not a valid item or quantity";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenMultipleQuantityOfSameItemAddedToCartShowSingleLineItem() {
        shoppingCart.addToCart("Dove Soap", 5);
        shoppingCart.addToCart("Dove Soap", 3);

        assertEquals(shoppingCart.getCart().size(), 1);
        assertEquals(shoppingCart.getCart().get("Dove Soap"), 8);
        assertEquals(319.92, shoppingCart.getTotal());
    }

    @Test
    public void whenMultipleItemsAndQuantitiesAddedToCartAggregateByItemAndCalculateTotalWithTax() {
        shoppingCart.addToCart("Dove Soap", 2);
        shoppingCart.addToCart("Axe Deo", 2);
        shoppingCart.addSalesTax();

        assertEquals(shoppingCart.getCart().size(), 2);
        assertEquals(shoppingCart.getCart().get("Dove Soap"), 2);
        assertEquals(shoppingCart.getCart().get("Axe Deo"), 2);
        assertEquals(314.96, shoppingCart.getTotal());
    }

}