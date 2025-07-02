package org.skypro.skyshop_.model.product;

import java.math.BigDecimal;
import java.util.UUID;

public class DiscountedProduct extends Product {
    private final BigDecimal basePrice; // Базовая цена теперь BigDecimal
    private final int discountPercent; // Процент скидки остается целым числом

    public DiscountedProduct(UUID id, String name, BigDecimal basePrice, int discountPercent) {
        super(id, name, basePrice); // Предаём BigDecimal в родительский класс
        if (basePrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть больше нуля");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть в диапазоне от 0 до 100");
        }
        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
    }

    @Override
    public BigDecimal getPrice() {
        // Расчёт цены с учётом скидки (работаем с BigDecimal)
        BigDecimal discountFactor = BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100)).negate().add(BigDecimal.ONE);
        return basePrice.multiply(discountFactor);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discountPercent + "%)";
    }
}