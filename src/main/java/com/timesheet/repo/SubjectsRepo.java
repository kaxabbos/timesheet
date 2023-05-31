package com.timesheet.repo;

import com.timesheet.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectsRepo extends JpaRepository<Subjects, Long> {
    List<Subjects> findAllByNameContainingAndPrice(String name, int price);
}
