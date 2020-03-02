package com.mate.academy.reviewsanalyzer.controller;

import com.mate.academy.reviewsanalyzer.service.ReviewService;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class InitializeController {
    private final ReviewService reviewService;

    public InitializeController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/init")
    public String initializeFileReading() {
        log.info("Started file reading at" + LocalDateTime.now());
        reviewService.readFromResourceFile();
        return "File has been read at" + LocalDateTime.now();
    }
}
