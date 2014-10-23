import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;


public class FactorMainTest {
	public static void main(String[] args) {
		System.out.println("Test");
		getFactorsTest();
		testInputIntTest();
		getGreatestCommonDenominatorTest();
	}

	@Test
	//Test method for FactorMain.testInputInt() that checks the 0,1 and 100,101 
	//	edge cases
	public static void testInputIntTest() {
		Assert.assertFalse("edge case testInputInt(0) not false\n",FactorMain.testInputInt(0));
		Assert.assertTrue("edge case testInputInt(1) not true\n",FactorMain.testInputInt(1));
		Assert.assertTrue("edge case testInputInt(100) not true\n",FactorMain.testInputInt(100));
		Assert.assertFalse("edge case testInputInt(101) not false\n",FactorMain.testInputInt(101));
	}
	
//	@Test
//	public static void testQuitTest() {
//		Assert.assertTrue(FactorMain.testQuit("quit"));
//		Assert.assertTrue(FactorMain.testQuit("QUIT"));
//		String quit = "quit";
//		for(int i = 0; i < quit.length(); i++) {
//			Assert.assertTrue(FactorMain.testQuit(quit.));
//		}
//	}

	@Test
	//Test method for FactorMain.getFactors() that tests to see if each number contains
	//	1 and itself (as a base test to see if the code is working on a base scale)
	//	and makes sure that a series of numbers have the correct number of factors
	public static void getFactorsTest() {
		for(int i = 1; i <= 100; i++) {
			Assert.assertTrue("Factors don't include 1",FactorMain.getFactors(i).contains(1));
			Assert.assertTrue("Factors doesn't include " + i + " (itself)",FactorMain.getFactors(i).contains(i));
		}
		
		Assert.assertEquals("getFactors(60) doesn't output the correct number of factors\n",12,FactorMain.getFactors(60).size());
		Assert.assertEquals("getFactors(10) doesn't output the correct number of factors\n",4,FactorMain.getFactors(10).size());
		Assert.assertEquals("getFactors(1) doesn't output the correct number of factors\n",1,FactorMain.getFactors(1).size());
	}

	@Test
	//Test method for FactorMain.getGreatestCommonDenominator() that checks to see if
	//	when passed two sets of factors (either two different or it's self) that it
	//	returns the correct number
	public static void getGreatestCommonDenominatorTest() {
		ArrayList<Integer> factors1 = new ArrayList<Integer>();
		factors1.add(1);
		factors1.add(2);
		factors1.add(3);
		factors1.add(5);
		factors1.add(15);
		factors1.add(45);

		ArrayList<Integer> factors2 = new ArrayList<Integer>();
		factors2.add(1);
		factors2.add(2);
		factors2.add(3);
		factors2.add(5);
		factors2.add(15);
		factors2.add(60);

		Assert.assertEquals("getGreatestCommonDenominator(45, 45) should be 45\n",45, FactorMain.getGreatestCommonDenominator(factors1, factors1));
		Assert.assertEquals("getGreatestCommonDenominator(60, 60) should be 45\n",60, FactorMain.getGreatestCommonDenominator(factors2, factors2));
		Assert.assertEquals("getGreatestCommonDenominator(45, 60) should be 15\n",15, FactorMain.getGreatestCommonDenominator(factors1, factors2));
		Assert.assertEquals("getGreatestCommonDenominator(60, 45) should be 15\n",15, FactorMain.getGreatestCommonDenominator(factors2, factors1));
	}
}
