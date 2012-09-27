/**
 * @Model- Contains detail of individual bill item.
 */
package com.billing.model;

public class ItemBill {
	
	private String productCode;
	private double price;
	private double qty; //Quantity
	private String billType; // grocery(G), non-grocery(NG)

	/**
	 * @return the Product/Item Code
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * @return the Item Price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the Bill Type- grocery(G), non-grocery(NG)
	 */
	public String getBillType() {
		return billType;
	}
	/**
	 * @param billType the billType to set
	 */
	public void setBillType(String billType) {
		this.billType = billType;
	}
	/**
	 * @return the Item quantity
	 */
	public double getQty() {
		return qty;
	}
	/**
	 * @param qty the quantity to set
	 */
	public void setQty(double qty) {
		this.qty = qty;
	}
}
