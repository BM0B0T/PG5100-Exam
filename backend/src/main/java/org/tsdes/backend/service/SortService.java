package org.tsdes.backend.service;

import org.springframework.stereotype.Service;
import org.tsdes.backend.entity.Review;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortService {
    public List <Review> sortByRating(List <Review> reviewList) {
        return reviewList.stream()
                .sorted(Comparator.comparing(Review::getRating).reversed())
                .collect(Collectors.toList());
    }

    public List <Review> sortByTimestamp(List <Review> reviewList) {
        return reviewList.stream()
                .sorted(Comparator.comparing(Review::getTimestamp).reversed())
                .collect(Collectors.toList());
    }
}
