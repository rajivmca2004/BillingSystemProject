/**
 * @Utilities- Customer Types Constants/codes
 *  DEFAULT("D") - Who doesn't fall in any of the given category 
  * EMPLOYEE("E") - Employee of the store
  * AFFLILIATE("A") - Affiliated customer
  * OTHER_TWOYEAR_OLD_CUSTOMER - Customer who has associated with store from more than 2 yrs
 */
package com.billing.utilities;

public enum UserTypes {
 
	DEFAULT("D"),
	EMPLOYEE("E"),
	AFFLILIATE("A"),
	OTHER_TWOYEAR_OLD_CUSTOMER("TWO_YR_OLD_CUST");
	
	/**
	 * @param val= Customer Types
	 */
	String val;
	private UserTypes(String val) {
		this.val = val;
	}
	
	//@return- Return code of User type
	public String getVal() {
		return val;
	}
 
}
