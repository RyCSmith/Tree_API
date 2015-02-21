package tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * JUnit test class for Expression.java.
 * @author ryancsmith
 *
 */
public class ExpressionTest {
	Expression goodExp1;
	Expression badExp1;
	Expression goodExp2;
	Expression badExp2;
	Expression badExp3;
	
    @Before
    public void setUp() throws Exception {
    	
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testExpression() {
    	goodExp1 = new Expression("+ (5 10 -( *(15 20) 25) 30)");
    	//non-leaf number
		badExp1 = new Expression("4 (5 10 -( *(15 20) 25) 30)");
		//non-number value
		badExp2 = new Expression("+ (5 ten -( *(15 20) 25) 30)");
		//multiplication with only one child
		badExp3 = new Expression("4 (5 10 -( *(15) 25) 30)");
    }

    @Test
    public final void testEvaluate() {
    	goodExp1 = new Expression("+(/ (+ (5 10 -( *(15 20) 25) 30) 3)3 4)");
    	assertEquals(113, goodExp1.evaluate());
    	goodExp2 = new Expression("+ (5 10 -( *(15 20) 25) 30)");
    	assertEquals(320, goodExp2.evaluate());
    	
    }

    @Test
    public final void testToString() {
    	goodExp1 = new Expression("+(/ (+ (5 10 -( *(15 20) 25) 30) 3)3 4)");
    	goodExp2 = new Expression("+ (5 10 -( *(15 20) 25) 30)");
    	assertEquals("(((5 + 10 + ((15 * 20) - 25) + 30) / 3) + 3 + 4)", goodExp1.toString());
    	assertEquals("(5 + 10 + ((15 * 20) - 25) + 30)", goodExp2.toString());
    }

}
