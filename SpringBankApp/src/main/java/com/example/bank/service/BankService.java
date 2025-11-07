package com.example.bank.service;

public interface BankService {

    /**
     * Transfers funds from one account to another.
     *
     * @param fromAccountId The ID of the account to debit.
     * @param toAccountId   The ID of the account to credit.
     * @param amount        The amount to transfer.
     * @param simulateFailure If true, will throw an exception to test rollback.
     */
    void transferFunds(Long fromAccountId, Long toAccountId, double amount, boolean simulateFailure);
    
    void printAccountDetails(Long accountId);
}