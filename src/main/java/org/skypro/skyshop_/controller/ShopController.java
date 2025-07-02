package org.skypro.skyshop_.controller;

import org.skypro.skyshop_.model.article.Article;
import org.skypro.skyshop_.model.product.Product;
import org.skypro.skyshop_.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.skypro.skyshop_.model.search.SearchResult;
import org.skypro.skyshop_.service.SearchService;

import java.util.Collection;
import java.util.List;

@RestController
public class ShopController {

    private final SearchService searchService;
    private final StorageService storageService; // Добавляем StorageService

    @Autowired
    public ShopController(SearchService searchService, StorageService storageService) {
        this.searchService = searchService;
        this.storageService = storageService; // Сохраняем ссылку на StorageService
    }

    @GetMapping("/search")
    public List<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getAllProducts(); // Обращаемся к экземпляру
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getAllArticles(); // Обращаемся к экземпляру
    }
}