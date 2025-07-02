package org.skypro.skyshop_.model.article;

import org.skypro.skyshop_.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;      // Новое уникальное поле ID
    private String title;
    private String text;

    // Модифицированный конструктор с параметром id
    public Article(UUID id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    // Геттер для уникального идентификатора
    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id); // Равенство проверяется по идентификатору
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}