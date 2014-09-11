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
	public static void testInputIntTest() {
		Assert.assertFalse(FactorMain.testInputInt(0));
		Assert.assertTrue(FactorMain.testInputInt(1));
		Assert.assertTrue(FactorMain.testInputInt(50));
		Assert.assertTrue(FactorMain.testInputInt(100));
		Assert.assertFalse(FactorMain.testInputInt(101));
	}

	@Test
	public static void getFactorsTest() {
		for(int i = 1; i <= 100; i++) {
			Assert.assertTrue(FactorMain.getFactors(i).contains(1));
			Assert.assertTrue(FactorMain.getFactors(i).contains(i));
		}
		Assert.assertEquals(12,FactorMain.getFactors(60).size());
		Assert.assertEquals(9,FactorMain.getFactors(100).size());
		Assert.assertEquals(6,FactorMain.getFactors(20).size());
		Assert.assertEquals(4,FactorMain.getFactors(10).size());
		Assert.assertEquals(2,FactorMain.getFactors(5).size());
		Assert.assertEquals(1,FactorMain.getFactors(1).size());
		
		Assert.assertEquals(null,FactorMain.getFactors(0));
		Assert.assertEquals(null,FactorMain.getFactors(101));
	}
	
	@Test
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
		
		Assert.assertEquals(45, FactorMain.getGreatestCommonDenominator(factors1, factors1));
		Assert.assertEquals(60, FactorMain.getGreatestCommonDenominator(factors2, factors2));
		Assert.assertEquals(15, FactorMain.getGreatestCommonDenominator(factors1, factors2));
		Assert.assertEquals(15, FactorMain.getGreatestCommonDenominator(factors2, factors1));
		Assert.assertNotSame(5, FactorMain.getGreatestCommonDenominator(factors1, factors2));
	}
}
