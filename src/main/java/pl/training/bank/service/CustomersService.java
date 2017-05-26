package pl.training.bank.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import pl.training.bank.entity.Customer;
import pl.training.bank.service.repository.CustomersRepository;

public class CustomersService {

    private CustomersRepository customersRepository;
    private PasswordEncoder passwordEncoder;

    public CustomersService(CustomersRepository customersRepository, PasswordEncoder passwordEncoder) {
        this.customersRepository = customersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer add(Customer customer) {
        encodePassword(customer);
        customer.setEnabled(true);
        return customersRepository.save(customer);
    }

    private void encodePassword(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
    }

}
