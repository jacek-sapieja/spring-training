package pl.training.bank.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.training.bank.entity.Customer;
import pl.training.bank.service.repository.CustomersRepository;

public class CustomersService implements UserDetailsService {

    private CustomersRepository customersRepository;

    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Customer customer = customersRepository.getByEmail(login);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return customer;
    }

}
