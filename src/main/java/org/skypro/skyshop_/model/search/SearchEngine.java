package org.skypro.skyshop_.model.search;

import org.skypro.skyshop_.model.product.BestResultNotFound;

import java.util.*;
import java.util.stream.Collectors;


public class SearchEngine<K extends Searchable> {
    private Set<K> searchables = new HashSet<>();


    public void add(K k) {
        searchables.add(k);
    }

    public Set<Searchable> search(String query) {

        Comparator<Searchable> comparator = (s1, s2) -> {
            int lengthCompare = Integer.compare(
                    s2.getName().length(),
                    s1.getName().length()
            );
            return lengthCompare != 0
                    ? lengthCompare
                    : s1.getName().compareTo(s2.getName());
        };

        return searchables.stream()
                .filter(s -> s.getSearchTerm().contains(query))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxCount = 0;

        for (Searchable searchable : searchables) {
            String searchTerm = searchable.getSearchTerm();
            int count = countOccurrences(searchTerm, search);

            if (count > maxCount) {
                maxCount = count;
                bestMatch = searchable;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(search);
        }

        return bestMatch;
    }

    private int countOccurrences(String text, String search) {
        int count = 0;
        int index = 0;

        while ((index = text.indexOf(search, index)) != -1) {
            count++;
            index += search.length();
        }

        return count;
    }
}