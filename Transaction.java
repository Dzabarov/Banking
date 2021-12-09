package com.company;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Transaction implements Serializable {
    private final double amount;
    private final String timeStamp;
    private final Account account;
    private String typeTransactions;
    static Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-eee");

    public Transaction(String typeTransactions, double amount, String timeStamp, Account account) {
        this.typeTransactions = typeTransactions;
        this.amount = amount;
        this.timeStamp = timeStamp;
        this.account = account;
    }

    void getInfo() {
        System.out.println("-----USER: " + getAccount().getAccountHolder().getFirstName() + " " + getAccount().getAccountHolder().getLastName() + "-----");
        System.out.println("-----OPERATION TYPE: " + typeTransactions + "-----");
        System.out.println("-----TRANSACTION SUM: " + getAmount() + "-----");
        System.out.println("-----BALANCE AFTER TRANSAKTION: " + (getAccount().getBalance()) + "-----");
        System.out.println("-----TRANSAKTION DATE: " + getTimeStamp() + "-----");
        System.out.println("-----BILL'S TYPE: " + getAccount().getName() + "-----");
        System.out.println("-----BILL'S ID: " + getAccount().getId() + "-----");
        System.out.println("------------------------------------------------------");
    }

    static void informationTransaction() {
        boolean mistake = false;
        while (true) {
            Account.accounts();
            for (Transaction transaction : Main.bank.getTransactions()) {
                if (Main.loggedUser.getId() == transaction.getAccount().getAccountHolder().getId()) {
                    transaction.getInfo();
                    mistake = true;
                }
            }
            if (mistake) {
                break;
            }
            if (!mistake) {
                System.err.println("-----YOU DONT HAVE TRANSACTION'S-----");
                break;
            }
        }

    }

    static void withDrawMoney() {
        while (true) {
            try {
                Account.accounts();
                System.out.println("-----ENTER YOUR ACCOUNT'S ID-----");
                int id = scanner.nextInt();
                for (Account account : Main.loggedUser.getAccountLIst()) {
                    if (id == account.getId() && account.getName().equals("KGZ")) {
                        withDrawMoneySettignKGZ(account);
                    } else if (id == account.getId() && account.getName().equals("USD")) {
                        withDrawMoneySettignUSD(account);
                    }
                }
            } catch (Exception e) {
                System.err.println("-----ERROR!-----");
                System.err.println("-----INVALID INPUT FORMAT-----");
                scanner.next();
            }
        }
    }

    public static void withDrawMoneyKGZ(Account account) {
    }

    public static void withDrawMoneyNO(Account account) {
        while (true) {
            try {
                System.out.println("-----ENTER SUM-----");
                double sum = scanner.nextDouble();
                if (sum > account.getBalance()) {
                    System.out.println("-----YOU DON'T HAVE ENOUGH MONEY TO TAKE SO MONEY-----");
                } else {
                    double newBalance = account.getBalance() - sum;
                    account.setBalance(newBalance);
                    Transaction transaction = new Transaction("withdrawal from an account", sum, sdf.format(new Date()), account);
                    Main.bank.getTransactions().add(transaction);
                    transaction.getInfo();
                    Main.main();


                }

            } catch (Exception e) {
                System.out.println("-----ERROR!-----");
                System.out.println("-----INVALID INPUT FORMAT-----");
                scanner.next();

            }
        }

    }


    private static void withDrawMoneyUSD(Account account) {
        while (true) {
            try {
                System.out.println("-----ENTER SUM-----");
                double sum = scanner.nextDouble();
                System.out.println("-----YOU'RE TAKING MONEY FROM KGZ TO USD-----");
                System.out.println("-----SUM'LL BE REDUCED FROM USD-----");
                sum = sum / Main.getUsd;
                sum = Math.round(sum);
                if (sum > account.getBalance()) {
                    System.out.println("-----YOU DON'T HAVE ENOUGH MONEY TO MAKE TRANSACTION-----");
                    Main.main();
                } else {
                    double newBalnce = scanner.nextDouble();
                    account.setBalance(newBalnce);
                    Transaction transaction = new Transaction("withdrawal from an account", sum, sdf.format(new Date()), account);
                    Main.bank.getTransactions().add(transaction);

                }
            } catch (Exception e) {
                System.out.println("-----ERROR!-----");
                System.out.println("-----INVALID INPUT FORMAT-----");
                scanner.next();
            }
        }

    }

    private static void withDrawMoneySettignUSD(Account account) {
        while (true) {
            try {
                account.getInfo();
                System.out.println("-----BALANCE: " + account.getBalance() + "-----");
                System.out.println("----------------------------------------------");
                System.out.println("-----ENTER 1 TO WITHDRAW MONEY IN USD-----");
                System.out.println("-----ENTER 2 TO WITHDRAW MONEY IN KGZ-----");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        withDrawMoneyNO(account);
                    case 2:
                        withDrawMoneyKGZ(account);

                }
            } catch (Exception e) {
                System.out.println("-----ERROR!");
                System.out.println("-----INVALID INPUT FORMAT-----");
                scanner.next();
            }
        }
    }

    private static void withDrawMoneySettignKGZ(Account account) {
        while (true) {
            try {
                account.getInfo();
                System.out.println("-----BALANCE: " + account.getBalance() + "-----");
                System.out.println("----------------------------------------------");
                System.out.println("-----ENTER 1 TO WITHDRAW MONEY IN USD-----");
                System.out.println("-----ENTER 2 TO WITHDRAW MONEY IN KGZ-----");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        withDrawMoneyUSD(account);
                    case 2:
                        withDrawMoneyNO(account);


                }
            } catch (Exception e) {
                System.out.println("-----ERROR!");
                System.out.println("-----INVALID INPUT FORMAT-----");
                scanner.next();
            }
        }
    }

    private static void transferOfFundsKgz(Account account) {
        System.out.println("-----ENTER SUM TO REFILL-----");
        double summa = scanner.nextDouble();
        System.out.println("-----YOU'RE REFILLING USD ACCOUNT FROM KGZ-----");
        System.out.println("-----SUM'LL REFILL FROM KGZ-----");
        summa = summa / Main.getUsd;
        summa = Math.round(summa);
        double f = account.getBalance() + summa;
        account.setBalance(f);
        Transaction transaction = new Transaction("ACCOUNT REFILLING", summa, sdf.format(new Date()), account);
        Main.bank.getTransactions().add(transaction);
        transaction.getInfo();
        Main.main();
    }

    private static void transferOFFundsNO(Account account) {
        System.out.println("-----ENTER REFILL SUM-----");
        double sum = scanner.nextDouble();
        double f = account.getBalance();
        Transaction transaction = new Transaction("ACCOUNT REFILLING", sum, sdf.format(new Date()), account);
        Main.bank.getTransactions().add(transaction);
        Main.main();
    }

    private static void transferOfFundsUSD(Account account) {
        while (true) {
            try {
                account.getInfo();
                System.out.println("-----BALANCE: " + account.getBalance() + "-----");
                System.out.println("-----------------------------");
                System.out.println("-----ENTER 1 TO REFILL MONEY KGZ-----");
                System.out.println("-----ENTER 2 TO REFILL MONEY USD-----");
                int sum = scanner.nextInt();
                switch (sum) {
                    case 1:
                        transferOFFundsNO(account);
                    case 2:
                        transferOfFundsKGZ(account);

                }
            } catch (Exception e) {
                System.out.println("-----ERROR!-----");
                System.out.println("-----INVALID INPUT FORMAT-----");
                scanner.next();
            }
        }
    }

    private static void transferOfFundsKGZ(Account account) {
        while (true) {
            try {
                account.getInfo();
                System.out.println("-----BALANCE: " + account.getBalance() + "-----");
                System.out.println("----------------------------");
                System.out.println("-----ENTER 1 TO REFILL FROM USD-----");
                System.out.println("-----ENTER 2 TO REFILL FROM KGZ-----");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        transferOfFundsUSD(account);
                    case 2:
                        transferOFFundsNO(account);

                }

            } catch (Exception e) {
                System.out.println("-----ERROR!-----");
                System.out.println("-----INVALID INPUT FORMAT-----");
                scanner.next();
            }
        }
    }

    static void transferofFunds() {
        while (true) {
            try {
                Account.accounts();
                System.out.println("-----ENTER YOUR ACCOUNT ID-----");
                int id = scanner.nextInt();
                for (Account account : Main.loggedUser.getAccountLIst()) {
                    if (id == account.getId() && account.getName().equals("USD")) {
                        transferOfFundsUSD(account);

                    }
                }
            } catch (Exception e) {
                System.out.println("-----ERROR!-----");
                System.out.println("-----INVALID INPUT FORMAT-----");
                scanner.next();
            }
        }
    }

    static void topUpAccount() {
        while (true) {
            try {
                Account.accounts();
                System.out.println("-----ENTER YOUR ACCOUNT ID-----");
                int i = scanner.nextInt();
                for (Account a : Main.loggedUser.getAccountLIst()) {
                    if (i == a.getId()) {
                        Account.getAllAccounts((ArrayList<Account>) Main.bank.getAccounts());
                        System.out.println("-----ENTER ACCOUNT ID THAT YOU WANT TO PAY MONEY-----");
                        int sum = scanner.nextInt();
                        for (Account account : Main.bank.getAccounts()) {
                            if (sum == account.getId() && sum != a.getId()) {
                                account.getInfo();
                                System.out.println("-----ENTER TRANSACTION SUM-----");
                                double summ = scanner.nextDouble();
                                if (summ > a.getBalance()) {
                                    System.out.println("-----DON'T ENOUGH MONEY TO MAKE TRANSACTION-----");
                                    Main.main();

                                } else {
                                    if (a.getName().equals("KGZ") && account.getName().equals("USD")) {
                                        System.out.println("-----YOR'RE TRANSFERING FROM KGZ TO USD");
                                        System.out.println("-----SUM OF TRANSFER WILL REDUCED FROM USD");
                                        summ = summ / Main.getUsd;
                                        summ = Math.round(summ);
                                        if (summ > a.getBalance() - summ) {
                                            System.out.println("-----NOT ENOUGH MONEY ON BALANCE");
                                            Main.main();
                                        }
                                        double minus = scanner.nextDouble();
                                        double popolnenie = scanner.nextDouble();
                                        a.setBalance(minus);
                                        account.setBalance(popolnenie);
                                        Transaction transaction = new Transaction("TRANSFERING(CAME)", summ, sdf.format(new Date()), account);
                                        Transaction transaction1 = new Transaction("TRANSFERING(SeND)", summ, sdf.format(new Date()), account);
                                        Main.bank.getTransactions().add(transaction);
                                        Main.bank.getTransactions().add(transaction1);
                                        System.out.println("-----MONEY TRANSFERED-----");
                                        transaction.getInfo();
                                        System.out.println("-----MONEY CAME-----");
                                        transaction1.getInfo();
                                        Main.bank.getTransactions().add(transaction);
                                        Main.bank.getTransactions().add(transaction1);
                                        Main.main();


                                    } else if (a.getName().equals("USD") && account.getName().equals("KGZ"))
                                        System.out.println("-----YOU'RE TRANSFERING FROM USD TO KGZ-----");
                                    System.out.println("-----SUM OF TRANSFER WILL REFILL FROM USD-----");
                                    summ = summ / Main.getUsd;
                                    summ = Math.round(summ);
                                    double f = a.getBalance();
                                    double popolnenie = account.getBalance() + summ;
                                    a.setBalance(f);
                                    account.setBalance(popolnenie);
                                    Transaction transactionM = new Transaction("TRANSFERING(CAME)", summ, sdf.format(new Date()), account);
                                    Transaction transactionP = new Transaction("TRANSFERING(SEND)", summ, sdf.format(new Date()), account);
                                    System.out.println("-----MONEY TRANSFERED-----");
                                    transactionM.getInfo();
                                    System.out.println("-----MONEY CAME-----");
                                    transactionP.getInfo();
                                    Main.bank.getTransactions().add(transactionM);
                                    Main.bank.getTransactions().add(transactionP);
                                    Main.main();

                                }

                            }
                        }

                    }
                }
            }catch (Exception e){
                System.out.println("-----ERROR!-----");
                System.out.println("-----INVALID INPUT FORMAT-----");
                scanner.next();
            }
        }
    }

    public double getAmount() {
        return amount;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Account getAccount() {
        return account;
    }
}

