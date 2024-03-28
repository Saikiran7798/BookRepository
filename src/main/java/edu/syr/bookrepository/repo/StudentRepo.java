package edu.syr.bookrepository.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.syr.bookrepository.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
    // This interface defines a repository for the Student entity, extending JpaRepository.
}
