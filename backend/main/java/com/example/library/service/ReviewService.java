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
import java.util.stream.Collectors;

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

            System.out.println(reviewSubmissionDto.getBookReservationMemberId());
            System.out.println(editionId);
            BookReservation bookReservation = reservationService.getReservationByMemberIdAndEditionId(reviewSubmissionDto.getBookReservationMemberId(), editionId);
            if (bookReservation == null)
                return "Reservation doesn't exist.";

            Review review = modelMapper.map(reviewSubmissionDto, Review.class);
            review.setEdition(edition);
            review.setBookReservation(bookReservation);
            review.setId(null);

            reviewRepository.saveAndFlush(review);
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
            review.setChecked(true);
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
        boolean reservationExist = reservationService.getReservationByMemberIdAndEditionId(userId, editionId) != null;
        boolean reviewExist = reviewRepository.findByMemberIdAndEditionId(userId, editionId) != null;

        return (reservationExist && !reviewExist);
    }

    public List<ReviewDisplayDto> getReviewsByEditionId(Long editionId) {
        List<Review> reviews = reviewRepository.findByEdition_IdAndAllowedTrue(editionId);
        List<ReviewDisplayDto> reviewDtos = new ArrayList<>();

        for (Review r : reviews) {
            ReviewDisplayDto dto = modelMapper.map(r, ReviewDisplayDto.class);

            Member member = (Member) r.getBookReservation().getUser();
            dto.setFullName(member.getName() + " " + member.getSurname());

            reviewDtos.add(dto);
        }

        return reviewDtos;
    }

    public List<ReviewDisplayDto> getUncheckedReviews() {
        //return new ArrayList<ReviewDisplayDto>();
        return reviewRepository.findByCheckedFalse().stream().map(x -> modelMapper.map(x, ReviewDisplayDto.class)).collect(Collectors.toList());
    }
}
