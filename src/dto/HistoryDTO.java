package dto;

import model.Bill;
import model.CartItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDTO {
    private static final File file = new File("history.csv");

    public static List<Bill> readFile() {
        List<Bill> historyList = new ArrayList<>();
        String line;
        String now = "";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<CartItem> boughtList = new ArrayList<>();
            String userName = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] billString = line.split(",");
                now =  billString[0];
                int id  = Integer.parseInt(billString[1]);
                String productName =billString[2];
                int quantity = Integer.parseInt(billString[3]);
                double price = Double.parseDouble(billString[4]);;
                double totalAmount = price * quantity;
                String type = billString[5];
                userName = billString[6];
                CartItem cartItem = new CartItem(now,id,productName, quantity, price,type,userName);
                boughtList.add(cartItem);
            }
            Bill bill = new Bill(now, userName, boughtList);
            historyList.add(bill);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return historyList;
    }

    public static void writeFile(List<Bill> historyList) {


        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Bill bill : historyList) {
                for (CartItem cartItem : bill.getBoughtList()) {
                    StringBuilder billString = new StringBuilder();
                    billString.append(cartItem.getNow()).append(",").append(cartItem.getId()).append(",")
                            .append(cartItem.getName()).append(",")
                            .append(cartItem.getQuanity()).append(",")
                            .append(cartItem.getUnitPrice()).append(",")
                            .append(cartItem.getType()).append(",")
                            .append(cartItem.getUsername()).append(",").append("\n");
                    bufferedWriter.append(billString.toString());
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
