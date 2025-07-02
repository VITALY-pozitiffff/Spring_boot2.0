package org.skypro.skyshop_.service;


import org.skypro.skyshop_.model.article.Article;
import org.skypro.skyshop_.model.product.DiscountedProduct;
import org.skypro.skyshop_.model.product.Product;
import org.skypro.skyshop_.model.product.SimpleProduct;
import org.skypro.skyshop_.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class StorageService {

    private static final Map<UUID, Product> products = new HashMap<>();
    private static final Map<UUID, Article> articles = new HashMap<>();

    public StorageService() {
        initializeTestData();
    }

    private void initializeTestData() {
        UUID product1Id = UUID.randomUUID();
        products.put(product1Id, new SimpleProduct(product1Id, "Хлеб", new BigDecimal("90"))); // Изменили цену на BigDecimal

        UUID product2Id = UUID.randomUUID();
        products.put(product2Id, new org.skypro.skyshop_.product.FixPriceProduct(product2Id, "Молоко"));

        UUID product3Id = UUID.randomUUID();
        products.put(product3Id, new DiscountedProduct(product3Id, "Кофе", new BigDecimal("300"), 10)); // Кофе со скидкой 10%, цена тоже BigDecimal

        // Тестовые статьи
        UUID article1Id = UUID.randomUUID();
        articles.put(article1Id, new Article(article1Id, "Статья №1", "Это первая статья."));

        UUID article2Id = UUID.randomUUID();
        articles.put(article2Id, new Article(article2Id, "Статья №2", "Вторая статья содержит важную информацию."));
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public Set<Product> getAllProducts() {
        return new HashSet<>(products.values()); // Используем HashSet для безопасности
    }

    public Set<Article> getAllArticles() {
        return new HashSet<>(articles.values());
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> allItems = new ArrayList<>();
        allItems.addAll(getAllProducts());
        allItems.addAll(getAllArticles());
        return Collections.unmodifiableCollection(allItems);
    }
}