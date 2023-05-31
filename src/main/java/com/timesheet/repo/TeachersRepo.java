package com.timesheet.repo;

import com.timesheet.model.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachersRepo extends JpaRepository<Teachers, Long> {
    List<Teachers> findAllByCategory(String category);
}
