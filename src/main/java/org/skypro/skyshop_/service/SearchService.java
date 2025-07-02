package org.skypro.skyshop_.service;

import org.skypro.skyshop_.model.search.SearchResult;
import org.skypro.skyshop_.model.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final StorageService storageService;

    @Autowired
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }


    public List<SearchResult> search(String pattern) {
        return storageService.getAllSearchables()
                .stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(pattern.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
