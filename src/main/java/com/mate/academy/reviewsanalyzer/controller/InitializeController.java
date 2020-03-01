package com.mate.academy.reviewsanalyzer.controller;

import com.mate.academy.reviewsanalyzer.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitializeController {
    private final ReviewService reviewService;

    public InitializeController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/init")
    public String initializeFileReading() {
        reviewService.readFromResourceFile();
        return "File has been read";
    }
}
