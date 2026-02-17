package com.ait.inventoryManagement;

public class Customer {

	private int customerId;
	private String customerName;
	private String mobileNumber;
	
	public Customer(int customerId, String customerName, String mobileNumber) {
		this.customerId=customerId;
		this.customerName=customerName;
		this.mobileNumber=mobileNumber;
		}
	public int getCustomerId() {
		return customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public String getMobbileNumber() {
		return mobileNumber;
	}
	
	public void displayCustomerDetails() {
		System.out.println("Customer Id: "+customerId);
		System.out.println("Coustomer Name : "+customerName);
		System.out.println("Mobile Number: "+mobileNumber);
	}
}
