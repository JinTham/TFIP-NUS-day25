package tfiip.paf.day25.Services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tfiip.paf.day25.Models.Book;
import tfiip.paf.day25.Models.Reservation;
import tfiip.paf.day25.Models.ReservationDetails;
import tfiip.paf.day25.Repositories.BookRepository;
import tfiip.paf.day25.Repositories.ReservationDetailsRepository;
import tfiip.paf.day25.Repositories.ReservationRepository;

@Service
public class ReservationService {
    
    @Autowired
    BookRepository bookRepo;

    @Autowired
    ReservationRepository reservationRepo;

    @Autowired
    ReservationDetailsRepository reservationDetailsRepo;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public Boolean reserveBooks(List<Book> bookList, String borrower) {
        // check for book availability by quantity
        for (Book book : bookList) {
            if (book.getQuantity()<1){
                return false;
            }
        }

        // create reservation record
        Reservation reservation = new Reservation();
        reservation.setFullName(borrower);
        reservation.setReservationDate(Date.valueOf(LocalDate.now()));
        Integer reservationId = reservationRepo.create(reservation); //create a Reservation object and return its reservation id

        // minus book quantity and create the reservation details records for each book
        for (Book book : bookList) {
            bookRepo.update(book.getId());
            ReservationDetails reservationDetails = new ReservationDetails();
            reservationDetails.setBookId(book.getId());
            reservationDetails.setReservationId(reservationId);
            reservationDetailsRepo.create(reservationDetails);
        }
        return true;
    }

}
