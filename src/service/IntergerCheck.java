package service;

public class IntergerCheck {
    public static boolean integerCheck(String a) {
        try {
            int b = Integer.parseInt(a);
            return true;
        } catch (Exception e) {
            System.err.println("Input is not valid, Enter number please");
            return false;
        }
    }
}

