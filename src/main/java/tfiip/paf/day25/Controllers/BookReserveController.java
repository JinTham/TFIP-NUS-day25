package tfiip.paf.day25.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tfiip.paf.day25.Payload.ReservationRequest;
import tfiip.paf.day25.Services.ReservationService;

@RestController
@RequestMapping(path="/api/reservations")
public class BookReserveController {
    
    @Autowired
    ReservationService reservationSvc;

    @PostMapping
    public ResponseEntity<Boolean> reserveBooks(@RequestBody ReservationRequest rq) {
        Boolean reserved = reservationSvc.reserveBooks(rq.getBookIdList(),rq.getBorrower());
        if (reserved) {
            return new ResponseEntity<Boolean>(reserved, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(reserved,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
