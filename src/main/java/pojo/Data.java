package pojo;

import java.math.BigInteger;
import java.util.List;

public class Data {

    private String accountNumber;
    private BigInteger balance;
    private List<String> cardNumbers;

    public String getAccountNumber() {
        return accountNumber;
    }

    public Data setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public Data setBalance(BigInteger balance) {
        this.balance = balance;
        return this;
    }

    public List<String> getCardNumbers() {
        return cardNumbers;
    }

    public Data setCardNumbers(List<String> cardNumbers) {
        this.cardNumbers = cardNumbers;
        return this;
    }
}
