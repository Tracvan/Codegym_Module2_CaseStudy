package controller;

import dto.AccountDTO;
import model.Account;
import view.AdminMenu;
import view.CustomerMenu;
import view.HomePage;
import view.NewPage;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LoginController {
    Scanner scanner = new Scanner(System.in);
    List<Account> accountList = AccountDTO.readFile();
    public static String currentAccount;

    public void login() throws InterruptedException {
        boolean isSuceesed = false;
        System.out.println("-----Log in------");
        System.out.println("User name: ");
        String inputUserName = scanner.nextLine();
        System.out.println("Password: ");
        String inputPassword = scanner.nextLine();
        for (Account a : accountList) {
            if (a.getUserName().equals(inputUserName) && a.getPassword().equals(inputPassword)) {
                isSuceesed = true;
                currentAccount = inputUserName;
                if(a.getRole().equalsIgnoreCase("admin")){
                    System.out.println("Login Successful");
                    Thread.sleep(1000);
                    NewPage.printNewPage();
                    AdminMenu.showMenu();
                    break;
                }else if(a.getRole().equalsIgnoreCase("customer")){
                    System.out.println("Login Successful");
                    Thread.sleep(1000);
                    NewPage.printNewPage();
                    CustomerMenu.showMenu();
                    break;
                }
            }
        }if(!isSuceesed){
            System.err.println("Username Or Password is wrong");
            System.out.println();
            login();
        }
    }
    public static String getUserName(){
        List<Account> accounts = AccountDTO.readFile();
        StringBuilder outputName = new StringBuilder();
        for(Account a : accounts){
            if(currentAccount.equals(a.getUserName())){
                outputName.append(a.getFirstName()).append(a.getLastName());
            }
        }
        return outputName.toString();
    }
}
