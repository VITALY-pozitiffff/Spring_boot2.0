package org.skypro.skyshop_.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop_.model.search.SearchResult;
import org.skypro.skyshop_.model.search.Searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SearchServiceTest {

    @Mock
    private StorageService storageServiceMock;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchEmptyStorage() {
        when(storageServiceMock.getAllSearchables()).thenReturn(new ArrayList<>());

        List<SearchResult> results = searchService.search("любые данные");

        assertEquals(0, results.size(), "Результатов поиска не должно быть при пустом хранилище");
    }

    @Test
    void testSearchWithUnmatchedResults() {
        List<Searchable> items = createSearchableItems("яблоки", "груши");
        when(storageServiceMock.getAllSearchables()).thenReturn(items);

        List<SearchResult> results = searchService.search("виноград");

        assertEquals(0, results.size(), "Должны отсутствовать совпадения по данному шаблону");
    }

    @Test
    void testSearchWithMatchingResults() {
        List<Searchable> items = createSearchableItems("яблоки", "груши", "бананы");
        when(storageServiceMock.getAllSearchables()).thenReturn(items);

        List<SearchResult> results = searchService.search("яблоки");

        assertEquals(1, results.size(), "Ожидался ровно один результат поиска");
        assertEquals("яблоки", results.get(0).getName(), "Имя первого результата поиска неверно");
    }

    private List<Searchable> createSearchableItems(String... names) {
        List<Searchable> result = new ArrayList<>();
        for (String name : names) {
            result.add(createSearchable(name));
        }
        return result;
    }

    private Searchable createSearchable(String name) {
        return new Searchable() {
            private final UUID id = UUID.randomUUID(); // Генерируем уникальный ID
            private final String contentType = "Тип-" + name;

            @Override
            public UUID getId() {
                return id;
            }

            @Override
            public String getSearchTerm() {
                return name;
            }

            @Override
            public String getContentType() {
                return contentType;
            }

            @Override
            public String getName() {
                return name;
            }
        };
    }


