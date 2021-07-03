package com.example.library.service;

import com.example.library.model.*;
import com.example.library.model.dto.ReviewDisplayDto;
import com.example.library.model.dto.ReviewSubmissionDto;
import com.example.library.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            Edition edition = editionService.getEditionById(editionId);
            if (edition == null)
                return "Edition doesn't exist.";

            BookReservation bookReservation = reservationService.getReservationByMemberIdAndEditionId(reviewSubmissionDto.getMemberId(), editionId);
            if (bookReservation == null)
                return "Reservation doesn't exist.";

            Review review = modelMapper.map(reviewSubmissionDto, Review.class);
            review.setEdition(edition);
            review.setBookReservation(bookReservation);

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
        return reservationService.getReservationByMemberIdAndEditionId(userId, editionId) != null;
    }

    public List<ReviewDisplayDto> getReviewsByEditionId(Long editionId) {
        List<Review> reviews = reviewRepository.findByEdition_Id(editionId);
        List<ReviewDisplayDto> reviewDtos = new ArrayList<>();

        for (Review r : reviews) {
            ReviewDisplayDto dto = modelMapper.map(r, ReviewDisplayDto.class);

            Member member = (Member) r.getBookReservation().getUser();
            dto.setFullName(member.getName() + " " + member.getSurname());

            reviewDtos.add(dto);
        }

        return reviewDtos;
    }
}
