/**
 * @Service- Contains business logics of discount calculation. 
 */
package com.billing.service;

import com.billing.utilities.DiscountTypes;

public class BusinessService {
	
	/** @param1-Total bill amount, @param2-Discount Type
	 * @return-Net payable amount
	 * */
	public double calculateNetPayableAmount(double totalbillamount, DiscountTypes discountType) {
		if(discountType==null){
			throw new IllegalArgumentException("Discount type is null !!!");
		}
		return totalbillamount - calculateDiscount(totalbillamount,discountType);
	}
	
	/** @param1- Total bill amount, @param2- Discount Type
	 * @return- Calculated discount
	 * */
	private double calculateDiscount(double totalbillamount,DiscountTypes discountType) {
		return Math.round((totalbillamount * discountType.getDiscount()) / 100);
	}

	/**
	 * @param - Total bill amount
	 * @return - Calculated net-percentage discount of $5 based on multiple of each $100
	 */
	public double calculateNonPercentageDiscount(double totalbillamount) {
		double npdisc=0;
		if(totalbillamount>=100){
			npdisc=Math.floor(totalbillamount / 100)*DiscountTypes.EACH_HUNDRED_DOLLAR.getDiscount();
		}
		return npdisc;
	}
	
	/**
	 * @param1 - Quantity
	 * @param2- Unit price
	 * @return - Calculated accumulated sum of quantity* unit
	 */
	public double calculateItemBillAmount(double qty, double price) {
		if(qty<1 || price<1){
			throw new IllegalArgumentException("Item quantity or price value should noe be less than or equal to zero !!!");
		}
		return qty * price;
	}
	
}
