package service;

import view.AdminMenu;
import view.NewPage;

import java.util.Scanner;

public class AdminService {
    public static void enterToBackMenu() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter key to back to menu page");
        scanner.nextLine();
        NewPage.printNewPage();
        AdminMenu.showMenu();
    }
}
