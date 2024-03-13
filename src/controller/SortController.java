package controller;

import service.CustomerService;
import service.ProductService;
import view.AdminMenu;
import view.CustomerMenu;

import java.util.Scanner;

public class SortController {
    public static void menu() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();

            System.out.println("Enter your choice");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    ProductService.sortPriceLowToHigh();
                    CustomerService.enterToBackMenu();
                    break;
                case "2":
                    ProductService.sortPriceHighToLow();
                    CustomerService.enterToBackMenu();
                    break;
                case "3":
                    productService.findByType();
                    CustomerService.enterToBackMenu();
                    break;
                case "4":
                    CustomerMenu.showMenu();
                    break;
            }
    }
}
