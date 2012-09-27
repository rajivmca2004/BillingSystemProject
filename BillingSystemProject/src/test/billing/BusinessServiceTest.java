/**
 * Test- Unit test cases for BusinessService.java
 */
package test.billing;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.billing.service.BusinessService;
import com.billing.utilities.DiscountTypes;

public class BusinessServiceTest {
	BusinessService service;
	
	@Before
	public void init(){
		service=new BusinessService();
	}
	// Test for Employee Discount
	@Test
	public void testCalculateNetPayableAmountforEmployee() {
		Assert.assertEquals(70.0, service.calculateNetPayableAmount(100, DiscountTypes.EMPLOYEE));
	}
	
	// Test for Affiliate Discount
	@Test
	public void testCalculateNetPayableAmountforAffiliate() {
		Assert.assertEquals(90.0, service.calculateNetPayableAmount(100, DiscountTypes.AFFILIATE));
	}
	
	// Test for Customer Discount
	@Test
	public void testCalculateNetPayableAmountforCustomer() {
		Assert.assertEquals(95.0, service.calculateNetPayableAmount(100, DiscountTypes.OTHER_TWOYEAR_OLD_CUSTOMER));
	}

	// Test for Hundred Dollar
	@Test
	public void testCalculateNetPayableAmountforEachHundredDoll() {
		Assert.assertEquals(95.0, service.calculateNetPayableAmount(100,DiscountTypes.EACH_HUNDRED_DOLLAR));
	}


	// Test for Null discount type
	@Test(expected=IllegalArgumentException.class)
	public void testCalculateNetPayableAmountforNulll() {
		Assert.assertEquals(95.0, service.calculateNetPayableAmount(100,null));
	}
	
	//Test Non percentage discount on total bill. Business rule: $5 discount on each $100 bill
	@Test
	public void testCalculateNonPercentageDiscount() {
		Assert.assertEquals(5.0,service.calculateNonPercentageDiscount(100.0));
	}
	
	//Test discount of multiple of $100. It should return 0.0 value for lesser than $100 bill amount
	@Test
	public void testCalculateNonPercentageDiscountforInvalidValue() {
		Assert.assertEquals(0.0,service.calculateNonPercentageDiscount(99));
	}
	
	// Test discount of multiple of $100. It should return 0.0 value for lesser than negative value bill amount
	@Test
	public void testCalculateNonPercentageDiscountforNegativeValue() {
		Assert.assertEquals(0.0, service.calculateNonPercentageDiscount(-1));
	}

	//Test for expected value = quantity*unit price
	@Test
	public void testCalculateItemBillAmount() {
		Assert.assertEquals(200.0,service.calculateItemBillAmount(2, 100.0));
	}
	
	// Test for invalid value
	@Test(expected=IllegalArgumentException.class)
	public void testCalculateItemBillAmountforInvalidValues1() {
		service.calculateItemBillAmount(-2, 100.0);
	}
	
	// Test for invalid value
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateItemBillAmountforInvalidValues2() {
		service.calculateItemBillAmount(-2, -100.0);
	}
	
	// Test for invalid value
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateItemBillAmountforInvalidValues3() {
		service.calculateItemBillAmount(0, 0);
	}
	
	// Test for invalid value
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateItemBillAmountforInvalidValues4() {
		service.calculateItemBillAmount(0.5, 0.5);
	}
	
	@After
	public void destroy(){
		service=null;
	}

}
