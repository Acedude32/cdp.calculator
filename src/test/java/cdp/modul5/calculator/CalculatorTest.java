package cdp.modul5.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

    private Calculator calc = new Calculator();

    @Test
    public void test1() {
        assertEquals("5.00", calc.calculate("2 + 3"));
    }

    @Test
    public void test2() {
        assertEquals("6.00", calc.calculate("2 * 3"));
    }
    
    @Test
    public void test3() {
        assertEquals("6.00", calc.calculate("1 + 2 + 3"));
    }
    
    @Test
    public void test4() {
        assertEquals("2.00", calc.calculate("1 - 2 + 3"));
    }
    
    @Test
    public void test5() {
        assertEquals("0.00", calc.calculate("1 + 2 - 3"));
    }
    
    @Test
    public void test6() {
        assertEquals("1.50", calc.calculate("2 * 3 / 4"));
    }
    
    @Test
    public void test7() {
        assertEquals("2.67", calc.calculate("2 / 3 * 4"));
    }
    
    @Test
    public void test8() {
        assertEquals("7.00", calc.calculate("1 + 2 * 3"));
    }
    
    @Test
    public void test9() {
        assertEquals("5.00", calc.calculate("1 * 2 + 3"));
    }
    
    @Test
    public void test10() {
        assertEquals("-11.50", calc.calculate("2 / 4 - 4 * 3"));
    }
    
    @Test
    public void test11() {
        assertEquals("1.00", calc.calculate("2 - 4 * 1 + 3"));
    }
    
    @Test
    public void test12() {
        assertEquals("-5.50", calc.calculate("2 / 4 - 4 * 3 / 2"));
    }
    
    @Test
    public void test13() {
        assertEquals("3.50", calc.calculate("2 / 4 * 4 + 3 / 2"));
    }
    
    @Test
    public void test14() {
        assertEquals("Fail", calc.calculate("2 2 + 3"));
    }
    
    @Test
    public void test15() {
        assertEquals("Fail", calc.calculate("2 2 * + 3"));
    }
    
    @Test
    public void test16() {
        assertEquals("Fail", calc.calculate("- 2 + 1"));
    }
    
    @Test
    public void test17() {
        assertEquals("Fail", calc.calculate("a + 1"));
    }
    
}
