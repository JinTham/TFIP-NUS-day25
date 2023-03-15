package tfiip.paf.day25.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tfiip.paf.day25.Payload.TransferRequest;
import tfiip.paf.day25.Services.BankAccService;

@RestController
@RequestMapping(path="/accounts")
public class BankAccController {
    
    @Autowired
    BankAccService bankAccSvc;

    @PostMapping
    public ResponseEntity<Boolean> transferMoney(@RequestBody TransferRequest transferRequest) {
        Boolean transferred = false;
        transferred = bankAccSvc.transferMoney(transferRequest.getAccountFrom(), transferRequest.getAccountTo(), transferRequest.getAmount());
        if (transferred) {
            return new ResponseEntity<Boolean>(transferred, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(transferred, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
