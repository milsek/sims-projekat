package com.example.library.repository;

import com.example.library.model.Isle;
import com.example.library.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {

    Line findByIsleAndNumber(Isle isle, Integer lineNumber);
}
