package edu.syr.bookrepository.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.syr.bookrepository.model.Book;
import edu.syr.bookrepository.serviceImplementation.BookRepoService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	// This class serves as a REST controller for book-related operations.

	@Autowired
	private BookRepoService bookRepoService;
	// Autowired BookRepoService to access book-related service methods.

	@GetMapping("/all")
	// Handle HTTP GET request to retrieve a list of all books.
	public @ResponseBody List<Book> getBooks() {
		return bookRepoService.getAllBooks();
	}

	/*
	 * Handle HTTP POST request to buy a book with the specified 'id' by a student
	 * with 'suid'. 
	 * - 'id': The unique identifier of the book to be purchased. -
	 * 'suid': The SUID of the student making the purchase.
	 */
	@PostMapping("/buy")
	@ResponseBody
	public String buyBook(@RequestParam long id, @RequestParam long suid) {
		return bookRepoService.buyBook(id, suid);
	}

	/*
	 * Handle HTTP POST request to sell a book with the specified 'id' by a student
	 * with 'suid'. 'id': The unique identifier of the book to be sold. 'suid': The
	 * SUID of the student selling the book.
	 */
	@PostMapping("/sell")
	@ResponseBody
	public String sellBook(@RequestParam long id, @RequestParam long suid) {
		return bookRepoService.sellBook(id, suid);
	}

	/*
	 * Handle HTTP POST request to add a new book to the repository with provided
	 * details. 'title': The title of the book. 'author': The author of the book.
	 * 'isbn': A unique number given to each book. 'edition': The edition of the
	 * book. 'initialPrice': The cost of the book.
	 */
	@PostMapping("/add")
	@ResponseBody
	public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam String isbn,
			@RequestParam String edition, @RequestParam double initialPrice) {
		return bookRepoService.addBook(title, author, isbn, edition, initialPrice);
	}

	/*
	 * Handle HTTP POST request to delete a book with the specified 'id' from the
	 * repository. 'id': The unique identifier of the book to be deleted.
	 */
	@PostMapping("/delete")
	@ResponseBody
	public String deleteBook(@RequestParam long id) {
		return bookRepoService.deleteBook(id);
	}

	/*
	 * Handle HTTP GET request to retrieve a list of books that match the given
	 * 'title'. 'title': The title used for matching books.
	 */
	@GetMapping("/find-by-title")
	@ResponseBody
	public List<Book> getBookByTitle(@RequestParam String title) {
		return bookRepoService.findByTitle(title);
	}

	/*
	 * Handle HTTP GET request to retrieve a list of books that match the given
	 * 'author'. 'author': The author used for matching books.
	 */
	@GetMapping("/find-by-author")
	@ResponseBody
	public List<Book> getBookByAuthor(@RequestParam String author) {
		return bookRepoService.findByAuthor(author);
	}

	/*
	 * Handle HTTP GET request to retrieve a book by its unique identifier ('id').
	 * 'id': The unique identifier of the book to be retrieved.
	 */
	@GetMapping("/find-by-id")
	@ResponseBody
	public Book getBookById(@RequestParam long id) {
		return bookRepoService.findById(id);
	}
}
