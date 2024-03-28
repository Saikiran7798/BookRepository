package edu.syr.bookrepository.serviceInterface;

import java.util.List;

import edu.syr.bookrepository.model.Student;

public interface StudentRepoInterface {
    // This interface defines a method for registering a new student.

    public String registerStudent(long SUID, String firstName, String lastName, String emailID);
    // Register a new student with the provided 'SUID', 'firstName', 'lastName', and 'emailID'.

	List<Student> getAllStudents();
}

