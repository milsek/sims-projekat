package com.example.library.controller;

import com.example.library.model.Review;
import com.example.library.model.dto.ReviewDisplayDto;
import com.example.library.model.dto.ReviewSubmissionDto;
import com.example.library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("review")
    public Review getReview(@RequestParam Long id) {
        return reviewService.getById(id);
    }

    @GetMapping("edition-reviews")
    public List<ReviewDisplayDto> getReviewsByEdition(@RequestParam Long editionId) {
        return reviewService.getReviewsByEditionId(editionId);
    }

    @GetMapping("user-can-review")
    public Boolean userCanReview(@RequestParam Long userId, @RequestParam Long editionId){
        return reviewService.userCanReview(userId, editionId);
    }

    @PostMapping("review")
    public String submitReview(@RequestBody ReviewSubmissionDto reviewSubmissionDto, @RequestParam Long editionId) {
        return reviewService.createReview(reviewSubmissionDto, editionId);
    }

    @PostMapping("mark-review")
    public String markReview(@RequestParam Boolean allowed, @RequestParam Long reviewId) {
        return reviewService.markReview(reviewId, allowed);
    }
}