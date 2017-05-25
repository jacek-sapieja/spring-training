package pl.training.bank.service;

import pl.training.bank.entity.Customer;
import pl.training.bank.service.repository.CustomersRepository;

public class CustomersService {

    private CustomersRepository customersRepository;

    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public Customer add(Customer customer) {
        return customersRepository.save(customer);
    }

}
