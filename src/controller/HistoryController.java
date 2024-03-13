package controller;

import service.*;
import view.*;

import java.util.Scanner;

public class HistoryController {

    public static void controller() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean isExcept = false;
        do{
            System.out.println("Enter your choice");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    isExcept = true;
                    HistoryService.viewAllHistory();
                    AdminService.enterToBackMenu();
                    break;
                case "2":
                    isExcept = true;
                    HistoryService.filterByProductName();
                    AdminService.enterToBackMenu();
                    break;
                case "3":
                    isExcept = true;
                    HistoryService.filterByProductType();
                    AdminService.enterToBackMenu();
                    break;
                case "4":
                    isExcept = true;
                    HistoryService.filterByUserName();
                    AdminService.enterToBackMenu();
                    break;
                case "0":
                    NewPage.printNewPage();
                    AdminMenu.showMenu();
                    break;
                default:
                    System.err.println("Choose again");
            }
        }while(!isExcept);

    }
}




