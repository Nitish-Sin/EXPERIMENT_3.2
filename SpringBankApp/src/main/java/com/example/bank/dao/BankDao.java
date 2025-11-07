package com.example.bank.dao;

import com.example.bank.model.Account;
import com.example.bank.model.Transaction;

public interface BankDao {
    
    Account getAccountById(Long accountId);
    
    void updateAccount(Account account);
    
    void saveTransaction(Transaction transaction);
}