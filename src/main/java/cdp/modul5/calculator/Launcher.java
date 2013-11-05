package cdp.modul5.calculator;


public class Launcher {
    public static void main(String[] args) {

        final String input = System.getenv("input"); //getting input
        Calculator calc = new Calculator();
        String result = calc.calculate(input);
        System.out.println(result);
    }
}
