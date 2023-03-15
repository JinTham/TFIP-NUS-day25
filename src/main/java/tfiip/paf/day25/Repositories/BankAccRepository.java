package tfiip.paf.day25.Repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import tfiip.paf.day25.Models.BankAccount;

@Repository
public class BankAccRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_CHECK_BALANCE = "select balance from bank_account where id = ?";
    private final String SQL_GET_ACCOUNT = "select * from bank_account where id = ?";
    private final String SQL_WITHDRAW = "update bank_account set balance = balance - ? where id = ?";
    private final String SQL_DEPOSIT = "update bank_account set balance = balance + ? where id = ?";
    private final String SQL_CREATE_ACCOUNT = "insert into bank_account (full_name, is_active, acct_type, balance) values (?,?,?,?)";

    public Boolean checkBalance(Integer accountId, Float withdrawnAmount) {
        Boolean balanceEnough = false;
        Float balance = jdbcTemplate.queryForObject(SQL_CHECK_BALANCE, Float.class, accountId);
        if (withdrawnAmount <= balance) {
            balanceEnough = true;
        }
        return balanceEnough;
    }

    public BankAccount getAccountDetails(Integer accountId) {
        BankAccount bankAcc = jdbcTemplate.queryForObject(SQL_GET_ACCOUNT, BeanPropertyRowMapper.newInstance(BankAccount.class), accountId);
        return bankAcc;
    }

    public Boolean withdraw(Integer accountId, Float withdrawnAmount) {
        Boolean withdrawn = false;
        int updated = jdbcTemplate.update(SQL_WITHDRAW, withdrawnAmount, accountId);
        if (updated > 0) {
            withdrawn = true;
        }
        return withdrawn;
    }

    public Boolean deposit(Integer accountId, Float depositAmount) {
        Boolean deposit = false;
        int updated = jdbcTemplate.update(SQL_DEPOSIT, depositAmount, accountId);
        if (updated > 0) {
            deposit = true;
        }
        return deposit;
    }

    public Boolean createAccount(BankAccount bankAcc) {
        Boolean created = false;
        jdbcTemplate.execute(SQL_CREATE_ACCOUNT, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1,bankAcc.getFullName());
                ps.setBoolean(2,bankAcc.getIsActive());
                ps.setString(3,bankAcc.getAcctType());
                ps.setFloat(4,bankAcc.getBalance());
                Boolean result = ps.execute();
                return result;
            }
        });
        return created;
    }
}
