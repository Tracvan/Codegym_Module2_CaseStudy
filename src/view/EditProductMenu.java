package view;

import dto.ProductDTO;
import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Scanner;

public class EditProductMenu {

    public static void editProduct() throws InterruptedException {
        List<Product> productList = ProductDTO.readFile();
        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);
        productService.showAllProduct();
        System.out.println("Enter id of product to edit");
        String idEdit = scanner.nextLine();
        boolean isMatch = false;
        for(Product p : productList){
            if( idEdit.equals(p.getId()+"") ){
                isMatch = true;
                System.out.println("1. Name of product");
                System.out.println("2. Quantity of product");
                System.out.println("3. Price of product");
                System.out.println("4. Type of product");
                System.out.println("Enter your choice");
                String choice = scanner.nextLine();
                switch (choice){
                    case "1" :
                        System.out.println("Enter new Name for product: ");
                        String newName = scanner.nextLine();
                        p.setName(newName);
                        System.out.println("Product is edited");
                        ProductDTO.writeFile(productList);
                        break;
                    case "2":
                        System.out.println("Enter new Quantity for product");
                        int newQuantity = scanner.nextInt();
                        scanner.nextLine();
                        p.setQuanity(newQuantity);
                        System.out.println("Product is edited");
                        ProductDTO.writeFile(productList);
                        break;
                    case "3":
                        System.out.println("Enter new Price for product");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine();
                        p.setPrice(newPrice);
                        System.out.println("Product is edited");
                        ProductDTO.writeFile(productList);
                        break;
                    case "4":
                        System.out.println("Enter new Type for product");
                        String newType = scanner.nextLine();
                        p.setType(newType);
                        System.out.println("Product is edited");
                        ProductDTO.writeFile(productList);
                        break;
                    default:
                        System.err.println("Choice again");;
                }
                break;
            }
        }
        if(!isMatch){
            System.err.println("Product not found");
        }

    }
}
