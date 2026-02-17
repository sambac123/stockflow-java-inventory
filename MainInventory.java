package com.ait.inventoryManagement;

import java.sql.SQLException;
import java.util.*;

public class MainInventory {

	public static void main(String[] args) throws SQLException {
		
		InventoryManagement inventory=new InventoryManagement();
		
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			System.out.println("=========Smart Order && Inventory Management System=========");
			System.out.println("1.Add Product ");
			System.out.println("2.View All products");
			System.out.println("3.Rigister Customer");
			System.out.println("4.Place Order");
			System.out.println("5.View Orders");
			System.out.println("6.View Low Stock Products");
			System.out.println("7.Payment Details");
			System.out.println("8.Cancel Order");
			System.out.println("9.View Customers");
			System.out.println("10.Exit");
			
			System.out.println("Choose the correct options..");
			int choice=sc.nextInt();
			
			switch(choice) {
			
			case 1:
				System.out.println("Enter product Id : ");
				int prodId=sc.nextInt();
				System.out.println("Enter product Name : ");
				String prodName=sc.next();
				System.out.println("Enter Category :");
				String category=sc.next();
				System.out.println("Enter Product price : ");
				double prodPrice=sc.nextDouble();
				System.out.println("Enter Stock :");
				int proStock=sc.nextInt();
				Inventory product=new Inventory(prodId,prodName,category,prodPrice,proStock);
				inventory.addProductToDB(product);
				break;
			case 2:
				inventory.viewAllProduct();
				break;
			case 3:
				System.out.println("Enter Customer Id :");
				int customerId=sc.nextInt();
				System.out.println("Enter Customer Name: ");
				String customerName=sc.next();
				System.out.println("Enter your mobile number: ");
				String mobileNumber=sc.next();
				Customer customer=new Customer(customerId,customerName,mobileNumber);
				inventory.registerCustomer(customer);
				break;
				
			case 4:
				System.out.println("Enter Order ID: ");
				int orderId=sc.nextInt();
				System.out.println("Enter Customer ID: ");
				customerId=sc.nextInt();
				System.out.println("Enter Product ID: ");
				prodId=sc.nextInt();
				System.out.println("Enter Quantity : ");
				int quantity=sc.nextInt();
				inventory.placeOrder(orderId, customerId, prodId, quantity);
				break;
			case 5:
				inventory.viewOrders();
				break;
				
			case 6:
				inventory.viewLowStock();
				break;
			case 7:
				System.out.println("Enter Order Id :");
				orderId=sc.nextInt();
				inventory.FullPaymentDetails(orderId);
			break;
				
			case 8:
				
				System.out.println("Enter order ID :");
				orderId=sc.nextInt();
				
				System.out.println("Select Cancellation Reason");
				System.out.println("1.Ordered by mistake");
				System.out.println("2.Found cheaper elsewhere");
				System.out.println("3.Delevery Delay");
				System.out.println("4.Product not reqired");
				System.out.println("5.Others");
				
				int option=sc.nextInt();
				CancelReason reasons = null;
				switch(option) {
				
				case 1:
					reasons=CancelReason.Ordered_By_Mistake;
					break;
				case 2:
					reasons=CancelReason.Found_Cheaper_Elsewhere;
					break;
				case 3:
					reasons=CancelReason.Delivery_Delay;
					break;
				case 4:
					reasons=CancelReason.Product_Not_Required;
					break;
				case 5:
					reasons=CancelReason.Others;
					break;
				default:
					System.out.println("Inavalid Option...!!!");
				}
				if(reasons != null) {
					inventory.orderCancel(orderId, reasons);
				}
				
				break;
				
			case 9:
				
				inventory.viewCustomers();
				break;
			case 10:
				System.out.println("Thank You Order Again....!!!");
				System.exit(0);
				sc.close();
				break;
			default:
				System.out.println("choose correct option....");
			
			}
		}

	}

}
