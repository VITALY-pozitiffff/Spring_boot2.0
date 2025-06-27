package org.skypro.skyshop_.model.basket;

import org.skypro.skyshop_.model.product.Product;

import java.math.BigDecimal;
import java.util.List;

public class UserBasket {
    private final List<BasketItem> items;
    private final BigDecimal totalCost;

    public UserBasket(List<BasketItem> items) {
        this.items = items;
        this.totalCost = calculateTotal(items);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    private BigDecimal calculateTotal(List<BasketItem> items) {
        return items.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}