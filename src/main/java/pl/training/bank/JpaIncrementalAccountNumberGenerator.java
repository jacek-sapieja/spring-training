package pl.training.bank;

import pl.training.bank.service.IncrementalAccountNumberGenerator;

import javax.persistence.EntityManagerFactory;

public class JpaIncrementalAccountNumberGenerator extends IncrementalAccountNumberGenerator {

    private static final String GET_LAST_ACCOUNT_NUMBER = "select max(a.number) from Account a";

    public JpaIncrementalAccountNumberGenerator(EntityManagerFactory entityManagerFactory) {
        String lastAccountNumber = entityManagerFactory.createEntityManager()
                .createQuery(GET_LAST_ACCOUNT_NUMBER, String.class)
                .getSingleResult();
        setCounter(lastAccountNumber);
    }

}
