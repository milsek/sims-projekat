package com.example.library.repository;

import com.example.library.model.Isle;
import com.example.library.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsleRepository extends JpaRepository<Isle, Long> {

    Isle findBySectionAndName(Section section, String isleName);
}
