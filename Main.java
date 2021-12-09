package com.company;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static double getUsd = 86;
    static Bank bank;
    static User loggedUser;
    Scanner scanner = new Scanner(System.in);

    public static void main() {
        ArrayList<User>users = new ArrayList<>();
        ArrayList<Account>accounts = new ArrayList<>();
        ArrayList<Transaction>transactions = new ArrayList<>();
        ArrayList<Account>accounts1 = new ArrayList<>();
        User abdulaziz = new User("Abdulaziz","Dzabarov","1","1",accounts1);
        Account KGZAbdulaziz = new Account(5000,"KGZ",abdulaziz);
        Account USDAbdulaziz = new Account(4000,"USD",abdulaziz);
        accounts1.add(KGZAbdulaziz);
        accounts1.add(USDAbdulaziz);
        accounts.addAll(accounts1);
        users.add(abdulaziz);

        ArrayList<Account>BillAccounts = new ArrayList<>();
        User bill = new User("Billy","Kitchen","2","2",BillAccounts);
        Account KGZBill = new Account(5000,"KGZ",bill);
        Account USDBill = new Account(5000,"USD",bill);
        BillAccounts.add(KGZBill);
        BillAccounts.add(USDBill);
        accounts.addAll(BillAccounts);
        users.add(bill);

        bank = new Bank("Demir",users,accounts,transactions);
        readingFile();
        login();






    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (true){
            try {
                System.out.println("Enter your login...");
                String login = scanner.nextLine();
                System.out.println("Enter your password...");
                String password = scanner.nextLine();
                while (true){
                    for (User user:bank.getCostumers()){
                        if (login.equals(user.getLogin()) && password.equals(user.getPassword())){
                            loggedUser = user;
                            MainMenu();
                            break;

                        }
                    }
                    if (count<2) {
                        count++;
                        System.err.println("-----INVALID LOGIN OR PASSWORD-----" +
                                "\n-----TRIES LEFT: " + (3 - count));
                    }else {
                        System.err.println("-----3 TRIES ENTERED-----");
                        System.err.println("-----YOU COULDNT ENTER,SHITTING DOWN-----");
                        System.exit(0);

                    }
                }

            }catch (IllegalAccessError e){
                e.printStackTrace();
                System.out.println("ERROR");
            }finally {
                System.out.println("dd");
            }


        }

    }

    private static void MainMenu() {

    }

    private static void readingFile() {
        try {
            ObjectInputStream oos = new ObjectInputStream(new FileInputStream("Bank"));
            bank = (Bank) oos.readObject();
            oos.close();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("COULDN'T READ FILE");

        }
    }
}
