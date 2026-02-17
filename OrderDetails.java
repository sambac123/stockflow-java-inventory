package com.ait.inventoryManagement;

public class OrderDetails {

	private int orderId;
	private Customer customer;
	private Inventory inventory;
	private int quantity;
	private FullPayment fullpayment;
	private String status;
	private CancelReason cancelReason;
//	private String cancelStatus;
	
	
	
	public OrderDetails(int orderId, Customer customer,int quantity, Inventory inventory) {
		
		this.orderId=orderId;
		this.customer=customer;
		this.inventory=inventory;
		this.quantity=quantity;
		this.status="Placed";
		this.fullpayment=null;
		
		
	}
	
	public int getOrderId() {
		return orderId;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public String getStatus() {
		return status;
	}
	
	
	public void setpaymentDetails(FullPayment fullpayment) {
		this.fullpayment=fullpayment;
		this.status="Paid";
	}
	
	public void cancelOrder(CancelReason reason) {
		this.status="Cancelled";
		this.cancelReason=reason;
	}
	
	public boolean isPaid() {
		return fullpayment != null;
	}
	
	public void displayOrdetails() {
		System.out.println("Order Id         : "+orderId);
		System.out.println("Customer Name    : "+customer.getCustomerName());
		System.out.println("Products         : "+inventory.getProname());
		System.out.println("Product Quantity : "+quantity);
		System.out.println("Order Status     : "+status);
		System.out.println("         Thank you....!!");
		System.out.println("--------------------------------");
		
		if(status.equals("Cancelled")) {
			System.out.println("Cancel Reason : "+cancelReason);
		}
		
		if(fullpayment !=null) {
			System.out.println("=======Payment details========");
			fullpayment.paymentDetails();
		}else {
			System.out.println("Payment details : Not yet paid");
		}
		
		
		System.out.println("---------------------------");
	}
}
