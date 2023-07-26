import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Input:");
        String input = s.nextLine().replaceAll("\\s", "");
        String [] operation_verification = input.split("[-+*/.]");
        if (operation_verification.length == 2) {
            try {
                int x = Integer.parseInt(operation_verification[0]);
                int y = Integer.parseInt(operation_verification[1]);
                if (x >= 1 && x <= 10 && y >= 1 && y <= 10) {
                    String action = Operation.action(input);
                    int z = Operation.result(action, x, y);
                    System.out.println("Output:\n" + z);
                }else {
                    System.out.println("Калькулятор принимает на вход числа от 1 до 10 включительно, не более");
                }
            }catch (NumberFormatException e){
                if(Examination.numericalValue(operation_verification[0]) && Examination.numericalValue(operation_verification[1])) {
                    int num_x = Translation.translationIntoArabic(operation_verification[0]);
                    int num_y = Translation.translationIntoArabic(operation_verification[1]);
                    if (num_x >= 1 && num_x <= 10 && num_y >= 1 && num_y <= 10) {
                        if (Operation.action(input).equals("-") && num_x < num_y) {
                            System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                        } else {
                            String action = Operation.action(input);
                            int z = Operation.result(action, num_x, num_y);
                            System.out.println("Output:\n" + Translation.translationIntoRim(z));
                        }
                    } else {
                        System.out.println(Examination.numericalValue(operation_verification[0]));
                        System.out.println("Калькулятор принимает на вход числа от 1 до 10 включительно, не более");
                    }
                }else {
                    System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                }
            }
        } else if (operation_verification.length == 1) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
        } else {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
    }
}

class Translation{
    static String[] romanAway = new String[]{
            "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
            "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI",
            "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
            "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
            "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII",
            "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
            "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
            "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    static int translationIntoArabic(String roman) {
        for (int i = 0; i < romanAway.length; i++) {
            if (roman.equals(romanAway[i])) {
                return i;
            }
        }
        return -1;
    }

    static String translationIntoRim(int z) {
        return romanAway[z];
    }
}

class Operation{
    static String action(String input) {
        if (input.indexOf('+') != -1) return "+";
        else if (input.indexOf('-') != -1) return "-";
        else if (input.indexOf('*') != -1) return "*";
        else return "/";
    }

    static int result(String action, int x, int y) {
        if (action.indexOf('+') != -1) return x + y;
        else if (action.indexOf('-') != -1) return x - y;
        else if (action.indexOf('*') != -1) return x * y;
        else return x / y;
    }
}

class Examination{
    static boolean numericalValue(String input) {
        try {
            Double.parseDouble(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}