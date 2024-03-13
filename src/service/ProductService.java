package service;

import dto.ProductDTO;
//import input.Input;
import model.CartItem;
import model.Product;
import view.EditProductMenu;
import view.SortProductListMenu;

import java.util.*;

public class ProductService implements IService<Product> {
    Scanner scanner = new Scanner(System.in);
    List<Product> productList = ProductDTO.readFile();

    @Override
    public void add() throws InterruptedException {
        int id = productList.size() + 1;
        System.out.println("====Add product====");
        for (int i = 0; i < productList.size(); i++) {
            if (i + 1 != productList.get(i).getId()) {
                id = i + 1;
                break;
            }
        }
        System.out.println("Enter product's name");
        String name = scanner.nextLine();
        System.out.println("Enter amount of product");
        int quanity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter price of product");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter type of product");
        String type = scanner.nextLine();
        Product newProduct = new Product(id, name, quanity, price, type);
        productList.add(newProduct);
        ProductDTO.writeFile(productList);
        System.out.println("Product is added success");
        Thread.sleep(1000);
    }

    @Override
    public void edit() throws InterruptedException {

    }

    @Override
    public void delete() throws InterruptedException {
        System.out.println("Enter id of product");
        int id = scanner.nextInt();
        scanner.nextLine();
        productList.remove(id - 1);
        ProductDTO.writeFile(productList);
        System.out.println("Product is deleted");
        Thread.sleep(1000);
    }

    @Override
    public void findByName() throws InterruptedException {
        System.out.println("Enter name of product");
        String name = scanner.nextLine();
        boolean isExists = false;
        System.out.println("Number  Product's Name    Quantity  Price   Type");
        System.out.println("---------------------------------------------------");
        for (Product p : productList) {
            if (p.getName().trim().toLowerCase().contains(name.trim().toLowerCase())) {
                String formattedString = String.format("%-7d %-16s %-9d %.2f   %s", p.getId(), p.getName(), p.getQuanity(), p.getPrice(), p.getType());
                System.out.println(formattedString);
                isExists = true;
            }
        }
        if (!isExists) {
            System.err.println("Product not found");
            Thread.sleep(1000);
        }
    }

    @Override
    public void findByType() throws InterruptedException {
        List<Product> products = ProductDTO.readFile();
        List<String> typeList = new ArrayList<>();
        Map<String, String> typeMap = new HashMap<>();
        for (Product p : products) {
            boolean isExist = false;
            String type = p.getType();
            for (String s : typeList) {
                if (s.equals(type)) {
                    isExist = true;
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
        for (Product p : products) {
            if (p.getType().equals(typeMap.get(choice))) {
                isMatch = true;
                String formattedString = String.format("%-7d %-16s %-9d %.2f   %s", p.getId(), p.getName(), p.getQuanity(), p.getPrice(), p.getType());
                System.out.println(formattedString);
            }
        }if(isMatch) {
            System.out.println("---------------------------------------------------");
        }else {
            System.err.println("Product not found");
        }
    }

    @Override
    public void showAllProduct() throws InterruptedException {
        sortByID();
    }

    public static void editWhenBuy(List<CartItem> cartItemList) {
        List<Product> productList = ProductDTO.readFile();
        for (CartItem cartItem : cartItemList) {
            for (Product product : productList) {
                if (cartItem.getName().equals(product.getName())) {
                    product.setQuanity(product.getQuanity() - cartItem.getQuanity());
                }
            }
        }
        ProductDTO.writeFile(productList);
    }

    public static void sortPriceLowToHigh() throws InterruptedException {
        List<Product> products = ProductDTO.readFile();
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (products.get(i).getPrice() < products.get(j).getPrice()) {
                    Product temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);
                }
            }
        }
        System.out.println("__________________________________________________");
        System.out.println("Number  Product's Name    Quantity  Price   Type");
        System.out.println("---------------------------------------------------");
        for (Product p : products) {
            String formattedString = String.format("%-7d %-16s %-9d %.2f   %s", p.getId(), p.getName(), p.getQuanity(), p.getPrice(), p.getType());
            System.out.println(formattedString);
        }
        System.out.println("__________________________________________________");
        Thread.sleep(1000);
    }

    public static void sortPriceHighToLow() throws InterruptedException {
        List<Product> products = ProductDTO.readFile();
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (products.get(i).getPrice() > products.get(j).getPrice()) {
                    Product temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);
                }
            }
        }
        System.out.println("________________________________________________");
        System.out.println("Number  Product's Name    Quantity  Price   Type");
        System.out.println("---------------------------------------------------");
        for (Product p : products) {
            String formattedString = String.format("%-7d %-16s %-9d %.2f   %s", p.getId(), p.getName(), p.getQuanity(), p.getPrice(), p.getType());
            System.out.println(formattedString);
        }
        System.out.println("________________________________________________");
        Thread.sleep(1000);

    }
    public static void sortByID() throws InterruptedException {
        List<Product> products = ProductDTO.readFile();
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (products.get(i).getId() < products.get(j).getId()) {
                    Product temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);
                }
            }
        }
        System.out.println("________________________________________________");
        System.out.println("Number  Product's Name    Quantity  Price   Type");
        System.out.println("---------------------------------------------------");
        for (Product p : products) {
            String formattedString = String.format("%-7d %-16s %-9d %.2f   %s", p.getId(), p.getName(), p.getQuanity(), p.getPrice(), p.getType());
            System.out.println(formattedString);
        }
        System.out.println("________________________________________________");
        Thread.sleep(1000);

    }

}






