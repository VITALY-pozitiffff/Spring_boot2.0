package org.skypro.skyshop_.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop_.exception.NoSuchProductException;
import org.skypro.skyshop_.model.basket.ProductBasket;
import org.skypro.skyshop_.model.basket.UserBasket;
import org.skypro.skyshop_.model.product.Product;
import org.skypro.skyshop_.model.product.SimpleProduct;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BasketServiceTest {

    @Mock
    private ProductBasket productBasketMock;

    @Mock
    private StorageService storageServiceMock;

    @InjectMocks
    private BasketService basketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNonExistingProductToBasket() {
        UUID nonExistentProductId = UUID.randomUUID();
        when(storageServiceMock.getProductById(nonExistentProductId)).thenThrow(new NoSuchProductException("Товар не найден"));

        assertThrows(NoSuchProductException.class, () ->
                basketService.addProductToBasket(nonExistentProductId)
        );
    }

    @Test
    void testAddExistingProductToBasket() {
        UUID existingProductId = UUID.randomUUID();
        Product product = new SimpleProduct(existingProductId, "Яблоко", 100);
        when(storageServiceMock.getProductById(existingProductId)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(existingProductId);

        verify(productBasketMock).addProduct(existingProductId);
    }

    @Test
    void testGetEmptyUserBasket() {
        when(productBasketMock.getCurrentBasket()).thenReturn(Map.of());

        UserBasket userBasket = basketService.getUserBasket();

        assertEquals(0, userBasket.getItems().size(), "Корзина должна быть пустой");
    }

    @Test
    void testGetFilledUserBasket() {
        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct(productId, "Апельсин", 80);
        Map<UUID, Integer> currentBasket = new HashMap<>();
        currentBasket.put(productId, 2); // 2 штуки апельсинов

        when(productBasketMock.getCurrentBasket()).thenReturn(currentBasket);
        when(storageServiceMock.getProductById(productId)).thenReturn(Optional.of(product));

        UserBasket userBasket = basketService.getUserBasket();

        assertEquals(1, userBasket.getItems().size(), "Количество позиций в корзине неверно");
        assertEquals(2, userBasket.getItems().get(0).getQuantity(), "Неверное количество товара в позиции");
        assertEquals("Апельсин", userBasket.getItems().get(0).getProduct().getName(), "Неправильное название товара");
    }
}