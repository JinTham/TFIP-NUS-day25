package tfiip.paf.day25.Models;

public class ReservationDetails {
    private Integer id;
    private Integer bookId;
    private Integer reservationId;

    public ReservationDetails() {
    }
    public ReservationDetails(Integer id, Integer bookId, Integer reservationId) {
        this.id = id;
        this.bookId = bookId;
        this.reservationId = reservationId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public Integer getReservationId() {
        return reservationId;
    }
    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

}
