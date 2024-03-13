package service;

import controller.HomeController;
import controller.LoginController;
import dto.*;
import model.*;

import java.util.List;
import java.util.Scanner;
public class AccountService {
    Scanner scanner = new Scanner(System.in);
    List<Account> accountList = AccountDTO.readFile();

    public void createAccount() throws InterruptedException {
        System.out.println("-------Create Account------");
        System.out.println("Enter userName");
        boolean userNameExists = false;

        String userName;

        String USERNAME_REGEX = "^(?=.*[A-Z])(?=.*\\d).{6,}$";
        boolean isMatch = false;
        do {
            System.out.println("\033[34m" + "Username must be at least 6 characters, including at least 1 digit and 1 uppercase letter." + "\033[0m");
            userName = scanner.nextLine();
            if (userName.matches(USERNAME_REGEX)) {
                isMatch = true;
            } else {
                System.err.println("Invalid username.");
            }
        } while (!isMatch);
        for (Account a : accountList) {
            if (a.getUserName().equals(userName)) {
                userNameExists = true;
                break;
            }
        }
        if (userNameExists) {
            System.err.println("Username already exists. Please enter another username.");
            userNameExists = false;
        }
        String exceptPassword = "";
        boolean isExcept = false;
        do {
            System.out.println("Enter password");
            String inputPassword = scanner.nextLine();
            String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d).{6,}$";
            if (inputPassword.matches(PASSWORD_REGEX)) {
                isExcept = true;
                exceptPassword = inputPassword;
            } else {
                System.err.println("Invalid password.");
            }
            System.out.println("\033[34m" + "Password must be at least 6 characters, including at least 1 digit and 1 uppercase letter." + "\033[0m");
        }while(!isExcept);
        System.out.println("Enter your first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name");
        String lastName = scanner.nextLine();
        String role = "customer";
        double wallet = 0;
        boolean isValid = false;
        String phoneNumber;

        do {
            String PHONE_REGEX = "^0\\d{7,}$";
            System.out.println("Phone number must be at least 8 numbers and start with 0");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.matches(PHONE_REGEX)) {
                isValid = true;
            } else {
                System.err.println("Invalid phone number.");
            }
        }while(!isValid);
        Account newAccount = new Account(userName, exceptPassword, firstName, lastName, role, phoneNumber, wallet);
        accountList.add(newAccount);
        AccountDTO.writeFile(accountList);
        System.out.println("Create Successed");
        HomeController.load();
    }

    public static void changePassword() {
        List<Account> accountList = AccountDTO.readFile();
        Scanner scanner = new Scanner(System.in);
        boolean isMatching = false;
        do {
            System.out.println("Enter current password");
            String currentPassword = scanner.nextLine();

            for (Account a : accountList) {
                if (LoginController.currentAccount.equals(a.getUserName()) && a.getPassword().equals(currentPassword)) {
                    System.out.println("Enter new password");
                    String newPassword = scanner.nextLine();
                    a.setPassword(newPassword);
                    isMatching = true;
                    System.out.println("Password is changed");
                    break;
                }
            }
            if (!isMatching) {
                System.out.println("Password is wrong, enter again");
            }
            AccountDTO.writeFile(accountList);
        } while (!isMatching);
    }

}






