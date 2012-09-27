/**
 * @Utilties- It contains Bill types constants.
 */
package com.billing.utilities;

public enum BillTypes {
	//Grocery Item
	GROCERYITEM("GROCERY"),
	//Non-Grocery Item
	NONGROCERYITEM("NONGROCERY");
	
	String value;
	
	private BillTypes(String value) {
		this.value = value;
	}
	public String getVal(){
		return value;
	}
}
