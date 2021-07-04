package com.example.library.repository;

import com.example.library.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByEdition_Id(Long editionId);

    Page<Review> findByCheckedFalse(Pageable paging);

    List<Review> findByEdition_IdAndAllowedTrue(Long editionId);

    @Query(value = "select * from review r1 join reservation r2 on r1.book_reservation_id = r2.id where r1.edition_id=?2 and r2.user_id=?1", nativeQuery = true)
    Review findByMemberIdAndEditionId(Long userId, Long editionId);
}
