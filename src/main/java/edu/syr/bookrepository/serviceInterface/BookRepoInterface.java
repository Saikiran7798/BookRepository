package edu.syr.bookrepository.serviceInterface;

import java.util.List;

import edu.syr.bookrepository.model.Book;

public interface BookRepoInterface {
    // This interface defines a set of methods for interacting with the book repository.

    public List<Book> getAllBooks();
    // Retrieve and return a list of all books from the repository.

    public String buyBook(long id, long suid);
    // Method to buy a book with the Book 'id' by a student with 'suid'.

    public String sellBook(long id, long suid);
    // Method to resell a book with the Book 'id' by a student with 'suid'.

    public String addBook(String title, String author, String isbn, String edition, double initialPrice);
    // Method to add a new book to the repository with the provided details.

    public String deleteBook(long id);
    // method to delete a book with the Book 'id' from the repository.

    public List<Book> findByTitle(String title);
    // method to retrieve and return a list of books that match the given 'title'.

    public List<Book> findByAuthor(String author);
    // Method to retrieve and return a list of books that match the given 'author'.

    public Book findById(long id);
    // Method to retrieve and return a book by its unique identifier ('id').
}

