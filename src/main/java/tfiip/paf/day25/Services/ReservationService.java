package tfiip.paf.day25.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public Boolean reserveBooks(List<Integer> bookList, Reservation reservation) {
        Boolean reservationCompleted = false;
        // check for book availability by quantity
        Boolean allBooksAvailable = true;
        for (Integer bookId : bookList) {
            if (bookRepo.findById(bookId).getQuantity()<1){
                allBooksAvailable = false;
            }
        }
        // if books available, minus book quantity
        if (allBooksAvailable) {
            for (Integer id : bookList) {
                bookRepo.update(id);
            }
        }
        // create reservation record
        reservationRepo.create(reservation);

        // create the reservation details records
        ReservationDetails reservationDetails = new ReservationDetails(null, null, null);
        reservationDetailsRepo.create(reservationDetails);

        return reservationCompleted;
    }

}
