package edu.syr.bookrepository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Book {
    // Entity representing a Book in the database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId; // Unique identifier for the book
    private String title; // Title of the book
    private String author; // Author of the book
    private String ISBN; // International Standard Book Number
    private String edition; // Edition of the book
    private double initialPrice; // Initial price of the book
    private int soldCount; // Number of copies sold
    private double currentPrice; // Current price of the book

    @Enumerated(EnumType.STRING)
    private Status availability; // Enum representing the availability status of the book

    public enum Status {
        Available, // The book is available
        NotAvailable // The book is not available
    }

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student SUID; // Student who owns the book

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Status getAvailability() {
        return availability;
    }

    public void setAvailability(Status availability) {
        this.availability = availability;
    }

    public Student getSUID() {
        return SUID;
    }

    public void setSUID(Student sUID) {
        SUID = sUID;
    }
}
