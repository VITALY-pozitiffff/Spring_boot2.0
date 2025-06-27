package org.skypro.skyshop_.model.product;

import org.skypro.skyshop_.model.search.Searchable;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;
    private final BigDecimal price; // Меняем тип на BigDecimal

    public Product(UUID id, String name, BigDecimal price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым или null");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getSearchTerm() {
        return getName();
    }

    public boolean isSpecial() {
        return false;
    }

    public BigDecimal getPrice() { // Возвращаем BigDecimal
        return price;
    }

    @Override
    public String toString() {
        return "Название товара: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}