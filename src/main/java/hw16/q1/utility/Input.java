package hw16.q1.utility;

import java.util.Scanner;

public class Input {
    private static final Scanner input = new Scanner(System.in);

    public static String getOptionalStringInputValue(String msg) {
        System.out.print(msg + " ");
        return input.nextLine().trim();
    }

    public static String getStringInputValue(String msg) {
        String result = null;
        while (true) {
            result = getOptionalStringInputValue(msg);
            if (result.isEmpty())
                Printer.printErrorMessage("Invalid input!");
            else
                return result;
        }
    }

    public static int getInputValue(String msg) {
        while (true)
            try {
                return Integer.parseInt(getStringInputValue(msg));
            } catch (Exception e) {
                Printer.printErrorMessage("Invalid input");
                if (msg.isEmpty())
                    System.out.println("> ");

            }
    }

    public static double getDoubleValue(String msg) {
        while (true){
            try {
                return Double.parseDouble(getStringInputValue(msg));
            } catch (Exception e) {
                Printer.printErrorMessage("Invalid input");
                if (msg.isEmpty())
                    System.out.println("> ");
            }
        }
    }

    public static Long getLongValue(String msg) {
        while (true){
            try {
                return Long.parseLong(getStringInputValue(msg));
            } catch (Exception e) {
                Printer.printErrorMessage("Invalid input");
                if (msg.isEmpty())
                    System.out.println("> ");
            }
        }
    }
}
