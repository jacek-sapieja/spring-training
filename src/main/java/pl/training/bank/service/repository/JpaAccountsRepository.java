package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class JpaAccountsRepository implements AccountsRepository {

    private static final String GET_BY_NUMBER_QL = "select a from Account a where number = :number";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account save(Account account) {
        entityManager.persist(account);
        entityManager.flush();
        entityManager.refresh(account);
        return account;
    }

    @Override
    public Account getByNumber(String sourceAccountNumber) {
        try {
            return entityManager
                    .createQuery(GET_BY_NUMBER_QL, Account.class)
                    .setParameter("number", sourceAccountNumber)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public void update(Account account) {
        getByNumber(account.getNumber());
        entityManager.merge(account);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
