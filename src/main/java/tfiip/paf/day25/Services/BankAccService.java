package tfiip.paf.day25.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import tfiip.paf.day25.Models.BankAccount;
import tfiip.paf.day25.Repositories.BankAccRepository;

@Service
public class BankAccService {

    @Autowired
    BankAccRepository bankAccRepo;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Boolean transferMoney(Integer accountFrom, Integer accountTo, Float amount) {

        // check if both accounts are valid and active
        BankAccount withdrawalAcc = bankAccRepo.getAccountDetails(accountFrom);
        Boolean withdrawalAccValid = withdrawalAcc.getIsActive();
        BankAccount depositAcc = bankAccRepo.getAccountDetails(accountTo);
        Boolean depositAccValid = depositAcc.getIsActive();
        
        Boolean withdrawn = false;
        Boolean deposited = false;
        if (depositAccValid && withdrawalAccValid) {
            // check withdrawal account has enough money
            if (bankAccRepo.checkBalance(withdrawalAcc.getId(), amount)) {
                // perform the withdrawal (requires transaction)
                withdrawn = bankAccRepo.withdraw(accountFrom, amount);
                // perform the deposit (requires transaction)
                deposited = bankAccRepo.deposit(accountTo, amount);
            } else {
                throw new IllegalArgumentException("Withdrawal failed!!!", null);
            }
        }
        // check transactions successful and return
        return (withdrawn && deposited);
    }
    
}
