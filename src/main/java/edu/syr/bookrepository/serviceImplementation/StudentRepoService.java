package edu.syr.bookrepository.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.syr.bookrepository.model.Book;
import edu.syr.bookrepository.model.Student;
import edu.syr.bookrepository.repo.StudentRepo;
import edu.syr.bookrepository.serviceInterface.StudentRepoInterface;
import jakarta.transaction.Transactional;

/* The @Service annotation is used to declare a class as a Spring-managed service component.
   It indicates that this class is a part of the business logic layer and should be managed
   by the Spring framework.
  */
@Service

/* The @Transactional annotation is used to specify that a method should be wrapped in
   a database transaction. Transactions ensure that a series of database operations are
   performed atomically (all or nothing). If an exception occurs within the method, the
   transaction will be rolled back, and changes to the database will be undone.
*/
@Transactional
public class StudentRepoService implements StudentRepoInterface {
    // Service implementation for the StudentRepoInterface

    @Autowired
    private StudentRepo studentRepo;
    // Autowired Student repository

    @Override
    public String registerStudent(long SUID, String firstName, String lastName, String emailID) {
        try {
            Student student = new Student();
            student.setId(SUID);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmailId(emailID);
            // Create a new Student entity.

            studentRepo.save(student);
            // Save the newly created student entity to the database.

            return "Student added successfully";
        } catch (Exception e) {
            return "Failed to register Student";
        }
    }
    
    @Override
    public List<Student> getAllStudents() {
        // Retrieve and return a list of all books from the repository.
        return studentRepo.findAll();
    }
}

