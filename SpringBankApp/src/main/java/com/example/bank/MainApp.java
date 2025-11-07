package com.example.bank;

import com.example.bank.config.AppConfig;
import com.example.bank.service.BankService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        BankService bankService = context.getBean(BankService.class);

        Long fromAccountId = 1001L;
        Long toAccountId = 1002L;

        System.out.println("---- INITIAL STATE ----");
        bankService.printAccountDetails(fromAccountId);
        bankService.printAccountDetails(toAccountId);
        System.out.println("------------------------\n");

        // --- Test 1: Successful Transaction ---
        System.out.println("---- TEST 1: SUCCESSFUL TRANSFER ($50) ----");
        try {
            bankService.transferFunds(fromAccountId, toAccountId, 50.00, false);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        System.out.println("------------------------------------------\n");

        System.out.println("---- STATE AFTER SUCCESSFUL TRANSFER ----");
        bankService.printAccountDetails(fromAccountId);
        bankService.printAccountDetails(toAccountId);
        System.out.println("----------------------------------------\n");

        // --- Test 2: Failed Transaction (Rollback Test) ---
        System.out.println("---- TEST 2: FAILED TRANSFER ($100) with SIMULATED FAILURE ----");
        try {
            // This transfer will debit account 1001 but fail before crediting 1002
            bankService.transferFunds(fromAccountId, toAccountId, 100.00, true);
        } catch (Exception e) {
            System.err.println("CAUGHT EXPECTED ERROR: " + e.getMessage());
        }
        System.out.println("-------------------------------------------------------------\n");

        System.out.println("---- FINAL STATE (SHOULD BE SAME AS AFTER TEST 1) ----");
        bankService.printAccountDetails(fromAccountId); // Should be 950 (not 850)
        bankService.printAccountDetails(toAccountId); // Should be 1050 (not 1150)
        System.out.println("------------------------------------------------------\n");
        
        System.out.println("Check the database. Account 1001 should have 950 and Account 1002 should have 1050.");
        System.out.println("The debit from the failed transaction should have been rolled back.");

        context.close();
    }
}