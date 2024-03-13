package view;
import controller.HistoryController;
public class HistoryMenu {
    public static void showMenu() throws InterruptedException {
        System.out.println("---------Menu----------");
        System.out.println("1.View all history");
        System.out.println("2.Filter by product's name");
        System.out.println("3.Filter by product's type");
        System.out.println("4.View customer history");
        System.out.println("0. Exit");
        HistoryController.controller();
    }
}





