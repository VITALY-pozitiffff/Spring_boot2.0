package org.skypro.skyshop_.model.search;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {
    UUID getId();

    @JsonIgnore // Скрыть значение в JSON
    String getSearchTerm();

    @JsonIgnore
        // Скрыть значение в JSON
    String getContentType();

    String getName();

    default String getStringRepresentation() {
        return getName() + "-" + getContentType();
    }
}
