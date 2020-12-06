import java.io.*;
import java.util.Map;

public class Calculator {

    public static final RomanNumerals romanNumerals = new RomanNumerals();

    public static void main(String[] args) throws Exception {

        String input;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while ((input = reader.readLine()) != null){
                if (input.isEmpty()) break;
                if (isArabicNumerals(input)) arabicNumeralsCalculation(input);
                else romanNumeralsCalculation(input);
            }
        } catch (Exception e) {
            throw new Exception("Ой! Что-то пошло не так и программа была завершена.");
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
            int num1 = convertRomanToArabicNumeral(inputPart1);
            int num2 = convertRomanToArabicNumeral(inputPart2);
            if (input.contains("+")){
                convertArabicNumeralToRomanNumeralAndPrint(add(num1, num2));
            }else if (input.contains("-")){
                convertArabicNumeralToRomanNumeralAndPrint(subtract(num1, num2));
            }else if (input.contains("*")){
                convertArabicNumeralToRomanNumeralAndPrint(multiply(num1, num2));
            }else if (input.contains("/")){
                convertArabicNumeralToRomanNumeralAndPrint(divide(num1, num2));
            }else {
                wrongOperationError();
            }
        }catch (Exception e){
            throw new Exception();
        }
    }

    public static int convertRomanToArabicNumeral(String romanNumber) throws Exception {
        int sum = 0;
        try {
            int num1, num2;
            for (int i = 0; i < romanNumber.length()-1; i++) {
                num1 = romanNumerals.map.get(String.valueOf(romanNumber.charAt(i)));
                num2 = romanNumerals.map.get(String.valueOf(romanNumber.charAt(i+1)));
                if (num1<num2){
                    sum -= num1;
                }else {
                    sum += num1;
                }
            }
            num2 = romanNumerals.map.get(String.valueOf(romanNumber.charAt(romanNumber.length()-1)));
            sum += num2;
            return sum;
        } catch (Exception e) {
            wrongOperationError();
        }
        return sum;
    }

    public static void convertArabicNumeralToRomanNumeralAndPrint(int number){
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
        for (Map.Entry<String, Integer> pair : romanNumerals.map.entrySet()){
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

    public static int add(int num1, int num2){ return num1 + num2; }

    public static int subtract(int num1, int num2){ return num1 - num2; }

    public static int multiply(int num1, int num2){ return num1 * num2; }

    public static int divide(int num1, int num2){ return num1 / num2; }

    private static void wrongOperationError() throws Exception {
        throw new Exception("Ошибка! Некорректная операция!");
    }
}
