package cdp.modul5.calculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import cdp.modul5.calculator.api.ICalculator;
import cdp.modul5.calculator.rpn.CalculatorRPN;


public class Launcher {
    public static void main(String[] args) {
        try {
            final String input = System.getenv("input"); //getting input
            ICalculator calc = new CalculatorRPN();
            float result = calc.calculate(input);

            String formattedResult = formatResult(result);
            System.out.println(formattedResult);
            
        } catch(Exception e) {
            System.out.println("Fail on " + e.getMessage() + " symbol");
        } 
    }

    private static String formatResult(float result) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", dfs);
        return df.format(result);
    }
}
