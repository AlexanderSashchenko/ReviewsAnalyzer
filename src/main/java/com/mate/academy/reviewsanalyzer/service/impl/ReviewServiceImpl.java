package com.mate.academy.reviewsanalyzer.service.impl;

import com.mate.academy.reviewsanalyzer.model.Review;
import com.mate.academy.reviewsanalyzer.repository.ReviewRepository;
import com.mate.academy.reviewsanalyzer.service.ReviewService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private static final String FILE_PATH = "src/main/resources/Reviews.csv";
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<String> getMostActiveUsers() {
        return reviewRepository.getMostActiveUsers();
    }

    @Override
    public List<String> getMostCommentedProducts() {
        return reviewRepository.getMostCommentedProduct();
    }

    @Override
    public List<String> getMostFrequentlyUsedWords() {
        return reviewRepository.getMostFrequentlyUsedWords();
    }

    @Override
    public List<Review> readFromResourceFile() {
        List<Review> reviewsList;
        try {
            File inputFile = new File(FILE_PATH);
            InputStream fileInputStream = new FileInputStream(inputFile);
            BufferedReader bufferedReader
                    = new BufferedReader(new InputStreamReader(fileInputStream));
            reviewsList = bufferedReader.lines().skip(1).map(mapLineToReview)
                    .collect(Collectors.toList());
            //TODO probably try to save each mapped entity to DB instead of collecting it to list;
            //TODO UPD Dis mudafucka works too long. Probably better use parallel streams or
            // some custom library for csv paring
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
        reviewRepository.saveAll(reviewsList);
        return reviewsList;
    }

    private Function<String, Review> mapLineToReview = (line) -> {
        String[] reviewFields = line.concat(" ").split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
        //TODO Still some troubles with parsing data
        Review review = new Review();
        review.setProductId(reviewFields[1]);
        review.setUserId(reviewFields[2]);
        review.setProfileName(reviewFields[3]);
        review.setHelpfulnessNumerator(reviewFields[4]);
        review.setHelpfulnessDenominator(reviewFields[5]);
        review.setScore(Integer.valueOf(reviewFields[6]));
        review.setTime(LocalDateTime.now()); //TODO implement Date&Time parsing from reviewFields[7]
        review.setSummary(reviewFields[8]);
        review.setText(reviewFields[9].trim());
        return review;
    };
}
