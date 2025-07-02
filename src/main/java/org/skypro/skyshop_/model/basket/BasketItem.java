package org.skypro.skyshop_.model.basket;

import org.skypro.skyshop_.model.product.Product;

import java.util.UUID;

public class BasketItem {
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}