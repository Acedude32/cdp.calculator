package cdp.modul5.calculator.rpn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cdp.modul5.calculator.rpn.CalculatorRPN;

public class CalculatorRPNTest {

    private CalculatorRPN calc = new CalculatorRPN();

//    @Test
//    public void test1() {
//        assertEquals(5.0, calc.calculate("2 + 3"), 0.01);
//    }
//
//    @Test
//    public void test2() {
//        assertEquals(66.0, calc.calculate("22 * 3"), 0.01);
//    }
//    
//    @Test
//    public void test3() {
//        assertEquals(6.0, calc.calculate("1 + 2 + 3"), 0.01);
//    }
//    
//    @Test
//    public void test4() {
//        assertEquals(2.0, calc.calculate("1 - 2 + 3"), 0.01);
//    }
//    
//    @Test
//    public void test5() {
//        assertEquals(0.0, calc.calculate("1 + 2 - 3"), 0.01);
//    }
//    
//    @Test
//    public void test6() {
//        assertEquals(1.5, calc.calculate("2 * 3 / 4"), 0.01);
//    }
//    
//    @Test
//    public void test7() {
//        assertEquals(2.67, calc.calculate("2 / 3 * 4"), 0.01);
//    }
//    
//    @Test
//    public void test8() {
//        assertEquals(7.0, calc.calculate("1 + 2 * 3"), 0.01);
//    }
//    
//    @Test
//    public void test9() {
//        assertEquals(5.0, calc.calculate("1 * 2 + 3"), 0.01);
//    }
//    
//    @Test
//    public void test10() {
//        assertEquals(-11.5, calc.calculate("2 / 4 - 4 * 3"), 0.01);
//    }
//    
//    @Test
//    public void test11() {
//        assertEquals(1.0, calc.calculate("2 - 4 * 1 + 3"), 0.01);
//    }
//    
//    @Test
//    public void test12() {
//        assertEquals(-5.5, calc.calculate("2 / 4 - 4 * 3 / 2"), 0.01);
//    }
//    
//    @Test
//    public void test13() {
//        assertEquals(3.5, calc.calculate("2 / 4 * 4 + 3 / 2"), 0.01);
//    }
//    
//    @Test(expected=IllegalArgumentException.class)
//    public void test14() {
//        assertEquals("Fail", calc.calculate("2 2 + 3"));
//    }
//    
//    @Test(expected=IllegalArgumentException.class)
//    public void test15() {
//        assertEquals("Fail", calc.calculate("2 2 * + 3"));
//    }
//    
//    @Test(expected=IllegalArgumentException.class)
//    public void test16() {
//        assertEquals("Fail", calc.calculate("- 2 + 1"));
//    }
//    
//    @Test(expected=IllegalArgumentException.class)
//    public void test17() {
//        assertEquals("Fail", calc.calculate("a + 1"));
//    }
    
    @Test
    public void testBrackets1() {
        assertEquals(9.0, calc.calculate("(1 + 2) * 3"), 0.01);
    }
    
    @Test
    public void testBrackets2() {
        assertEquals(10.0, calc.calculate("(1 + 2) * 3 + (1 + 2) / 3"), 0.01);
    }
    
    @Test
    public void testBrackets3() {
        assertEquals(6.0, calc.calculate("(1 + 2) * (1 + 1)"), 0.01);
    }

    @Test
    public void testBrackets4() {
        assertEquals(6.0, calc.calculate("(1 + 2))"), 0.01);
    }

}
