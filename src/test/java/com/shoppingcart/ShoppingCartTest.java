package com.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class ShoppingCartTest {
    ShoppingCart shoppingCart;
    Set<Product> productCatalog = new HashSet<>();
    Product doveSoap = new Product("Dove Soap", 39.99);
    Product axeDeo = new Product("Axe Deo", 99.99);

    @BeforeEach
    void init() {
        productCatalog.add(doveSoap);
        productCatalog.add(axeDeo);
        shoppingCart = new ShoppingCart();
        shoppingCart.setProductCatalog(productCatalog);
        shoppingCart.setSalesTaxRate(0.125);
    }

    @Test
    public void whenSingleItemAddedToCartShowsItemWithTotal() {
        shoppingCart.addToCart(doveSoap, 1);

        assertEquals(shoppingCart.getCart().size(), 1);
        assertEquals(shoppingCart.getCart().get(doveSoap), 1);
        assertEquals(39.99, shoppingCart.getTotal());
    }

    @Test
    public void whenInvalidItemAddedToCartThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addToCart(new Product("Dove Shampoo", 29.99), 1);
        });

        String expectedMessage = "Not a valid item or quantity";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void whenInvalidQuantityAddedToCartThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addToCart(doveSoap, 0);
        });

        String expectedMessage = "Not a valid item or quantity";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenMultipleQuantityOfSameItemAddedToCartShowSingleLineItem() {
        shoppingCart.addToCart(doveSoap, 5);
        shoppingCart.addToCart(doveSoap, 3);

        assertEquals(shoppingCart.getCart().size(), 1);
        assertEquals(shoppingCart.getCart().get(doveSoap), 8);
        assertEquals(319.92, shoppingCart.getTotal());
    }

    @Test
    public void whenMultipleItemsAndQuantitiesAddedToCartAggregateByItemAndCalculateTotalWithTax() {
        shoppingCart.addToCart(doveSoap, 2);
        shoppingCart.addToCart(axeDeo, 2);
        shoppingCart.addSalesTax();

        assertEquals(shoppingCart.getCart().size(), 2);
        assertEquals(shoppingCart.getCart().get(doveSoap), 2);
        assertEquals(shoppingCart.getCart().get(axeDeo), 2);
        assertEquals(314.96, shoppingCart.getTotal());
    }

}