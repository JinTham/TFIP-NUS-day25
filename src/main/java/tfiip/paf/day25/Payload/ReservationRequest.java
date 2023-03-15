package tfiip.paf.day25.Payload;

import java.util.List;

public class ReservationRequest {
    private List<Integer> bookIdList;
    private String borrower;

    public ReservationRequest() {
    }

    public List<Integer> getBookIdList() {
        return bookIdList;
    }
    public void setBooks(List<Integer> bookIdList) {
        this.bookIdList = bookIdList;
    }
    public String getBorrower() {
        return borrower;
    }
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
    
}
