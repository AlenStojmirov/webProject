package com.example.webproject.repository;

import com.example.webproject.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic,Long> {
}
