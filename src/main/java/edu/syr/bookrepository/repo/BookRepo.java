package edu.syr.bookrepository.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.syr.bookrepository.model.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
    // This interface defines a repository for the Book entity, extending JpaRepository.

    List<Book> findByTitle(String title);
    // Method to find a list of books by their title.

    List<Book> findByAuthor(String author);
    // Method to find a list of books by their author.
}
