package tfiip.paf.day25.Models;

public class BankAccount {
    
    private Integer id;
    private String fullName;
    private Boolean isActive;
    private String acctType;
    private Float balance;
    
    public BankAccount() {
    }

    public BankAccount(Integer id, String fullName, Boolean isActive, String acctType, Float balance) {
        this.id = id;
        this.fullName = fullName;
        this.isActive = isActive;
        this.acctType = acctType;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    

    

}
