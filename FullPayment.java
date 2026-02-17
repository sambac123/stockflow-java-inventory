package com.ait.inventoryManagement;

public class FullPayment {
	
	private double totalAmount;
	private double paidAmount;
	private String paymentStatus;
	
	public FullPayment(OrderDetails orderDetails) {
		this.totalAmount=orderDetails.getInventory().getPrice()*orderDetails.getQuantity();
		this.paidAmount=totalAmount;
		this.paymentStatus="Payment successfully paid";
	}
	
	public void paymentDetails() {
		System.out.println("Total Amount     : "+totalAmount);
		System.out.println("Paid Amount      : "+paidAmount);
		System.out.println("Payment Status   : "+paymentStatus);
	}
	

}
