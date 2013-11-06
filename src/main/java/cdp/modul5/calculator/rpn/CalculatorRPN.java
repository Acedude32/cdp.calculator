package cdp.modul5.calculator.rpn;

import static cdp.modul5.calculator.Constants.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cdp.modul5.calculator.api.ICalculator;
import cdp.modul5.calculator.api.IParser;

public class CalculatorRPN implements ICalculator{
    private IParser parser;

    public CalculatorRPN() {
        parser = new ParserRPN();
    }

    public float calculate(String input) {
        float result = 0;
        validate(input);
        List<String> parsed = parser.parse(input);
        result = calculateParsed(parsed);
        return result;
    }

    private void validate(String input) {
        checkIfNullOrEmpty(input);
        checkExpresiionIsCorrect(input);
    }

    private void checkExpresiionIsCorrect(String input) {
        String regex = "(\\s*\\d+\\s*[\\+\\-\\*\\/]\\s*){1,}\\d+\\s*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            int failPosition = getFailPosition(pattern, input);
            throw new IllegalArgumentException(failPosition + "");
        }
    }

    private int getFailPosition(Pattern pattern, String input) {
        for (int i = 0; i <= input.length(); i++) {
            Matcher matcher = pattern.matcher(input.substring(0, i));
            if (!matcher.matches() && !matcher.hitEnd()) {
                return i - 1;
            }
        }
        return input.length();
    }

    private void checkIfNullOrEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("input is empty");
        }
    }

    private boolean isOperation(String s) {
        return OPERATIONS.contains(s);
    }

    private float calculateParsed(List<String> parsed) {
        float result = 0;
        while (parsed.size() > 1) {
            for (int i = 0; i < parsed.size(); i++) {
                String element = parsed.get(i);
                if (isOperation(element)) {
                    result = performCalculations(parsed, element, result, i);
                    break;
                }
            }
        }
        return result;
    }

    private float performCalculations(List<String> parsed, String element, float result, int i) {
        float n1 = Float.valueOf(parsed.get(i - 2));
        float n2 = Float.valueOf(parsed.get(i - 1));

        if (OPERATION_PLUS.equals(element)) {
            result = n1 + n2;
        }
        if (OPERATION_MINUS.equals(element)) {
            result = n1 - n2;
        }
        if (OPERATION_MULTIPLY.equals(element)) {
            result = n1 * n2;
        }
        if (OPERATION_DIVISION.equals(element)) {
            result = n1 / n2;
        }
        changeParsed(parsed, i, result);
        return result;
    }

    private void changeParsed(List<String> parsed, int i, float result) {
        parsed.set(i - 2, String.valueOf(result));
        parsed.remove(i - 1);
        parsed.remove(i - 1);
    }
}
