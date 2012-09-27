/**
 * Unit test cases for BillingDiscountHelperTest.java
 */
package test.billing;

/**
 * Test- Unit test cases for BillingDiscountHelper.java
 */
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.billing.BillingDiscountHelper;
import com.billing.model.Bill;
import com.billing.model.ItemBill;
import com.billing.model.User;
import com.billing.utilities.BillTypes;
import com.billing.utilities.UserTypes;

public class BillingDiscountHelperTest {
	Bill bill;
	User user;
	BillingDiscountHelper discountHelper;
	
	@Before
	public void init(){
		 bill=new Bill();
		 user= new User();
		 discountHelper= new BillingDiscountHelper();
	}

	//Test for User Type=Employee (@30% disc) and mix item types of Grocery and Non-grocery
	@Test
	public void testGetNetPayableAmountforEmployeeGrocery() {
		List<ItemBill> itembilllist= new ArrayList<ItemBill>();
		
		ItemBill itemBill1= new ItemBill();
		itemBill1.setProductCode("Item-1");
		itemBill1.setBillType(BillTypes.GROCERYITEM.getVal());
		itemBill1.setQty(1);
		itemBill1.setPrice(100.00);
		itembilllist.add(itemBill1);

		ItemBill itemBill2= new ItemBill();
		itemBill2.setProductCode("Item-2");
		itemBill2.setBillType(BillTypes.NONGROCERYITEM.getVal());
		itemBill2.setQty(2);
		itemBill2.setPrice(100.00);
		itembilllist.add(itemBill2);

		user.setUserId("E111");
		user.setUserType(UserTypes.EMPLOYEE.getVal());
		bill.setItembill(itembilllist);
		bill.setUser(user);
		
		System.out.println("Net Payable Amount: $"+discountHelper.getNetPayableAmount(bill));
		Assert.assertEquals(225.0, discountHelper.getNetPayableAmount(bill));
	}
	
	//Test for User Type=Affiliate (@10% disc) and mix item types of Grocery and Non-grocery

	@Test
	public void testGetNetPayableAmountforAffiliate() {
		List<ItemBill> itembilllist= new ArrayList<ItemBill>();
		
		ItemBill itemBill1= new ItemBill();
		itemBill1.setProductCode("Item-1");
		itemBill1.setBillType(BillTypes.GROCERYITEM.getVal());
		itemBill1.setQty(1);
		itemBill1.setPrice(100.00);
		itembilllist.add(itemBill1);

		ItemBill itemBill2= new ItemBill();
		itemBill2.setProductCode("Item-2");
		itemBill2.setBillType(BillTypes.NONGROCERYITEM.getVal());
		itemBill2.setQty(1);
		itemBill2.setPrice(100.00);
		itembilllist.add(itemBill2);

		user.setUserId("A411");
		user.setUserType(UserTypes.AFFLILIATE.getVal());
		bill.setItembill(itembilllist);
		bill.setUser(user);
		
		System.out.println("Net Payable Amount: $"+discountHelper.getNetPayableAmount(bill));
		Assert.assertEquals(180.0, discountHelper.getNetPayableAmount(bill));
	}
	
	/*
	 * Test for User Type=Customer (@5% disc) who is associated with store for more than 2 years
	 * and mix item types of Grocery and Non-grocery
	 */
		@Test
		public void testGetNetPayableAmountforCustomer() {
			List<ItemBill> itembilllist= new ArrayList<ItemBill>();
			
			ItemBill itemBill1= new ItemBill();
			itemBill1.setProductCode("Item-1");
			itemBill1.setBillType(BillTypes.GROCERYITEM.getVal());
			itemBill1.setQty(1);
			itemBill1.setPrice(100.00);
			itembilllist.add(itemBill1);

			ItemBill itemBill2= new ItemBill();
			itemBill2.setProductCode("Item-2");
			itemBill2.setBillType(BillTypes.NONGROCERYITEM.getVal());
			itemBill2.setQty(1);
			itemBill2.setPrice(100.00);
			itembilllist.add(itemBill2);

			user.setUserId("C311");
			user.setUserType(UserTypes.OTHER_TWOYEAR_OLD_CUSTOMER.getVal());
			bill.setItembill(itembilllist);
			bill.setUser(user);
			
			System.out.println("Net Payable Amount: $"+discountHelper.getNetPayableAmount(bill));
			Assert.assertEquals(185.0, discountHelper.getNetPayableAmount(bill));
		}
		
	//Test discount calculation for userType=Employee
	
		@Test
		public void testGetNetPayableAmountforEmployee() {
			List<ItemBill> itembilllist= new ArrayList<ItemBill>();
			
			ItemBill itemBill1= new ItemBill();
			itemBill1.setProductCode("Item-1");
			itemBill1.setBillType(BillTypes.NONGROCERYITEM.getVal());
			itemBill1.setQty(1);
			itemBill1.setPrice(100.00);
			itembilllist.add(itemBill1);
			
			user.setUserId("E111");
			user.setUserType(UserTypes.EMPLOYEE.getVal());
			bill.setItembill(itembilllist);
			bill.setUser(user);
			
			System.out.println("Net Payable Amount: $"+discountHelper.getNetPayableAmount(bill));
			Assert.assertEquals(65.0, discountHelper.getNetPayableAmount(bill));
		}

