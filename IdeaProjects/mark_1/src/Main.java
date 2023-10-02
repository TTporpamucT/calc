import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.Integer.parseInt;

public class Main {
    public static class Converter {
        TreeMap<Character, Integer> spravochik_R_v_A = new TreeMap<>();
        TreeMap<Integer, String> spravochik_A_v_R = new TreeMap<>();

        public Converter() {
            spravochik_R_v_A.put('I', 1);
            spravochik_R_v_A.put('V', 5);
            spravochik_R_v_A.put('X', 10);
            spravochik_R_v_A.put('L', 50);
            spravochik_R_v_A.put('C', 100);
            spravochik_R_v_A.put('D', 500);
            spravochik_R_v_A.put('M', 1000);

            spravochik_A_v_R.put(1000, "M");
            spravochik_A_v_R.put(900, "CM");
            spravochik_A_v_R.put(500, "D");
            spravochik_A_v_R.put(400, "CD");
            spravochik_A_v_R.put(100, "C");
            spravochik_A_v_R.put(90, "XC");
            spravochik_A_v_R.put(50, "L");
            spravochik_A_v_R.put(40, "XL");
            spravochik_A_v_R.put(10, "X");
            spravochik_A_v_R.put(9, "IX");
            spravochik_A_v_R.put(5, "V");
            spravochik_A_v_R.put(4, "IV");
            spravochik_A_v_R.put(1, "I");

        }


        public boolean isRoman(String number){
            return spravochik_R_v_A.containsKey(number.charAt(0));
        }

        public String intToRoman(int number) {
            String roman = "";
            int arabianKey;
            do {
                arabianKey = spravochik_A_v_R.floorKey(number);
                roman += spravochik_A_v_R.get(arabianKey);
                number -= arabianKey;
            } while (number != 0);
            return roman;


        }
        public int romanToInt(String s) {
            int end = s.length() - 1;
            char[] arr = s.toCharArray();
            int arabian;
            int result = spravochik_R_v_A.get(arr[end]);
            for (int i = end - 1; i >= 0; i--) {
                arabian = spravochik_R_v_A.get(arr[i]);

                if (arabian < spravochik_R_v_A.get(arr[i + 1])) {
                    result -= arabian;
                } else {
                    result += arabian;
                }


            }
            return result;

        }
    }

    public static void main(String[] args) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        if(exp.contains(",") | exp.contains(".")){System.out.println("ОШИБКА: Должны быть только целые числа.");
            return;}
        int actionIndex=-1;
        for (int i = 0; i < actions.length; i++) {
            if(exp.contains(actions[i])){
                actionIndex = i;
                break;
            }
        }
        if(actionIndex==-1){
            System.out.println("ОШИБКА: Некорректное выражение");
            return;
        }
        String[] data = exp.split(regexActions[actionIndex]);

        if(converter.isRoman(data[0].trim()) == converter.isRoman(data[1].trim())){

            int a,b;
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                a = converter.romanToInt(data[0].trim());
                b = converter.romanToInt(data[1].trim());

            }else{
                    a = parseInt(data[0].trim());
                    b = parseInt(data[1].trim());
            }
            int result;
            switch (actions[actionIndex]){
                case "+":
                    result = a+b;
                    break;
                case "-":
                    result = a-b;
                    break;
                case "*":
                    result = a*b;
                    break;
                default:
                    result = a/b;
                    break;
            }
            if(a<=10 & b<=10){
                if (isRoman) {
                    if (result < 0) {
                        System.out.println("ОШИБКА: Калькулятор не вычисляет отрицательные значения");
                    } else {
                        System.out.println(converter.intToRoman(result));
                    }
                }
                    else {
                        System.out.println(result);

                }
            }else{
                System.out.println("ОШИБКА: Цифры не могут превышать 10.");}
        }else{
            System.out.println("ОШИБКА: Цифры должны быть в одном формате");
        }


    }
}


