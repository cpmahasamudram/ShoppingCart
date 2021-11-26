package com.shoppingcart;

class Product {
        private String name;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    private Double price;

        Product(String name, Double price) {
            this.name = name;
            this.price = price;
        }
    }

