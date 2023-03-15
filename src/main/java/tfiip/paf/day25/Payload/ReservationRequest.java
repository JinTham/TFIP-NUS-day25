package tfiip.paf.day25.Payload;

import java.util.List;

import tfiip.paf.day25.Models.Book;

public class ReservationRequest {
    private List<Book> books;
    private String borrower;

    public ReservationRequest() {
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public String getBorrower() {
        return borrower;
    }
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
    
}
