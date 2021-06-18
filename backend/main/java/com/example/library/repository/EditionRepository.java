package com.example.library.repository;

import com.example.library.model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {

    List<Edition> findTop10ByOrderByRatingDesc();

    List<Edition> findTop16ByOrderByReadsDesc();

    public Map<Long, List<Edition>> searchEditions(String text, int page, int amount);

}
