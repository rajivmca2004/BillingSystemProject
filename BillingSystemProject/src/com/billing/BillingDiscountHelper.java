/**
 * @author Rajiv Srivastava
 * Main helper class that is responsible for discount calculation.It's entry point for this application.
 * Approach : Test driven approach (TDD) thru JUnit test classes.
 */
package com.billing;

import java.util.List;

import com.billing.model.Bill;
import com.billing.model.ItemBill;
import com.billing.model.User;
import com.billing.service.BusinessService;
import com.billing.utilities.BillTypes;
import com.billing.utilities.DiscountTypes;
import com.billing.utilities.UserTypes;

public class BillingDiscountHelper {
	/**
	 * @param - Bill data that could contains multiple grocery/non-grocery bill items
	 * @return - Calculated total Net payable amount after discount.
	 */
	public double getNetPayableAmount(Bill bill) {
		BusinessService service = new BusinessService();
		double totalBillAmount = 0;
		double totalNetPayableAmount=0;
		double netPayableAmount=0;
		double nonPercentageDiscBillAmt=0;
		double percentageDiscBillAmt=0;
		double nonpercentageDiscount=0;
		DiscountTypes discountType=null;
	
		// Validate Bill Data
		validateBillData(bill);
		
		List<ItemBill> itemBills = bill.getItembill();
		for (ItemBill itemBill : itemBills) {
			
			if (isDiscountOnItemBill(itemBill.getBillType()) && isNotDefaultUser(bill.getUser().getUserType())) {
				//Sum bill amount of those bill item(s) which is applicable for percentage based discount.
				percentageDiscBillAmt += service.calculateItemBillAmount(itemBill.getQty(), itemBill.getPrice());
			}
			else{
				//Sum bill amount of those bill item(s) which is not-applicable for percentage based discount.
				nonPercentageDiscBillAmt +=service.calculateItemBillAmount(itemBill.getQty(), itemBill.getPrice());
			}
		}
		//  Get Applicable Discount type for given user
		if (isNotDefaultUser(bill.getUser().getUserType())) {
			discountType = getDiscountTypeForUser(bill.getUser());
			if (null == discountType) {
				throw new IllegalArgumentException("User type is not specified !!!");
			} else {
				// Calculate net payable amount of sum of amount on which percentage discount is applicable(percentageDiscBillAmt) 
				netPayableAmount = service.calculateNetPayableAmount(percentageDiscBillAmt, discountType);
			}
		}
		/* Total Bill amount which is sum of amounts on which percentage discount is applicable 
		and percentage discount is not-applicable*/
		totalBillAmount=percentageDiscBillAmt+nonPercentageDiscBillAmt;
		
		// nonpercentageDiscount= $5 discount on each $100 
		nonpercentageDiscount=service.calculateNonPercentageDiscount(totalBillAmount);
		
		/**
		 *  Calculate final net payable amount after discount on given bill amount
		 */

		totalNetPayableAmount = (netPayableAmount + nonPercentageDiscBillAmt) - nonpercentageDiscount;
		
		return totalNetPayableAmount;
	}
	
	/**
	 * @param- User Type
	 * @return - Check for Default user. Return false-Default, true- For specified users 
	 */
	private boolean isNotDefaultUser(String userType) {
		Boolean flag=true;
		
		if(null==userType){
			throw new IllegalArgumentException("User Type is mandatory !!!!");
		}
			
		if(userType.equals(UserTypes.DEFAULT.getVal())){
			flag=false;
		}
		return flag;
	}

	/**
	 * @param- Bill object data
	 * @return true if input Bill data is correct
	 */
	private void validateBillData(Bill bill) {
		if(null==bill){
			throw new IllegalArgumentException("Bill data is incorrect !!!");
		}else if(null==bill.getItembill() || bill.getItembill().isEmpty()){
			throw new IllegalArgumentException("Item Bill data is incorrect !!!");
		}else if(null==bill.getUser()){
			throw new IllegalArgumentException("User data is incorrect !!!");
		}
	}

	/**
	 * @param - User data
	 * @return - Discount type
	 */
    private DiscountTypes getDiscountTypeForUser(User user){
    	//Default discount which doesn't fall in any category of Employee, Affiliate and customer of 2 yrs above
    	DiscountTypes discountType = null;
    	
    	if(null!=user){
    	if(UserTypes.EMPLOYEE.getVal().equals(user.getUserType())){
    		discountType=DiscountTypes.EMPLOYEE;
    	}else if(UserTypes.AFFLILIATE.getVal().equals(user.getUserType())){
    		discountType=DiscountTypes.AFFILIATE;
    	}else if(UserTypes.OTHER_TWOYEAR_OLD_CUSTOMER.getVal().equals(user.getUserType())){
    		discountType=DiscountTypes.OTHER_TWOYEAR_OLD_CUSTOMER;
    	}
    	}else{
    		System.out.println("User data is empty");
    	}
    	 return discountType; 
	}
    
    /**
	 * @param - Bill type
	 * @return - false: If bill item is Grocery, true: If Non-grocery 
	 */
	private boolean isDiscountOnItemBill(String billType){
		// Discount is applicable only on Grocery items
		Boolean validDiscount=true;
		
		if(null==billType || billType.isEmpty()){
			throw new IllegalArgumentException("Bill Type should not be empty !!!");
		}
		else if(BillTypes.GROCERYITEM.getVal().equals(billType)){
			validDiscount=false;
		}
		return validDiscount;
	}
}