		//Test discount calculation for userType=Default User
		
			@Test
			public void testGetNetPayableAmountforDefalut() {
				List<ItemBill> itembilllist= new ArrayList<ItemBill>();
				
				ItemBill itemBill1= new ItemBill();
				itemBill1.setProductCode("Item-1");
				itemBill1.setBillType(BillTypes.NONGROCERYITEM.getVal());
				itemBill1.setQty(1);
				itemBill1.setPrice(100.00);
				itembilllist.add(itemBill1);
				
				ItemBill itemBill2= new ItemBill();
				itemBill2.setProductCode("Item-2");
				itemBill2.setBillType(BillTypes.GROCERYITEM.getVal());
				itemBill2.setQty(1);
				itemBill2.setPrice(100.00);
				itembilllist.add(itemBill2);

				
				user.setUserId("D211");
				user.setUserType(UserTypes.DEFAULT.getVal());
				bill.setItembill(itembilllist);
				bill.setUser(user);
				
				System.out.println("Net Payable Amount: $"+discountHelper.getNetPayableAmount(bill));
				Assert.assertEquals(190.0, discountHelper.getNetPayableAmount(bill));
			}
		
		//Test for no/null user type
		@Test(expected= IllegalArgumentException.class)
		public void testGetNetPayableAmountforNullUserType() {
			List<ItemBill> itembilllist= new ArrayList<ItemBill>();
			
			ItemBill itemBill1= new ItemBill();
			itemBill1.setProductCode("Item-1");
			itemBill1.setBillType(BillTypes.GROCERYITEM.getVal());
			itemBill1.setQty(1);
			itemBill1.setPrice(100.00);
			itembilllist.add(itemBill1);

			user.setUserId("E111");
			user.setUserType(null);
			bill.setItembill(itembilllist);
			bill.setUser(user);
			
			discountHelper.getNetPayableAmount(bill);
			Assert.fail("It should throw IllegalArgumentException for null value of Bill Item");
		}
	
	// Test for null Bill object
	@Test(expected = IllegalArgumentException.class)
	public void testGetNetPayableAmountforNullBill() {
		bill = null;
		discountHelper.getNetPayableAmount(bill);
		Assert.fail("It should throw IllegalArgumentException for null value of Bill Object");
	}
	
	//Test for null Bill item 
	@Test(expected = IllegalArgumentException.class)
	public void testGetNetPayableAmountforNullBillItem() {

		user.setUserId("E111");
		user.setUserType(UserTypes.EMPLOYEE.getVal());
		bill.setUser(user);
		bill.setItembill(null);
		discountHelper.getNetPayableAmount(bill);
		Assert.fail("It should throw IllegalArgumentException for null value of Bill Item object");
	}
	
	//Test for null User Object
		@Test(expected = IllegalArgumentException.class)
		public void testGetNetPayableAmountforNullUser() {
			List<ItemBill> itembilllist= new ArrayList<ItemBill>();
			
			ItemBill itemBill1= new ItemBill();
			itemBill1.setProductCode("Item-1");
			itemBill1.setBillType(BillTypes.GROCERYITEM.getVal());
			itemBill1.setQty(1);
			itemBill1.setPrice(100.00);
			itembilllist.add(itemBill1);

			bill.setItembill(itembilllist);
			bill.setUser(null);
			
			discountHelper.getNetPayableAmount(bill);
			Assert.fail("It should throw IllegalArgumentException for null value of User object");
		}
		
	
	//Test for null/empty Bill Type 
		@Test(expected=IllegalArgumentException.class)
		public void testGetNetPayableAmountforNullBillType() {
			List<ItemBill> itembilllist= new ArrayList<ItemBill>();
			
			ItemBill itemBill1= new ItemBill();
			itemBill1.setProductCode("Item-1");
			itemBill1.setBillType(null);
			itemBill1.setQty(1);
			itemBill1.setPrice(100.00);
			itembilllist.add(itemBill1);

			user.setUserId("E111");
			user.setUserType(UserTypes.EMPLOYEE.getVal());
			bill.setItembill(itembilllist);
			bill.setUser(user);
			
			System.out.println("Net Payable Amount: $"+discountHelper.getNetPayableAmount(bill));
			Assert.assertEquals(65.0, discountHelper.getNetPayableAmount(bill));
		}
		
		//Test for non-specified user type
		
				@Test(expected= IllegalArgumentException.class)
				public void testGetNetPayableAmountforUnknownUserType() {
					List<ItemBill> itembilllist= new ArrayList<ItemBill>();
					
					ItemBill itemBill1= new ItemBill();
					itemBill1.setProductCode("Item-1");
					itemBill1.setBillType(BillTypes.GROCERYITEM.getVal());
					itemBill1.setQty(1);
					itemBill1.setPrice(100.00);
					itembilllist.add(itemBill1);

					user.setUserId("E111");
					user.setUserType("GARBAGE");
					bill.setItembill(itembilllist);
					bill.setUser(user);
					
					discountHelper.getNetPayableAmount(bill);
					Assert.fail("It should throw IllegalArgumentException for null value of Bill Item");
				}
				
		@After
		public void destroy(){
			 bill=null;
			 user= null;
			 discountHelper= null;
		}
		
}
