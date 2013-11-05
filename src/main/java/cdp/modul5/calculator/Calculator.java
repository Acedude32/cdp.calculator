package cdp.modul5.calculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public String calculate(String input) {
        String result = "Fail";
        if (validate(input)) {
            input = input.replaceAll("\\s+", "");
            try {
                List<String> parsed = parse(input);
                result = calculateParsed(parsed);

            } catch (Exception e) {
                return result;
            }
        }
        return result;
    }

    private boolean validate(String input) {
        
        // check if input == null
        if (input == null) {
            return false;
        }
        
        // check if there is no operator between two numbers 
        String regex = "\\d\\s+\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return false;
        }
        
        return true;
        // TODO maybe need more validation cases
    }

    private List<String> parse(String input) {
        List<String> stack = new ArrayList<String>();
        List<String> parsed = new ArrayList<String>();
        boolean lastParsedIsDigit = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                if (lastParsedIsDigit) {
                    processDigit(parsed, ch);
                } else {
                    parsed.add(String.valueOf(ch));
                    lastParsedIsDigit = true;
                }
            } else if (isOperation(ch)) {
                String newOperator = String.valueOf(ch);

                if (stack.size() > 0) {
                    while (!stack.isEmpty() && newOperatorHasSameOrLowerPriority(newOperator, stack.get(stack.size() - 1)) ) {
                        parsed.add(stack.get(stack.size() - 1));
                        stack.remove(stack.size() - 1);
                    }
                } 
                stack.add(newOperator);
                lastParsedIsDigit = false;
                
            } else {
                throw new IllegalArgumentException();
            }
        }
        
        Collections.reverse(stack);
        parsed.addAll(stack);

        return parsed;
    }

    private void processDigit(List<String> parsed, char ch) {
        String digitValue = parsed.get(parsed.size() - 1);
        digitValue = digitValue + ch;
        parsed.set(parsed.size() - 1, digitValue);
    }

    private boolean newOperatorHasSameOrLowerPriority(String newOperator, String last) {

        return (newOperator.equals("*") || newOperator.equals("/")) && (last.equals("*") || last.equals("/"))
                || (newOperator.equals("+") || newOperator.equals("-")) && (last.equals("+") || last.equals("-"))
                || (newOperator.equals("+") || newOperator.equals("-")) && (last.equals("*") || last.equals("/"));
    }

    private boolean isOperation(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return true;
        }
        return false;
    }

    private boolean isOperation(String s) {
        if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)) {
            return true;
        }
        return false;
    }

    private String calculateParsed(List<String> parsed) {
        float result = 0;
        while (parsed.size() > 1) {
            for (int i = 0; i < parsed.size(); i++) {
                String element = parsed.get(i);
                if (isOperation(element)) {
                    float n1 = Float.valueOf(parsed.get(i - 2));
                    float n2 = Float.valueOf(parsed.get(i - 1));

                    if ("+".equals(element)) {
                        result = n1 + n2;
                        changeParsed(parsed, i, result);
                        break;
                    }
                    if ("-".equals(element)) {
                        result = n1 - n2;
                        changeParsed(parsed, i, result);
                        break;
                    }
                    if ("*".equals(element)) {
                        result = n1 * n2;
                        changeParsed(parsed, i, result);
                        break;
                    }
                    if ("/".equals(element)) {
                        result = n1 / n2;
                        changeParsed(parsed, i, result);
                        break;
                    }
                }
            }
        }

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", dfs);

        return df.format(result);
    }

    private void changeParsed(List<String> parsed, int i, float result) {
        parsed.set(i - 2, String.valueOf(result));
        parsed.remove(i - 1);
        parsed.remove(i - 1);
    }
}
