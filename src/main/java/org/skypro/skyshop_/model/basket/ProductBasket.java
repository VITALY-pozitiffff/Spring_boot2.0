package org.skypro.skyshop_.model.basket;



import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ProductBasket {

    private final Map<UUID, Integer> basketMap = new ConcurrentHashMap<>();

    public ProductBasket() {
    }

    public void addProduct(UUID productId) {
        synchronized(basketMap) {
            basketMap.merge(productId, 1, Integer::sum); // Если товар уже есть, увеличиваем количество
        }
    }

    public Map<UUID, Integer> getCurrentBasket() {
        return Collections.unmodifiableMap(basketMap); // Возврат защищённой копии карты
    }
}