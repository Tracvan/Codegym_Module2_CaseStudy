package service;

import controller.LoginController;
import dto.CartDTO;
import dto.ProductDTO;
import model.CartItem;
import model.Product;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cartservice {

    public static void addToCart() throws InterruptedException {
        ProductService productService = new ProductService();
        productService.showAllProduct();
        List<CartItem> cartList = CartDTO.readFile();
        List<Product> productList = ProductDTO.readFile();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of product");
        String id = scanner.nextLine();
        boolean isExist = false;
        if (IntergerCheck.integerCheck(id)) {
            for (Product p : productList) {
                if (p.getId() == Integer.parseInt(id)) {
                    isExist = true;
                    boolean quanityCheck = false;
                    do {
                        boolean isOverZero = false;
                        do {
                            System.out.println("Enter quantity want to add");
                            String quantity = scanner.nextLine();
                            if (IntergerCheck.integerCheck(quantity)) {
                                if (Integer.parseInt(quantity) > 0) {
                                    isOverZero = true;
                                    if (Integer.parseInt(quantity) <= p.getQuanity()) {
                                        quanityCheck = true;
                                        CartItem cartProduct = new CartItem(Integer.parseInt(id), p.getName(), Integer.parseInt(quantity), p.getPrice(), p.getType(), LoginController.currentAccount);
                                        cartList.add(cartProduct);
                                        CartDTO.writeFile(cartList);
                                        System.out.println("Product is added to cart");
                                        Cartservice.viewMyCart();
                                        break;
                                    } else {
                                        System.out.println("Quantity cannot be greater than store ");
                                    }
                                } else {
                                    System.err.println("Quantity must be greater than 0, enter again");
                                }
                            }

                        } while (!isOverZero);
                    } while (!quanityCheck);
                }
            }
        }
        if (!isExist) {
            System.err.println("Product not found");
            addToCart();
        }
    }

    public static double getTotal() {
        double total = 0;
        List<CartItem> cartItemList = CartDTO.readFile();
        for (CartItem p : cartItemList) {
            if (p.getUsername().equalsIgnoreCase(LoginController.currentAccount)) {
                total += p.getTotalAmount();
            }
        }
        return total;
    }

    public static List<CartItem> getMyCart() {
        List<CartItem> myCartList = new ArrayList<>();
        List<CartItem> cartList = CartDTO.readFile();
        String now = LocalTime.now().toString();
        for (CartItem c : cartList) {
            if (c.getUsername().equals(LoginController.currentAccount)) {
                CartItem cartItem = new CartItem(now, c.getId(), c.getName(), c.getQuanity(), c.getUnitPrice(), c.getType(), c.getUsername());
                myCartList.add(cartItem);
            }
        }
        return myCartList;
    }

    public static void viewMyCart() {
        double total = 0;
        int count = 1;
        List<CartItem> cartList = CartDTO.readFile();
        System.out.println("Number  Product's Name    Type    Quantity  Unit Price  Total Price");
        System.out.println("-----------------------------------------------------------------");
        for (CartItem p : cartList) {
            if (p.getUsername().equalsIgnoreCase(LoginController.currentAccount)) {
                String formattedString = String.format("%-7d %-16s %-7s %-9d %.2f       %.2f", count++, p.getName(), p.getType(), p.getQuanity(), p.getUnitPrice(), p.getTotalAmount());
                System.out.println(formattedString);
                total += p.getTotalAmount();
            }
        }
        System.out.printf("Total:%57.2f", total);
        System.out.println("");
    }
}

