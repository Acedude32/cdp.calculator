package cdp.modul5.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public float calculate(String input) {
        input = input.replaceAll("\\s+", "");
        if (validate(input)) {
            List<String> parsed = parse(input);
            float result = count(parsed);
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean validate(String input) {
        // TODO validation;
        return true;
    }

    private List<String> parse(String input) {
        List<String> stack = new ArrayList<String>();
        List<String> parsed = new ArrayList<String>();
        boolean digit = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                if (digit) {
                    String digitValue = parsed.get(parsed.size() - 1);
                    digitValue = digitValue + ch;
                    parsed.set(parsed.size() - 1, digitValue);
                } else {
                    parsed.add(String.valueOf(ch));
                    digit = true;
                }
            } else if (isOperation(ch)) {
                String newOperator = String.valueOf(ch);

                if (stack.size() > 0) {

//                    String last = stack.get(stack.size() - 1);

                    if ((newOperator.equals("+") || newOperator.equals("-"))) {
                        for (int j = stack.size() - 1; j >= 0; j--) {
                            parsed.add(stack.get(j));
                        }
                        stack.clear();
                    }
                }
                stack.add(newOperator);
                digit = false;
            } else {
                throw new IllegalArgumentException();
                // TODO remove it after validation creation

            }
        }
        for (int i = stack.size() - 1; i >= 0; i--) {
            parsed.add(stack.get(i));
        }
        return parsed;
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

    private float count(List<String> parsed) {
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
        return result;
    }

    private void changeParsed(List<String> parsed, int i, float result) {
        parsed.set(i - 2, String.valueOf(result));
        parsed.remove(i - 1);
        parsed.remove(i - 1);
    }
}
