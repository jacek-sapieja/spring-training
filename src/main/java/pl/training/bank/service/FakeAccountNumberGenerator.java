package pl.training.bank.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Generator(name = "fake")
//@Service
public class FakeAccountNumberGenerator implements AccountNumberGenerator {

    @Override
    public String getNext() {
        return "00000000000000000000000001";
    }

}
