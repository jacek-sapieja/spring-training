package pl.training.bank.service.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.training.bank.entity.Account;

public class HibernateAccountsRepository implements AccountsRepository {

    private static final String GET_BY_NUMBER_QL = "select a from Account a where number = :number";

    private SessionFactory sessionFactory;

    public HibernateAccountsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Account save(Account account) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(account);
        account.setId(id);
        return account;
    }

    @Override
    public Account getByNumber(String sourceAccountNumber) {
        return sessionFactory.getCurrentSession()
                .createQuery(GET_BY_NUMBER_QL, Account.class)
                .setParameter("number", sourceAccountNumber)
                .uniqueResultOptional()
                .orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public void update(Account account) {
        getByNumber(account.getNumber());
        sessionFactory.getCurrentSession().update(account);
    }

}
