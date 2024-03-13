package service;

import controller.LoginController;
import dto.AccountDTO;
import dto.CartDTO;
import dto.CodeDTO;
import dto.HistoryDTO;
import model.Account;
import model.Bill;
import model.CartItem;
import model.Code;
import view.CustomerMenu;
import view.NewPage;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import static javafx.beans.binding.Bindings.isEmpty;

public class CustomerService {

    public static void addFund() {
        List<Code> codeList = CodeDTO.readFile();
        List<Account> accountList1 = AccountDTO.readFile();
        String currentAccount = LoginController.currentAccount;
        Scanner scanner = new Scanner(System.in);
        boolean isMatching = false;
        do {
            System.out.println("Enter your code");
            String code = scanner.nextLine();

            for (Code c : codeList) {
                if (code.equals(c.getCode()) && c.isAvaiable()) {
                    for (Account a : accountList1) {
                        if (currentAccount.equals(a.getUserName())) {
                            a.setWallet(a.getWallet() + c.getCost());
                            System.out.println("Your account has been credited with an additional " + c.getCost() + "\n"
                                    + "Your balance is: " + a.getWallet());
                            CodeService.disableCode(code);
                            break;
                        }
                    }
                    isMatching = true;
                    break;
                }
            }

            if (!isMatching) {
                System.out.println("Code is not avaiable or not exist");
                isMatching = false;
            }
            AccountDTO.writeFile(accountList1);
        } while (!isMatching);

    }

    public static void buyProduct() {
        Account currentAccount = null;
        Scanner scanner = new Scanner(System.in);
        List<Account> accountList = AccountDTO.readFile();
        for (Account account : accountList) {
            if (account.getUserName().equals(LoginController.currentAccount)) {
                currentAccount = account;
            }
        }
        boolean isExist = false;
        if (Cartservice.getMyCart().size() > 0) {
            isExist = true;
        }
        if (isExist) {
            String choice;
            System.out.println("-----Your Cart-----");
            Cartservice.viewMyCart();
            System.out.println("You want to buy all product in cart ?");
            System.out.println("1.Yes");
            System.out.println("2. No");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    if (Cartservice.getTotal() < currentAccount.getWallet()) {
                        String now = LocalTime.now().toString();
                        String currentName = currentAccount.getUserName();
                        List<CartItem> myCartList = Cartservice.getMyCart();
                        List<Bill> historyList = HistoryDTO.readFile();
                        Bill bill = new Bill(now, currentName, myCartList);

                        historyList.add(bill);
                        HistoryDTO.writeFile(historyList);

                        ProductService.editWhenBuy(myCartList);
                        System.out.println("Order Successed, Thanks you!");
                        CustomerService.viewMyCartListBill(bill);


                        currentAccount.setWallet(currentAccount.getWallet() - Cartservice.getTotal());
                        AccountDTO.writeFile(accountList);




                        myCartList.clear();
                        CartDTO.writeFile(myCartList);


                    } else {
                        System.err.println("Account balance is insufficient for payment. Please top up.");
                    }
            }

        } else {
            System.out.println("Have nothing in your cart.");
        }
    }

    public static void viewMyWallet() {
        List<Account> accounts = AccountDTO.readFile();
        Account currentAccount = null;
        for (Account a : accounts) {
            if (a.getUserName().equals(LoginController.currentAccount)) {
                currentAccount = a;
            }
        }

        assert currentAccount != null;
        System.out.println("Your current account balance is " + currentAccount.getWallet() + " dollars . ");
    }

    public static void viewCustomerHistory() {
        List<Bill> history = HistoryDTO.readFile();
        int count = 1;
        double total = 0;

        System.out.println("___________________________________________________________________");
        System.out.printf("|%-12s |%-8s |%-20s |%-10s |%-10s |%-12s |\n", "Time", "Number", "Name", "Quantity", "Price", "Total Amount");
        System.out.println("|-------------|---------|---------------------|-----------|----------|-------------|");

        for (Bill bill : history) {
            List<CartItem> boughtList = bill.getBoughtList();
            double billTotal = 0; // Tổng giá trị của từng hóa đơn
            for (CartItem cartItem : boughtList) {
                if (cartItem.getUsername().equals(LoginController.currentAccount)) {
                    double itemTotal = cartItem.getQuanity() * cartItem.getUnitPrice();
                    System.out.printf("|%-12s |%-8d |%-20s |%-10d |%-10.2f |%-12.2f |\n", bill.getNow(), count++, cartItem.getName(), cartItem.getQuanity(), cartItem.getUnitPrice(), itemTotal);
                    total += itemTotal;
                    billTotal += itemTotal;
                }
            }
        }
        System.out.println("|-------------|---------|---------------------|-----------|----------|-------------|");
        System.out.printf("|%-12s |%-8s |%-20s |%-10s |%-10s |%.2f |\n", "", "TOTAL", "", "", "", total);
        System.out.println("|___________________________________________________________________________|");
    }
    public static void viewMyCartListBill(Bill bill) {
        int count = 1;
        double total = 0;
        System.out.println("___________________________________________________________________");
        System.out.printf("|%-12s |%-8s |%-20s |%-10s |%-10s |%-10s |\n", "Time", "Number", "Name", "Quantity", "Price", "Total Price");
        for (CartItem cartItem : Cartservice.getMyCart()) {
            System.out.println("|-------------|---------|---------------------|-----------|----------|----------|");
            double itemTotal = cartItem.getQuanity() * cartItem.getUnitPrice();
            System.out.printf("|%-12s |%-8d |%-20s |%-10d |%-10.2f |%-10.2f |\n", bill.getNow(), count++, cartItem.getName(), cartItem.getQuanity(), cartItem.getUnitPrice(), itemTotal);
            total += itemTotal;
        }

        System.out.println("|-------------|---------|---------------------|-----------|----------|----------|");
        System.out.printf("|%-12s |%-8s |%-20s |%-10s |%-10s |%.2f |\n", "", "TOTAL", "", "", "", total);
        System.out.println("|__________________________________________________________________|");
    }

    public static void enterToBackMenu() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean isExcept = false;
        System.out.println("Press enter key to back to menu page");
        scanner.nextLine();
        NewPage.printNewPage();
        CustomerMenu.showMenu();
    }
}
