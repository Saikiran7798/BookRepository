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
import edu.syr.bookrepository.model.Student;
import edu.syr.bookrepository.serviceImplementation.StudentRepoService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	// This class serves as a REST controller for student-related operations.

	@Autowired
	private StudentRepoService studentrepoService;
	// Autowired StudentRepoService to access student-related service methods.

	/*
	 * Handle HTTP POST request to register a new student with provided details.
	 * 'suid': The unique identifier of the student. 'firstName': The first name of
	 * the student. 'lastName': The last name of the student. 'emailID': The email
	 * ID of the student.
	 */

	@PostMapping("/register-student")
	@ResponseBody
	public String addStudent(@RequestParam long suid, @RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String emailID) {
		return studentrepoService.registerStudent(suid, firstName, lastName, emailID);
	}
	
	@GetMapping("/all")
	// Handle HTTP GET request to retrieve a list of all books.
	public @ResponseBody List<Student> getBooks() {
		return studentrepoService.getAllStudents();
	}
}
