package dto;

import model.Account;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDTO {
    private static final File file = new File("account.csv");

    public static List<Account> readFile() {
        List<Account> accountList = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] accountString = line.split(",");
                String username = accountString[0];
                String password = accountString[1];
                String firstName = accountString[2];
                String lastName = accountString[3];
                String role = accountString[4];
                String phoneNumber = accountString[5];
                double wallet =  Double.parseDouble(accountString[6]);
                Account account = new Account(username, password, firstName, lastName, role, phoneNumber, wallet);
                accountList.add(account);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    public static void writeFile(List<Account> accountList) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StringBuilder accountString = new StringBuilder();
            for (Account account : accountList) {
                accountString.append(account.getUserName()).append(",").append(account.getPassword()).append(",").append(account.getFirstName()).append(",").append(account.getLastName()).append(",").append(account.getRole()).append(",").append(account.getPhoneNumber()).append(",").append(account.getWallet()).append("\n");
            }
            bufferedWriter.write(accountString.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
