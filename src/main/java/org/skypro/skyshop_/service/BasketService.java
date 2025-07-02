package org.skypro.skyshop_.service;

import org.skypro.skyshop_.exception.NoSuchProductException;
import org.skypro.skyshop_.model.basket.BasketItem;
import org.skypro.skyshop_.model.basket.ProductBasket;
import org.skypro.skyshop_.model.basket.UserBasket;
import org.skypro.skyshop_.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public void addProductToBasket(UUID productId) {
        try {
            Product product = storageService.getProductById(productId).get();
            productBasket.addProduct(productId);
        } catch (NoSuchProductException e) {
            System.out.println(e.getMessage()); // Или обработайте иначе, например отправив сообщение клиенту
        }
    }

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