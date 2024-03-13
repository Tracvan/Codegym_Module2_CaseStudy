package service;

import dto.CartDTO;
import dto.CodeDTO;
import model.Code;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeService {
    public static void createCode(){
        List<Code> codeList = CodeDTO.readFile();
        Scanner scanner =new Scanner(System.in);
        System.out.println("Enter number of cost");
        double cost = scanner.nextDouble();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = now.format(formatter);
        String code = "CG" + formattedDateTime;
        Code newCode = new Code(code,cost,true);
        codeList.add(newCode);
        CodeDTO.writeFile(codeList);
        System.out.println(newCode.getCode() + " is created");
    }
    public static void disableCode(String code){
        List<Code> codeList = CodeDTO.readFile();
        for (Code c : codeList){
            if(c.getCode().equals(code)){
                c.setAvaiable(!c.isAvaiable());
                break;
            }
        }
        CodeDTO.writeFile(codeList);
    }
}
