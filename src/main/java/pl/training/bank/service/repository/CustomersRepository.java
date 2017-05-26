package pl.training.bank.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.training.bank.entity.Customer;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Account a join Customer c where a.balance >= :balance")
    List<Customer> getCustomersWithBalance(@Param("balance") long balance);

    Customer getByEmail(String email);

}
