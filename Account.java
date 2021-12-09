package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private int id;
    private double balance;
    private String name;
    private static User accountHolder;
    private List<Transaction>transactionList;
    static ArrayList<Transaction>transactions = new ArrayList<>();

    public Account(double balance, String name, User accountHolder) {

        this.balance = balance;
        this.name = name;
        this.accountHolder = accountHolder;
        this.transactionList = transactionList;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public User getAccountHolder() {
        return accountHolder;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
    static void getAllAccounts(ArrayList<Account>accounts){
        for (Account a:accounts){
            a.getInfo();
        }

    }

    public void getInfo() {
        System.out.println("-----BILL'S ID: " + this.getId() + "-----");
        System.out.println("-----BILL'S TYPE: " + this.getName() + "-----");
        System.out.println("-----BILL'S USER: " + accountHolder.getFirstName() + " " + accountHolder.getLastName() + "-----");
        System.out.println("-------------------------------------");

    }
    static void accounts(){
        System.out.println("-----INFORMATION OF ACCOUNT USER'S-----");
        System.out.println("---------------------------------------");
        for (Account a: Main.loggedUser.getAccountLIst()) {
            System.out.println("-----BILL'S ID:" + a.getId() + "-----");
            System.out.println("-----BILL'S TYPE: " + a.getName() + "-----");
            System.out.println("-----BILL'S BALANCE:" + a.getBalance() + "-----");
            System.out.println("-----BILL'S USER: " + accountHolder.getFirstName() + " " + accountHolder.getLastName() + "-----");
            System.out.println("----------------------------------------");

        }
    }
}
