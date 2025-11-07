package com.example.bank.exception;

// A RuntimeException is crucial. By default, Spring only rolls back
// transactions for RuntimeExceptions (and not checked exceptions).
public class InsufficientFundsException extends RuntimeException {
    
    public InsufficientFundsException(String message) {
        super(message);
    }
}