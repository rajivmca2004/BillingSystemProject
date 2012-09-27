/**
 *@Model- Bill POJO. It contains collection of individual item bill data and User data
 */
package com.billing.model;

import java.util.List;

public class Bill {

	private User user;
	private List<ItemBill> itembill;
	
	/**
	 * @return the user object
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the itembill List
	 */
	public List<ItemBill> getItembill() {
		return itembill;
	}

	/**
	 * @param itembill the itembill to set
	 */
	public void setItembill(List<ItemBill> itembill) {
		this.itembill = itembill;
	}
	
	}
