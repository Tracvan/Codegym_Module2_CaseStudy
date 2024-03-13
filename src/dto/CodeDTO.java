package dto;
import model.CartItem;
import model.Code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CodeDTO {
    private static File file = new File("fund_code.csv");

    public static List<Code> readFile() {
        List<Code> codeList = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] productString = line.split(",");
                String code = productString[0];
                double cost = Double.parseDouble(productString[1]);
                boolean avaiable = Boolean.parseBoolean(productString[2]);
                Code newCode = new Code(code,cost,avaiable);
                codeList.add(newCode);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return codeList;
    }
    public static void writeFile(List<Code> codeList){
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String codeString = "";
            for(Code code : codeList){
                codeString += code.getCode() + "," + code.getCost() +"," + code.isAvaiable() +"\n";
            }
            bufferedWriter.write(codeString);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
