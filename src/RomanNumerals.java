import java.util.HashMap;
import java.util.Map;

public class RomanNumerals {

    public Map<String, Integer> map;

    public RomanNumerals(){
        map = new HashMap<>();
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
}
