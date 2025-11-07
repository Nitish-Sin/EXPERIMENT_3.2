package com.example.bank.service;

import com.example.bank.dao.BankDao;
import com.example.bank.exception.InsufficientFundsException;
import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service // Marks this as a Spring bean for business logic
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;

    @Override
    @Transactional(rollbackFor = Exception.class) // This is the magic!
    public void transferFunds(Long fromAccountId, Long toAccountId, double amount, boolean simulateFailure) {
        System.out.println("Attempting to transfer " + amount + " from " + fromAccountId + " to " + toAccountId);

        // 1. Get the 'from' account
        Account fromAccount = bankDao.getAccountById(fromAccountId);
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in account " + fromAccountId);
        }

        // 2. Debit the 'from' account
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        bankDao.updateAccount(fromAccount);
        System.out.println("Debited " + amount + " from account " + fromAccountId);

        // 3. Simulate a failure *after* the first update, but *before* the second
        if (simulateFailure) {
            System.out.println("!!! SIMULATING FAILURE !!!");
            throw new RuntimeException("Simulated system failure! Transaction must be rolled back.");
        }

        // 4. Get the 'to' account
        Account toAccount = bankDao.getAccountById(toAccountId);

        // 5. Credit the 'to' account
        toAccount.setBalance(toAccount.getBalance() + amount);
        bankDao.updateAccount(toAccount);
        System.out.println("Credited " + amount + " to account " + toAccountId);

        // 6. Log the transaction
        Transaction transaction = new Transaction();
        transaction.setFromAccountId(fromAccountId);
        transaction.setToAccountId(toAccountId);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        bankDao.saveTransaction(transaction);
        
        System.out.println("Transfer successful. Transaction logged.");
    }

    @Override
    @Transactional(readOnly = true) // A read-only transaction is more efficient
    public void printAccountDetails(Long accountId) {
        Account account = bankDao.getAccountById(accountId);
        System.out.println(account);
    }
}