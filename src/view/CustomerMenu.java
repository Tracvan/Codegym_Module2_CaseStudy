package view;

import controller.CustomerController;
import controller.LoginController;

public class CustomerMenu {
    public static void showMenu() throws InterruptedException {
        System.out.println("---------"+ LoginController.getUserName() +"----------");
        System.out.println("1.View Product List");
        System.out.println("2.Search Product By Name");
        System.out.println("3.Search Product By Type Of Product");
        System.out.println("4.Add To Cart");
        System.out.println("5.View My Cart");
        System.out.println("6.View My History");
        System.out.println("7.Change Password");
        System.out.println("8.Add fund");
        System.out.println("9.Buy");
        System.out.println("10.View my wallet");
        System.out.println("0. Logout");
        CustomerController.controller();
    }
}