package cdp.modul5.calculator.rpn;

import static cdp.modul5.calculator.Constants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cdp.modul5.calculator.api.IParser;

public class ParserRPN implements IParser {

    public List<String> parse(String input) {
        input = input.replaceAll("\\s+", "");
        List<String> stack = new ArrayList<String>();
        List<String> parsed = new ArrayList<String>();

        process(input, parsed, stack);

        return parsed;
    }
    private void process(String input, List<String> parsed, List<String> stack) {
        boolean lastParsedIsDigit = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                processDigit(lastParsedIsDigit, parsed, ch);
                lastParsedIsDigit = true;

            } else if (ch == DOT.charAt(0)) {
                processDigit(lastParsedIsDigit, parsed, ch);
                
            } else if (isOperation(String.valueOf(ch))) {
                processCharacter(ch, stack, parsed);
                lastParsedIsDigit = false;
                
            } else if (ch == OPEN_BRACKET.charAt(0)) {
                stack.add(String.valueOf(ch));
                lastParsedIsDigit = false;
            } else if (ch == CLOSE_BRACKET.charAt(0)) {
                lastParsedIsDigit = false;
                processCloseBracket(parsed, stack);
            }
            else {
                throw new IllegalArgumentException();   //TODO Add variable to count your current position and place this information into exception message
            }
        }

        Collections.reverse(stack);
        parsed.addAll(stack);

    }
    
    private void processCloseBracket(List<String> parsed, List<String> stack) {
        while (stack.size() > 0) {
            String last = stack.get(stack.size() - 1);
            if (last.equals(OPEN_BRACKET)) {
                stack.remove(stack.size() - 1);
                break;
            }
            parsed.add(last);
            stack.remove(stack.size() - 1);
        }
    }
    
    private void processCharacter(char ch, List<String> stack, List<String> parsed) {
        String newOperator = String.valueOf(ch);

        if (stack.size() > 0) {
            while (!stack.isEmpty() && newOperatorHasSameOrLowerPriority(newOperator, stack.get(stack.size() - 1)) ) {
                parsed.add(stack.get(stack.size() - 1));
                stack.remove(stack.size() - 1);
            }
        } 
        stack.add(newOperator);
        
    }
    private void processDigit(boolean lastParsedIsDigit, List<String> parsed, char ch) {
        if (lastParsedIsDigit) {
            processDigit(parsed, ch);
        } else {
            parsed.add(String.valueOf(ch));
        }
    }
    
    private void processDigit(List<String> parsed, char ch) {
        String digitValue = parsed.get(parsed.size() - 1);
        digitValue = digitValue + ch;
        parsed.set(parsed.size() - 1, digitValue);
    }
    
    private boolean isOperation(String s) {
        return OPERATIONS.contains(s);
    }

    private boolean newOperatorHasSameOrLowerPriority(String newOperator, String last) {
        return isOperatorsHaveSamePriority(newOperator, last) || isFirstOperatorHasLowerPriority(newOperator, last);
    }

    private boolean isFirstOperatorHasLowerPriority(String o1, String o2) {
        return isMediumPriorityOperator(o1) && isMediumPriorityOperator(o2)
                || isLowPriorityOperator(o1) && isLowPriorityOperator(o2);
    }

    private boolean isOperatorsHaveSamePriority(String o1, String o2) {
        return isLowPriorityOperator(o1) && (isMediumPriorityOperator(o2));
    }

    private boolean isMediumPriorityOperator(String o) {
        return o.equals(OPERATION_MULTIPLY) || o.equals(OPERATION_DIVISION);
    }

    private boolean isLowPriorityOperator(String o) {
        return o.equals(OPERATION_MINUS) || o.equals(OPERATION_PLUS);
    }
    
}
