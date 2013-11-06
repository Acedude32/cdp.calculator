package cdp.modul5.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    
    public static final String OPERATION_PLUS = "+";
    public static final String OPERATION_MINUS = "-";
    public static final String OPERATION_MULTIPLY = "*";
    public static final String OPERATION_DIVISION = "/";
    
    public static final List<String> OPERATIONS = new ArrayList<String>();
    
    static {
        OPERATIONS.addAll(Arrays.asList(new String[] {OPERATION_PLUS, OPERATION_MINUS, OPERATION_MULTIPLY, OPERATION_DIVISION}));
    }


}
