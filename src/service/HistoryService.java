package service;
import dto.HistoryDTO;
import dto.ProductDTO;
import model.Bill;
import model.CartItem;
import model.Product;

import java.util.*;

public class HistoryService {

    public static void viewAllHistory() {
        List<Bill> historyList = HistoryDTO.readFile();
        double total = 0;
        for (Bill bill : historyList) {

            for (CartItem cartItem : bill.getBoughtList()) {
                double singleBillTotal = 0;
                singleBillTotal += cartItem.getTotalAmount();
                System.out.println("____________________________________***___________________________________________________");
                System.out.printf("|%-12s |%-20s |%-8s |%-10s |%-12s |%-15s |\n", "Time", "Product's Name", "Quantity", "Unit Price", "Total Amount", "Username");

                total += cartItem.getTotalAmount();
                System.out.printf("|%-12s |%-20s |%-8d |%-10.2f |%-12.2f |%-15s |\n", cartItem.getNow(), cartItem.getName(), cartItem.getQuanity(), cartItem.getUnitPrice(), cartItem.getTotalAmount(), cartItem.getUsername());
                System.out.printf("|%-12s |%-20s |%-8s |%-10s |%-12.2s |%-15s |\n", "", "", "", "", "------", "");
                System.out.printf("|%-12s |%-20s |%-8s |%-10s |%-12.2f |%-15s |\n", "", "", "", "Total", singleBillTotal, "");
                System.out.println("____________________________________***___________________________________________________");
            }
        }
        System.out.println("|-------------|---------------------|---------|----------|--------------|----------------|");
        System.out.printf("|%-12s |%-20s |%-8s |%-10s |%-12.2f |%-15s |\n", "", "", "", "Total", total, "");
        System.out.println("|-------------|---------------------|---------|----------|--------------|----------------|");
    }

    public static void filterByProductName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of product: ");
        String name = scanner.nextLine();
        boolean isMatch = false;

        List<Bill> historyList = HistoryDTO.readFile();
        double total = 0;
        int quantityTotal = 0;

        System.out.println("_____________________________________________________________________________");
        System.out.printf("|%-12s |%-20s |%-8s |%-10s |%-12s |%-10s |\n", "Time", "Product's Name", "Quantity", "Unit Price", "Total Amount", "Username");
        System.out.println("|-------------|---------------------|---------|-----------|--------------|----------|");

        for (Bill bill : historyList) {
            for (CartItem cartItem : bill.getBoughtList()) {
                if (cartItem.getName().toLowerCase().trim().equals(name.toLowerCase())) {
                    isMatch = true;
                    total += cartItem.getTotalAmount();
                    quantityTotal += cartItem.getQuanity();
                    System.out.printf("|%-12s |%-20s |%-8d |%-10.2f |%-12.2f |%-10s |\n", cartItem.getNow(), cartItem.getName(), cartItem.getQuanity(), cartItem.getUnitPrice(), cartItem.getTotalAmount(), cartItem.getUsername());
                }
            }
        }
        if (isMatch) {
            System.out.println("|-------------|---------------------|--------------|-----------|--------------|----------|");
            System.out.println("|-------------|---------------------|Total Quantity|-----------|Amount|------------------|");
            System.out.printf("|%-12s |%-20s |%-8s |%-10s |%-12.2f |%-10s |\n", "", "", quantityTotal, "", total, "");
            System.out.println("|-------------|---------------------|--------------|-----------|--------------|----------|");
        }
        if (!isMatch) {
            System.err.println("User not found");
        }
    }

    public static void filterByProductType() {
        List<Product> products = ProductDTO.readFile();
        Scanner scanner = new Scanner(System.in);
        List<Bill> bills = HistoryDTO.readFile();
        List<String> typeList = new ArrayList<>();
        Map<String, String> typeMap = new HashMap<>();
        for (Product p : products) {
            boolean isExist = false;
            String type = p.getType();
            for (String s : typeList) {
                if (s.equals(type)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                typeList.add(type);
            }
        }
        System.out.println("Which type of product do you want to find");
        for (String t : typeList) {
            int number = typeList.indexOf(t) + 1;
            String key = "" + number;
            typeMap.put(key, t);
            System.out.println(key + ". " + typeMap.get(key));
        }
        System.out.println("Enter your choice");
        String choice = scanner.nextLine();
        System.out.println("Number  Product's Name    Quantity  Price   Type");
        System.out.println("---------------------------------------------------");
        boolean isMatch = false;
        for (Bill bill : bills) {
            for (CartItem cartItem : bill.getBoughtList())
                if (cartItem.getType().equals(typeMap.get(choice))) {
                    isMatch = true;
                    String formattedString = String.format("%-7d %-16s %-9d %.2f   %s", cartItem.getId(), cartItem.getName(), cartItem.getQuanity(), cartItem.getUnitPrice(), cartItem.getType());
                    System.out.println(formattedString);
                }
        }
        if (isMatch) {
            System.out.println("---------------------------------------------------");
        } else {
            System.err.println("Product not found");
        }
    }

    public static void filterByUserName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of username: ");
        String name = scanner.nextLine();
        boolean isMatch = false;

        List<Bill> historyList = HistoryDTO.readFile();
        double total = 0;
        int quantityTotal = 0;

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-20s | %-8s | %-10s | %-12s | %-10s |\n", "Time", "Product's Name", "Quantity", "Unit Price", "Total Amount", "Username");
        System.out.println("|--------------|----------------------|----------|------------|--------------|--------------|");

        for (Bill bill : historyList) {
            for (CartItem cartItem : bill.getBoughtList()) {
                if (cartItem.getUsername().toLowerCase().trim().contains(name.toLowerCase().trim())) {
                    isMatch = true;
                    total += cartItem.getTotalAmount();
                    quantityTotal += cartItem.getQuanity();
                    System.out.printf("| %-12s | %-20s | %-8d | %-10.2f | %-12.2f | %-10s |\n", cartItem.getNow(), cartItem.getName(), cartItem.getQuanity(), cartItem.getUnitPrice(), cartItem.getTotalAmount(), cartItem.getUsername());
                }
            }
        }

        if (isMatch) {
            System.out.println("|--------------|----------------------|----------|------------|--------------|--------------|");
            System.out.println("|                                  Total Quantity|                         Amount            |");
            System.out.printf("|%47d |%30.2f |\n", quantityTotal, total);
            System.out.println("|--------------|----------------------|----------|------------|--------------|--------------|");
        }
        if (!isMatch) {
            System.err.println("Product not found");
        }
    }

    public static void viewAdminWallet() {
        List<Bill> historyList = HistoryDTO.readFile();
        double total = 0;
        for (Bill bill : historyList) {
            for (CartItem cartItem : bill.getBoughtList()) {
                total += cartItem.getTotalAmount();
            }
        }
        System.out.println("Your Balance : " + total);
    }

}
