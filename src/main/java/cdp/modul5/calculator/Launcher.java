package cdp.modul5.calculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Launcher {
    public static void main(String[] args) {
//        String input = "3 * 2 / 6 - 2";  // -1
//        String input = "2 * 3"; // 6
//        String input = "2 + 3"; // 5
//        String input = "2 + 3 + 1"; // 6
//        String input = "2 + 3 - 1"; // 4
//        String input = "2 + 3 * 4"; // 14
//        String input = "2 * 3 + 4"; // 10
//        String input = "2 * 3 / 4"; // 1.5
//        String input = "2 * 3 - 1 / 4"; // 5.75
//        String input = "2 - 1 * 3 - 1 / 4"; // -1.25
//      String input = "2 * 3 - 1 + 5 / 4"; // 6.25
//      String input = "6 - 1 + 2"; // 7

        final String input = System.getenv("input"); //getting input
        Calculator calc = new Calculator();
        float result = 0;
        try {
            result = calc.calculate(input);
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("#.00", dfs);
            System.out.print(df.format(result));
        } catch (Exception e) {
            System.out.println("Fail");
        }

    }
}
