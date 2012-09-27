/**
 * @Utilties- It contains discount types constants.
 * Business Rules : Various discount types for:
 *  EMPLOYEE (Employee of store)- 30%
 *  AFFLILIATE (Affiliated customer) -20%
 *  OTHER_TWOYEAR_OLD_CUSTOMER (Customer who has associated with store from more then 2 years)- 5%
 *  EACH_HUNDRED_DOLLAR - $5 Discount on purchase of each $100. It's a non-percentage discount.
 */
package com.billing.utilities;

public enum DiscountTypes {
	EMPLOYEE(30),
	AFFILIATE(10),
	OTHER_TWOYEAR_OLD_CUSTOMER(5),
	EACH_HUNDRED_DOLLAR(5);
	
	int discount;

	private DiscountTypes(int discount) {
		this.discount = discount;
	}
	
	//@return- Return Applied Discount
	
	public int getDiscount() {
		return discount;
	}
}
