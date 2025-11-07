package com.example.bank.dao;

import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // Marks this as a Spring bean for database operations
public class BankDaoImpl implements BankDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        // Gets the "current" session, which Spring manages
        // and binds to the transaction.
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Account getAccountById(Long accountId) {
        return getSession().get(Account.class, accountId);
    }

    @Override
    public void updateAccount(Account account) {
        getSession().update(account);
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        getSession().save(transaction);
    }
}