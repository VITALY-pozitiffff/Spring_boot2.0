package org.skypro.skyshop_.product;

import org.skypro.skyshop_.model.product.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final BigDecimal FIXED_PRICE = new BigDecimal("99.99"); // Меняем тип на BigDecimal

    public FixPriceProduct(UUID id, String name) {
        super(id, name, FIXED_PRICE); // Передаем BigDecimal в конструктор родительского класса
    }

    @Override
    public BigDecimal getPrice() {
        return FIXED_PRICE; // Возвращаем BigDecimal
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIXED_PRICE; // Теперь используем BigDecimal
    }
}