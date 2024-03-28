package edu.syr.bookrepository.serviceImplementation;
import edu.syr.bookrepository.model.Book.Status;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.syr.bookrepository.repo.BookRepo;
import edu.syr.bookrepository.repo.StudentRepo;
import edu.syr.bookrepository.serviceInterface.BookRepoInterface;
import edu.syr.bookrepository.model.Book;
import edu.syr.bookrepository.model.Student;


@Service
@Transactional
public class BookRepoService implements BookRepoInterface {
    // Service implementation for the BookRepoInterface
	
	/* The @Autowired annotation is used to automatically inject a Spring-managed bean of the specified type
	 into this class, making it available for use..*/


    @Autowired
    private BookRepo bookRepo; // Autowired Book repository

    
    @Autowired
    private StudentRepo studentRepo; // Autowired Student repository

    @Override
    public List<Book> getAllBooks() {
        // Retrieve and return a list of all books from the repository.
        return bookRepo.findAll();
    }

    // Function implementation to Buy Book
    @Override
    public String buyBook(long id, long suid) {
        try {
            Optional<Book> book = bookRepo.findById(id);
            Optional<Student> student = studentRepo.findById(suid);
            if (book.isPresent() && student.isPresent()) {
                if (book.get().getAvailability().equals(Status.Available)) {
                    // Check if the book is available for purchase. If yes, perform the purchase operation.
                    String returnMsg = "Thank you for Buying Book " + book.get().getTitle() + ", Price: $ " +
                            book.get().getCurrentPrice() + " \n Please remember the Id[" + book.get().getBookId() +
                            "] of the Book when reselling.";
                    book.get().setAvailability(Status.NotAvailable);
                    book.get().setSoldCount(book.get().getSoldCount() + 1);
                    book.get().setCurrentPrice(book.get().getCurrentPrice() - ((10 * book.get().getCurrentPrice())) / 100);
                    book.get().setSUID(student.get());
                    return returnMsg;
                } else {
                    return "Sorry!, Book is currently unavailable";
                }
            } else if (!student.isPresent()) {
                throw new NoSuchElementException("Student is not registered, suid: " + suid);
            } else {
                throw new NoSuchElementException("Book not in inventory, id: " + id);
            }
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

    //Function implementation to sell book
    @Override
    public String sellBook(long id, long suid) {
        try {
            Optional<Book> book = bookRepo.findById(id);
            Optional<Student> student = studentRepo.findById(suid);
            if (book.isPresent() && student.isPresent()) {
                if (book.get().getAvailability().equals(Status.NotAvailable) && (book.get().getSUID().getId() == student.get().getId())) {
                    // Check if the book is not available and the student owns the book, then perform the reselling operation.
                    book.get().setAvailability(Status.Available);
                    book.get().setSUID(null);
                    return "Thank you for reselling the Book, Price: $" + book.get().getCurrentPrice();
                } else {
                    return "Failed to resell book as you don't own the book";
                }
            } else {
                throw new NoSuchElementException("Book is new and not for reselling");
            }
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

    // Function implementation to add a new book to the inventory
    @Override
    public String addBook(String title, String author, String isbn, String edition,
            double initialPrice) {
        if (bookRepo.count() < 25) {
            // Check if the inventory limit is less than 25.
            Book book = new Book();
            book.setAuthor(author);
            book.setTitle(title);
            book.setISBN(isbn);
            book.setEdition(edition);
            book.setInitialPrice(initialPrice);
            book.setAvailability(Status.Available);
            book.setSoldCount(0);
            book.setCurrentPrice(initialPrice);
            try {
                bookRepo.save(book);
                return "Success";
            } catch (Exception e) {
                return "Unable to add Book";
            }
        } else {
            return "Failure";
        }
    }

    //Function implementation to delete book from inventory
    @Override
    public String deleteBook(long id) {
        try {
            Optional<Book> book = bookRepo.findById(id);
            if (book.isPresent()) {
                if (book.get().getSUID() == null) {
                    // Check if the book is not sold to any student, then delete it from inventory.
                    bookRepo.deleteById(id);
                    return "Book is deleted from inventory";
                } else {
                    return "Book can't be deleted as it is sold to Student with suid " + book.get().getSUID().getId();
                }
            } else {
                return "There is no Book with id " + id + " in the inventory";
            }
        } catch (Exception e) {
            return "Unable to delete book";
        }
    }

    @Override
    public List<Book> findByTitle(String title) {
        // Retrieve and return a list of books by their title.
        return bookRepo.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        // Retrieve and return a list of books by their author.
        return bookRepo.findByAuthor(author);
    }

    @Override
    public Book findById(long id) {
        // Retrieve and return a book by its unique identifier (id).
        return bookRepo.findById(id).get();
    }
}
