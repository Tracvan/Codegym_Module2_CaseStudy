package dto;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    private static File file = new File("products.csv");

    public static List<Product> readFile() {
        List<Product> productList = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] productString = line.split(",");
                int id = Integer.parseInt(productString[0]);
                String name = productString[1];
                int quanity = Integer.parseInt(productString[2]);
                double price =Double.parseDouble(productString[3]);
                String type = productString[4];

                Product product = new Product(id,name,quanity,price,type);
                productList.add(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }
    public static void writeFile(List<Product> productList){
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StringBuilder productString = new StringBuilder();
            for(Product product : productList){
                productString.append(product.getId()).append(",").
                        append(product.getName()).append(",").
                        append(product.getQuanity()).append(",").
                        append(product.getPrice()).append(",").
                        append(product.getType()).append("\n");
            }
            bufferedWriter.write(productString.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
