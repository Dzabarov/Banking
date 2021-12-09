package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable {
   private String bankName;
   private final List<User>costumers;
   private final List<Account>accounts;
   private ArrayList<Transaction>transactions = new ArrayList<>();

    public Bank(String bankName, List<User> costumers, List<Account> accounts, ArrayList<Transaction> transactions) {
        this.bankName = bankName;
        this.costumers = costumers;
        this.accounts = accounts;
        this.transactions = transactions;
    }
    static void BankInfo(){
        System.out.println("-----DEMIR BANK'S INFORMATION");
        System.out.println("--------------------------------");
        System.out.println("ALL BANK CLIENTS AND ACCOUNTS");
        System.out.println("---------------------------------");
        User.getAllUsers();
        System.out.println("-----SUM OF ALL CLIENTS: " + Main.bank.getCostumers().size() + "-----");
        System.out.println("-----SUM OF ALL ACCOUNTS: " + Main.bank.getAccounts().size() + "-----");
    }
    public ArrayList<Transaction>getTransactions(){
        return transactions;

    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public List<User> getCostumers() {
        return costumers;
    }

    public List<Account> getAccounts() {
        return accounts;
    }


}
