package view;

import controller.SortController;


public class SortProductListMenu {
    public static void sortView() throws InterruptedException {
        System.out.println("________________________________________________");
        System.out.println("1. Sort price : Low to Hight");
        System.out.println("2. Sort price :High to Low");
        System.out.println("3. Sort type: ");
        System.out.println("4. Back to menu");
        SortController.menu();


    }
}
