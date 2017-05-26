package pl.training.bank.service;

import org.hibernate.SessionFactory;

public class HibernateIncrementalAccountNumberGenerator extends IncrementalAccountNumberGenerator {

    private static final String GET_LAST_ACCOUNT_NUMBER = "select max(a.number) from Account a";

    public HibernateIncrementalAccountNumberGenerator(SessionFactory sessionFactory) {
        String lastAccountNumber = (String) sessionFactory.openSession()
                .createQuery(GET_LAST_ACCOUNT_NUMBER)
                .uniqueResult();
        setCounter(lastAccountNumber);
    }

}
