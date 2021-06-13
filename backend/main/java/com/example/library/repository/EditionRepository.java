package com.example.library.repository;

import com.example.library.model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {

    List<Edition> findTop10ByOrderByRatingDesc();

    List<Edition> findTop16ByOrderByReadsDesc();

}
