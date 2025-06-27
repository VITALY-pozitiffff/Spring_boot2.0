package org.skypro.skyshop_.service;

import org.skypro.skyshop_.model.basket.BasketItem;
import org.skypro.skyshop_.model.basket.ProductBasket;
import org.skypro.skyshop_.model.basket.UserBasket;
import org.skypro.skyshop_.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    // Уже реализованный метод добавления товара
    public void addProductToBasket(UUID productId) {
        Optional<Product> productOpt = storageService.getProductById(productId);
        if (!productOpt.isPresent()) {
            throw new IllegalArgumentException("Продукт с таким id не найден.");
        }
        productBasket.addProduct(productId);
    }

    // Метод получения полной корзины пользователя
    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketContents = productBasket.getCurrentBasket();
        List<BasketItem> basketItems = basketContents.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductById(productId).orElseThrow(IllegalStateException::new);
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());
        return new UserBasket(basketItems);
    }
}