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

    //TODO Throw a runtime exception with information about position where operation is incorrect.
    private boolean validate(String input) {
        
        // check if input == null
        if (input == null) {    //TODO Better to throw an exception here.
            return false;
        }
        
        // check if there is no operator between two numbers 
        String regex = "\\d\\s+\\d";        // This regex doesn't check what you need. It checks only spaces between digits...
                                            // The main idea was to go through entities using mather.
                                            // TODO Write correct regex, if possible, to check whole string for errors.
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return false;
        }
        
        return true;
        // TODO maybe need more validation cases
    }

    //TODO Please, refactor this method. Cut it into methods. This will increase readability and flexibility.
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
                throw new IllegalArgumentException();   //TODO Add variable to count your current position and place this information into exception message
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

        return (newOperator.equals("*") || newOperator.equals("/")) && (last.equals("*") || last.equals("/"))       //TODO Rewrite this logic. Very difficult to read and not flexible.
                || (newOperator.equals("+") || newOperator.equals("-")) && (last.equals("+") || last.equals("-"))   //TODO Create constants
                || (newOperator.equals("+") || newOperator.equals("-")) && (last.equals("*") || last.equals("/"));
    }

    private boolean isOperation(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {     //TODO Use list of objects (or at least chars) to check whether it supported operation or not.
            return true;
        }
        return false;
    }

    private boolean isOperation(String s) {
        if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)) { //TODO The same as in previous method.
            return true;
        }
        return false;
    }

    private String calculateParsed(List<String> parsed) {
        float result = 0;
        while (parsed.size() > 1) {
            for (int i = 0; i < parsed.size(); i++) {
                String element = parsed.get(i);
                if (isOperation(element)) {         //TODO Place everything from this if statement into method.
                    float n1 = Float.valueOf(parsed.get(i - 2));
                    float n2 = Float.valueOf(parsed.get(i - 1));

                    if ("+".equals(element)) {      //TODO Rewrite this ifs. You can use switches, but it would be better to use objects in list
                                                    //TODO which can check whether this operation is supported and calculate it.
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
