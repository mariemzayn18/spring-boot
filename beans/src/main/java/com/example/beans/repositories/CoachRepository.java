package com.example.beans.repositories;

import com.example.externalbean.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
