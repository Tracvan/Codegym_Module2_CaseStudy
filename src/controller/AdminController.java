package controller;
import service.*;
import view.EditProductMenu;
import view.HistoryMenu;

import java.util.Scanner;

public class AdminController {

    public static void controller() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
            System.out.println("Enter your choice");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    productService.add();
                    AdminService.enterToBackMenu();
                    break;
                case "2":
                    EditProductMenu.editProduct();
                    AdminService.enterToBackMenu();
                    break;
                case "3":
                    productService.delete();
                    AdminService.enterToBackMenu();
                    break;
                case "4":
                    productService.findByName();
                    AdminService.enterToBackMenu();
                    break;
                case "5":
                    productService.findByType();
                    AdminService.enterToBackMenu();

                    break;
                case "6":
                    productService.showAllProduct();
                    AdminService.enterToBackMenu();

                    break;
                case "7":
                    CodeService.createCode();
                    AdminService.enterToBackMenu();
                case "8":
                    HistoryMenu.showMenu();
                    AdminService.enterToBackMenu();
                case "9":
                    HistoryService.viewAdminWallet();
                    AdminService.enterToBackMenu();
                case "0":
                    LoginController.currentAccount = null;
                    System.out.println("------Good Bye-----");
                    HomeController.load();
            }
    }
}



