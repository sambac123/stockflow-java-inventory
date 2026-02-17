package com.ait.inventoryManagement;

public class CancelOrder {

	private String cancelOrder;
	private String orderStatus;
	private String reason;
	
	
	public CancelOrder(String cancelOrder, String orderStatus,String reason) {
		
		this.cancelOrder=cancelOrder;
		this.orderStatus="Cancelled";
		this.reason=reason;
	}
	public String getReason() {
		return reason;
	}
	
	public void cancelOrder() {
		System.out.println("Order Cancel         : "+cancelOrder);
		System.out.println("Order Status         : "+orderStatus);
		System.out.println("Cancellation Reason  : "+reason);
	}
	
	
}
