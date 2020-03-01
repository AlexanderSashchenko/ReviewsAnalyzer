package com.mate.academy.reviewsanalyzer.service;

import com.mate.academy.reviewsanalyzer.model.Review;

import java.util.List;

public interface ReviewService {
    List<String> getMostActiveUsers();

    List<String> getMostCommentedProducts();

    List<String> getMostFrequentlyUsedWords();

    List<Review> readFromResourceFile();
}
