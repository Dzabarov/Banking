package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private int id;
    private String firstName;
    private int lastName;
    private String login;
    private String password;
    private List<Account> accountList;
    static ArrayList<Integer> ids = new ArrayList<>();

    public User(String firstName, String lastName, String login, String password, List<Account> accountList) {
        this.id = genUniqueId();
        this.firstName = firstName;
        this.lastName = Integer.parseInt(lastName);
        this.login = login;
        this.password = password;
        this.accountList = accountList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(int lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<Account>getAccountLIst(){
        return (ArrayList<Account>) accountList;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static int genUniqueId() {
        int id = 0;
        while (true) {
            id = new Random().nextInt(1000);
            if (checkForDuplicates(id)) {
                ids.add(id);
                break;
            }
        }
        return id;
    }

    private static boolean checkForDuplicates(int id) {
        for (int i : ids) {
            return i != id;

        }
        return true;

    }
    static void getAllUsers(){
        for (User u:Main.bank.getCostumers()) {
            u.getInfo();
        }
    }

    private void getInfo() {
        System.out.println("-----User: " + this.firstName + " " + this.lastName + "-----");
        System.out.println("-----ID: " + this.id + "-----");
        System.out.println("-----Account detail's: " + "-----");
        System.out.println("-----------------------------------------------");
        for (Account account: Main.bank.getAccounts()) {
            if (account.getAccountHolder().getId()== id)
            account.getInfo();


        }
    }

}
