package com.example.library.service;

import com.example.library.model.*;
import com.example.library.model.dto.ReviewSubmissionDto;
import com.example.library.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private EditionService editionService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    public String createReview(ReviewSubmissionDto reviewSubmissionDto, Long editionId) {
        try {
            if (!editionService.editionExistsById(editionId))
                return "Edition doesn't exist.";
            Edition edition = editionService.getEditionById(editionId);
            Review review = modelMapper.map(reviewSubmissionDto, Review.class);
            review.setEdition(edition);

            reviewRepository.save(review);
        } catch (Exception e) {
            return "An error occurred.";
        }
        return "Review submitted.";
    }

    public String markReview(Long reviewId, Boolean allowed) {
        try {
            if (!reviewRepository.existsById(reviewId))
                return "Review doesn't exist.";

            Review review = reviewRepository.getById(reviewId);
            review.setAllowed(allowed);
            reviewRepository.save(review);
        } catch (Exception e) {
            return "An error occurred.";
        }
        return "Review updated.";
    }

    public Review getById(Long id) {
        return reviewRepository.getById(id);
    }

    public Boolean userCanReview(Long userId, Long editionId) {
        List<Reservation> reservations = reservationService.getReservationsByMemberId(userId);
        for (Reservation r : reservations) {
            if (!(r instanceof BookReservation))
                continue;

            if (((BookReservation) r).getEdition().getId().equals(editionId) && r.getReservationState() == ReservationState.RETURNED)
                return true;
        }
        return false;
    }
}
