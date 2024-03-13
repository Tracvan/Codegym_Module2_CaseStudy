package view;
import controller.AdminController;
public class AdminMenu {
    public static void showMenu() throws InterruptedException {
        System.out.println("---------Menu----------");
        System.out.println("1.Add product");
        System.out.println("2.Edit product");
        System.out.println("3.Delete product");
        System.out.println("4.Find product by Name");
        System.out.println("5.Find product by Type");
        System.out.println("6.Show all products");
        System.out.println("7.Get code");
        System.out.println("8.View History");
        System.out.println("9.View Wallet");
        System.out.println("0. Exit");
        AdminController.controller();
    }
}





