package org.skypro.skyshop_.model.error;

import org.skypro.skyshop_.exception.NoSuchProductException;
import org.skypro.skyshop_.model.error.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException exception) {
        // Формируем объект org.skypro.skyshop_.model.error.ShopError с нужным кодом и сообщением
        ShopError shopError = new ShopError("PRODUCT_NOT_FOUND", "Запрошенный продукт не существует");

        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}
