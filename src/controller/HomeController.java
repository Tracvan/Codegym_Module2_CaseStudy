package controller;

import service.AccountService;
import view.HomePage;
import view.NewPage;

import java.util.Scanner;

public class HomeController {


    public static void load() throws InterruptedException {
        AccountService accountService = new AccountService();
        LoginController loginController = new LoginController();
        boolean isValid = true;
        do {
            HomePage.login();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    NewPage.printNewPage();
                    loginController.login();
                    isValid = false;
                    break;
                case "2":
                    accountService.createAccount();
                    isValid = false;
                    break;
                case "0":
                    System.out.println("------Good Bye-----");
                    LoginController.currentAccount = null;
                    isValid = false;
                    System.exit(0);
            }
        }
            while (isValid) ;
        }
}


