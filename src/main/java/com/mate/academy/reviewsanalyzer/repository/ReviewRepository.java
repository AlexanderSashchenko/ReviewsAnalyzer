package com.mate.academy.reviewsanalyzer.repository;

import com.mate.academy.reviewsanalyzer.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("from Review where id=1") //TODO implement query
    List<String> getMostActiveUsers();

    @Query("from Review where id=1") //TODO implement query
    List<String> getMostCommentedProduct();

    @Query("from Review where id=1") //TODO provide implementationw
    List<String> getMostFrequentlyUsedWords();
}
