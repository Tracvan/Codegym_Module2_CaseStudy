package dto;

import model.CartItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    private static File file = new File("cart.csv");

    public static List<CartItem> readFile() {
        List<CartItem> cartItemList = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] productString = line.split(",");
                int id = productString[0].isEmpty() ? 0 : Integer.parseInt(productString[0]);
                String name = productString[1];
                int quanity = productString[0].isEmpty() ? 0 : Integer.parseInt(productString[2]);
                Double price = productString[0].isEmpty() ? 0 : Double.parseDouble(productString[3]);
                String type = productString[4];
                String username = productString[5];
                CartItem cartItem = new CartItem(id,name,quanity,price,type,username);
                cartItemList.add(cartItem);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cartItemList;
    }
    public static void writeFile(List<CartItem> cartItemList){
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String productString = "";
            for(CartItem cartItem : cartItemList){
                productString += cartItem.getId() + "," + cartItem.getName() + ","
                        + cartItem.getQuanity() + "," + cartItem.getUnitPrice() + "," + cartItem.getType()+ "," + cartItem.getUsername() +"\n";
            }
            bufferedWriter.write(productString);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
