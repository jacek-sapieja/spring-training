package pl.training.bank.service;

import org.springframework.stereotype.Component;

@Generator(name = "b")
public class FakeAccountNumberGenerator implements AccountNumberGenerator {

    @Override
    public String getNext() {
        return "00000000000000000000000001";
    }

}
