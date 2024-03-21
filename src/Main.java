import java.util.Scanner;

public class Main {
    static String[] arr = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static void main(String[] args) {
        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("input:");

            try {
                String input = scanner.nextLine();
                validateInput(input);
                String output = "output:" + "\n" + calc(input);
                System.out.println(output);
            } catch (InvalidInput e) {
                System.out.println(e.getMessage());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Результат работы калькулятора с римскими числами меньше единицы");
                break;
            }
        }
    }

    public static String calc(String input) {


        String regex = "^[IVX]+$";

        String[] item = input.split(" ");
        boolean match = item[0].matches(regex) ? true : false;

        int x = match ? getNumber(item[0]) : Integer.parseInt(item[0]);
        int y = match ? getNumber(item[2]) : Integer.parseInt(item[2]);

        String output = "";

        switch (item[1]) {
            case ("/"):
                output = String.valueOf(x / y);
                break;
            case ("+"):
                output = String.valueOf(x + y);
                break;
            case ("-"):
                output = String.valueOf(x - y);
                break;
            case ("*"):
                output = String.valueOf(x * y);
                break;
        }

        output = match ? outputRoman(output) : output;

        return output;
    }

    public static int getNumber(String input) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(input)) {
                return i + 1;
            }
        }
        return 0;
    }

    public static void validateInput(String input) throws InvalidInput {
        String reg1 = "^I{0,3}V{0,1}I{0,3}X{0,1}\\s[-\\+\\/\\*]\\sI{0,3}V?I{0,3}X?$";
        String reg2 = "^[1-9]?(10)?\\s[-\\+\\/\\*]\\s[1-9]?(10)?$";

        if (!input.matches(reg1) && !input.matches(reg2))
            throw new InvalidInput("Не подходящие числа или арифметическая операция");
    }

    public static String outputRoman(String output) {
        String[] arrty = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

        if (output.length() == 1) {
            output = arr[Integer.parseInt(output) - 1];
        } else {

            int[] index = {Integer.parseInt(output.substring(0, output.length() - 1)), Integer.parseInt(output.substring(output.length() - 1))};

            output = index[1]==0? arrty[index[0] - 1]: arrty[index[0] - 1] + arr[index[1] - 1];
        }
        return output;
    }

    static class InvalidInput extends Exception {
        public InvalidInput(String message) {
            super(message);
        }
    }
}