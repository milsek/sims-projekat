package com.example.library.repository;

import com.example.library.model.Building;
import com.example.library.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {

}
