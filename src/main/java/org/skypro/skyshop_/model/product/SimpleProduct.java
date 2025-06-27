package org.skypro.skyshop_.model.product;


import java.math.BigDecimal;
import java.util.UUID;

public class SimpleProduct extends Product {

    public SimpleProduct(UUID id, String name, BigDecimal price) {
        super(id, name, price); // Передаем BigDecimal в суперкласс
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice(); // Возвращаем BigDecimal из суперкласса
    }

    @Override
    public String toString() {
        return getName() + " цена " + getPrice(); // Теперь выводим BigDecimal
    }
}