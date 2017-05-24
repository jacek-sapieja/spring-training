package pl.training.bank.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
public class AccountDto {

    private String number;
    private long balance;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

}
