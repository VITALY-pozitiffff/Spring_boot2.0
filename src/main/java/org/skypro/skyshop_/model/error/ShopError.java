package org.skypro.skyshop_.model.error;

public final class ShopError {
    private final String code;
    private final String message;

    /**
     * Конструктор принимает код ошибки и её сообщение.
     */
    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Получение кода ошибки.
     *
     * @return Код ошибки.
     */
    public String getCode() {
        return code;
    }

    /**
     * Получение сообщения об ошибке.
     *
     * @return Сообщение об ошибке.
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "org.skypro.skyshop_.model.error.ShopError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
