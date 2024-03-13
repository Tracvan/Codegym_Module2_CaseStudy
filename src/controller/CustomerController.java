package controller;

import service.AccountService;
import service.Cartservice;
import service.CustomerService;
import service.ProductService;
import view.CustomerMenu;
import view.HomePage;
import view.NewPage;
import view.SortProductListMenu;

import java.util.Scanner;

public class CustomerController {

    public static void controller() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
            boolean isExcept = false;
            do{
                System.out.println("Enter your choice");
                String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    productService.showAllProduct();
                    SortProductListMenu.sortView();
                    break;
                case "2":
                    isExcept = true;
                    productService.findByName();
                    CustomerService.enterToBackMenu();
                    break;
                case "3":
                    isExcept = true;
                    productService.findByType();
                    CustomerService.enterToBackMenu();
                    break;
                case "4":
                    Cartservice.addToCart();
                    CustomerService.enterToBackMenu();
                    break;
                case "5":
                    Cartservice.viewMyCart();
                    CustomerService.enterToBackMenu();
                    break;
                case "6":
                    CustomerService.viewCustomerHistory();
                    CustomerService.enterToBackMenu();
                    break;
                case "7":
                    AccountService.changePassword();
                    CustomerService.enterToBackMenu();
                    break;
                case "8":
                    CustomerService.addFund();
                    CustomerService.enterToBackMenu();
                    break;
                case "9":
                    CustomerService.buyProduct();
                    CustomerService.enterToBackMenu();
                    break;
                case "10":
                    CustomerService.viewMyWallet();
                    CustomerService.enterToBackMenu();
                    break;
                case "0":
                    System.out.println("------Good Bye-----");
                    Thread.sleep(2000);
                    LoginController.currentAccount = null;
                    NewPage.printNewPage();
                    HomeController.load();
                    break;
                default:
                    System.err.println("Choose again");
            }
            }while(!isExcept);

    }
}




