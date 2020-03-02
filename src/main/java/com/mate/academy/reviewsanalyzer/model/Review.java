package com.mate.academy.reviewsanalyzer.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private String userId;
    private String profileName;
    private String helpfulnessNumerator;
    private String helpfulnessDenominator;
    private Integer score;
    private LocalDateTime time;
    private String summary;
    @Column(columnDefinition = "TEXT")
    private String text;
}
