package com.example.library.repository;

import com.example.library.model.Review;
import com.example.library.model.dto.ReviewDisplayDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findByEdition_Id(Long editionId);

    List<Review> findByCheckedFalse();

    List<Review> findByEdition_IdAndAllowedTrue(Long editionId);
}
