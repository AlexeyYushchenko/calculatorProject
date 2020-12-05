import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    public static Map<String, Integer> map = new HashMap<>();

    static{
        map.put("", 0);
        map.put("I", 1);
        map.put("II", 2);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("VI", 6);
        map.put("VII", 7);
        map.put("VIII", 8);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XX", 20);
        map.put("XXX", 30);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("LX", 60);
        map.put("LXX", 70);
        map.put("LXXX", 80);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CC", 200);
        map.put("CCC", 300);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("DC", 600);
        map.put("DCC", 700);
        map.put("DCCC", 800);
        map.put("CM", 900);
        map.put("M", 1000);
        map.put("MM", 2000);
        map.put("MMM", 3000);
        map.put("MV", 4000);
        map.put("ↁ", 5000);
        map.put("VM", 6000);
        map.put("VMM", 7000);
        map.put("VMMM", 8000);
        map.put("MX", 9000);
        map.put("ↂ", 10000);
    }

    public static void main(String[] args) throws Exception {

        String input;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while ((input = reader.readLine()) != null){
                if (input.isEmpty()) break;
                if (isArabicNumerals(input)) arabicNumeralsCalculation(input);
                else romanNumeralsCalculation(input);
            }
        } catch (Exception e) {
            throw new Exception("Ой! Что-то пошло не так! Программа завершена.");
        }
    }

    public static void arabicNumeralsCalculation(String input) throws Exception {
        int num1 = Integer.parseInt(input.replaceAll(" ", "").split("[^0-9]")[0].trim());
        int num2 = Integer.parseInt(input.replaceAll(" ", "").split("[^0-9]")[1].trim());
        if (input.contains("+")){
            System.out.println(add(num1, num2));
        }else if (input.contains("-")){
            System.out.println(subtract(num1, num2));
        }else if (input.contains("*")){
            System.out.println(multiply(num1, num2));
        }else if (input.contains("/")){
            System.out.println(divide(num1, num2));
        }else {
            wrongOperationError();
        }
    }

    public static void romanNumeralsCalculation(String input) throws Exception {
        try {
            String inputPart1 = input.replaceAll(" ", "").split("\\W")[0].trim();
            String inputPart2 = input.replaceAll(" ", "").split("\\W")[1].trim();
            int num1 = convertToArabicNumeral(inputPart1);
            int num2 = convertToArabicNumeral(inputPart2);
            if (input.contains("+")){
                convertAndPrintRomanNumeral(add(num1, num2));
            }else if (input.contains("-")){
                convertAndPrintRomanNumeral(subtract(num1, num2));
            }else if (input.contains("*")){
                convertAndPrintRomanNumeral(multiply(num1, num2));
            }else if (input.contains("/")){
                convertAndPrintRomanNumeral(divide(num1, num2));
            }else {
                wrongOperationError();
            }
        }catch (Exception e){
            throw new Exception();
        }
    }

    public static int convertToArabicNumeral(String romanNumber) throws Exception {
        int sum = 0;
        try {
            int num1, num2;
            for (int i = 0; i < romanNumber.length()-1; i++) {
                num1 = map.get(String.valueOf(romanNumber.charAt(i)));
                num2 = map.get(String.valueOf(romanNumber.charAt(i+1)));
                if (num1<num2){
                    sum -= num1;
                }else {
                    sum += num1;
                }
            }
            num2 = map.get(String.valueOf(romanNumber.charAt(romanNumber.length()-1)));
            sum += num2;
            return sum;
        } catch (Exception e) {
            wrongOperationError();
        }
        return sum;
    }

    public static void convertAndPrintRomanNumeral(int number){

        int ones = number%10;
        int tens = number%100-ones;
        int hundreds = number%1000-tens-ones;
        int thousands = number/1000*1000;

        String romanThousands = getRomanNumeral(thousands);
        String romanHundreds = getRomanNumeral(hundreds);
        String romanTens = getRomanNumeral(tens);
        String romanOnes = getRomanNumeral(ones);

        System.out.println(romanThousands +
                romanHundreds +
                romanTens +
                romanOnes);
    }

    public static String getRomanNumeral(int number){

        for (Map.Entry<String, Integer> pair : map.entrySet()){
            String key = pair.getKey();
            int value = pair.getValue();
            if (number == value) return key;
        }
        return "";
    }

    public static boolean isArabicNumerals(String input){
        try {
            int num1 = Integer.parseInt(input.replaceAll(" ", "").split("[^0-9]")[0].trim());
            int num2 = Integer.parseInt(input.replaceAll(" ", "").split("[^0-9]")[1].trim());
            return true;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static int add(int num1, int num2){
        return num1 + num2;
    }

    public static int subtract(int num1, int num2){
        return num1 - num2;
    }

    public static int multiply(int num1, int num2){
        return num1 * num2;
    }

    public static int divide(int num1, int num2){
        return num1 / num2;
    }

    private static void wrongOperationError() throws Exception {
        throw new Exception("Ошибка! Некорректная операция!");
    }



}
