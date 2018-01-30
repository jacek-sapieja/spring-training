package pl.training.bank.entity;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Account implements Serializable {

    private Long id;
    @NonNull
    private String number;
    private long balance;
    private List<Customer> customers = new ArrayList<>();

    public void deposit(long funds) {
        balance += funds;
    }

    public void withdraw(long funds) {
        balance -= funds;
    }


}