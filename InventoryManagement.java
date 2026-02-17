package com.ait.inventoryManagement;

import java.util.*;
import java.sql.*;

public class InventoryManagement {

	private Map<Integer,Inventory> inventories =new HashMap<>();
	private Map<Integer, Customer>customers=new HashMap<>();
	private List<OrderDetails>orderdetail= new ArrayList<>();
	private CancelReason cancelReason;

	public void addProductToDB(Inventory product) {

	    String sql = "INSERT INTO products (product_id, product_name, category, price, stock) VALUES (?, ?, ?, ?, ?)";

	    try (
	        Connection conn = DataConnection.getConnection()
;	        PreparedStatement ps = conn.prepareStatement(sql);
	    ) {
	        ps.setInt(1, product.getProdId());
	        ps.setString(2, product.getProname());
	        ps.setString(3, product.getCategory());
	        ps.setDouble(4, product.getPrice());
	        ps.setInt(5, product.getStock());

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            System.out.println("✅ Product added successfully to database");
	        }

	    } catch (SQLException e) {
	        System.out.println("❌ Error while adding product");
	        e.printStackTrace();
	    }
	}

	
	
	public void addProduct(Inventory inventory) {
		
		System.out.println("product Added Successfully...!!");
	}
	
	
	
	public void viewAllProduct() throws SQLException {
		String sql="select * from products";
		
		try (
			Connection conn=DataConnection.getConnection();
			PreparedStatement ps= conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
		)
		{
		boolean found =false;
		while(rs.next()) {
			found=true;
			System.out.println("Product Id   : "+rs.getInt("Product_id"));
			System.out.println("Name         : "+rs.getString("Product_name"));
			System.out.println("Category     : "+rs.getString("Category"));
			System.out.println("Price        : "+rs.getDouble("Price"));
			System.out.println("Stock        : "+rs.getInt("Stock"));
			System.out.println("---------------------------");
		}
		if(!found) {
			System.out.println("No products availble...!!");
		}
		}
		
	}
	

    public void registerCustomer(Customer customer) {
       String sql = "insert into customer (customer_id, customer_name, mobile)values(?,?,?)";
       try {
		Connection conn=DataConnection.getConnection();
		PreparedStatement ps=conn.prepareStatement(sql);
		{
			ps.setInt(1, customer.getCustomerId());
			ps.setString(2, customer.getCustomerName());
			ps.setString(3, customer.getMobbileNumber());
			
			ps.executeUpdate();
			System.out.println("customer is registred successfully...!!");
		
		}
	} catch (SQLException e) {
		
	    System.out.println("SQL Error Message: " + e.getMessage());
	    System.out.println("SQL Error Code: " + e.getErrorCode());
	    System.out.println("SQL State: " + e.getSQLState());
	}
       
    }
    
    public void viewCustomers() {
    	
    	    String sql="select * from customer";
    	    
    	    try {
				Connection conn=DataConnection.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				boolean found=false;
				while(rs.next()) {
					found=true;
					System.out.println("Customer Id            : "+rs.getInt("customer_id"));
					System.out.println("Customer Name          : "+rs.getString("customer_name"));
					System.out.println("Customer Mobile Number : "+rs.getString("mobile"));
					System.out.println("-------------------------------------");
				}
				if(!found) {
					System.out.println("Coustomer not found...!!");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	    
    	
    }
	
	public void placeOrder(int orderId ,int customerId, int productId, int quantity ) throws SQLException {
		
		Connection conn=DataConnection.getConnection();
		conn.setAutoCommit(false);
		
		// check Product
		
		String sql="select * from products where product_id = ?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, productId);
		ResultSet rs=ps.executeQuery();
		
		if(!rs.next()) {
			System.out.println("Product not found");
			conn.rollback();
			return;
		}
		
		int stock=rs.getInt("stock");
		double price=rs.getDouble("price");
		
		if(stock<quantity) {
			System.out.println("Insufficient stock..");
			conn.rollback();
			return;
		}
		double totalAmount=price * quantity;
		// insert order
		
		 sql="insert into orders(order_id, customer_id, product_id, quantity, total_amount, payment_status)"+"values(?, ?, ?, ?, ?, 'PLACED')";
		 PreparedStatement  orderPs=conn.prepareStatement(sql);
		 orderPs.setInt(1, orderId);
		 orderPs.setInt(2, customerId);
		 orderPs.setInt(3, productId);
		 orderPs.setInt(4, quantity);
		 orderPs.setDouble(5, totalAmount);
		 orderPs.executeUpdate();
		 
		 
		 // update stock
		 
		 sql="update products set stock=stock - ? where product_id = ?";
		 PreparedStatement updatePs=conn.prepareStatement(sql);
		 updatePs.setInt(1, quantity);
		 updatePs.setInt(2, productId);
		 updatePs.executeUpdate();
		 
		 conn.commit();
		 System.out.println("Order place successfully...!!");
		
	}
	



	public void FullPaymentDetails(int orderId) {
		
		String sql ="select o.order_id, o.quantity, o.total_amount, o.payment_status, " +
		        " p.product_name,p.category,p.price " + " from orders o " + 
				" join products p on o.product_id=p.product_id " + 
				" where o.order_id = ?";
		
		try {
			Connection conn=DataConnection.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			{
				ps.setInt(1, orderId);
				ResultSet rs=ps.executeQuery();
				
				if(!rs.next()) {
					System.out.println("Order not found....!!");
					return;
				}
				if("paid".equalsIgnoreCase(rs.getString("payment_status"))) {
					System.out.println("Order is Already paid...!!");
				}
				
				System.out.println("--------Order payment details---------");
				System.out.println("Oder Id      : "+rs.getInt("order_id"));
				System.out.println("Product Name : "+rs.getString("product_name"));
				System.out.println("Category     : "+rs.getString("category"));
				System.out.println("Price        : "+rs.getDouble("price"));
				System.out.println("Quantity     : "+rs.getInt("quantity"));
				System.out.println("Total Amount : "+rs.getDouble("total_amount"));
				
				System.out.println("----------------------------------------");
			}
			
			// update payment status
			sql="update orders set payment_status='paid' where order_id = ?";
			PreparedStatement updatePs=conn.prepareStatement(sql);
			updatePs.setInt(1, orderId);
			updatePs.executeUpdate();
			
			System.out.println("Payment Completed successfully....!!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void orderCancel(int orderId, CancelReason reason) {
	
		try {
			Connection conn=DataConnection.getConnection();
			conn.setAutoCommit(false);
			String sql="select product_id , quantity, payment_status from orders where order_id = ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, orderId);
			ResultSet rs=ps.executeQuery();
			
			if(!rs.next()) {
				System.out.println("Order not found..!!");
				conn.rollback();
				return;
			}
			if("Cancelled".equalsIgnoreCase(rs.getString("payment_status"))) {
				System.out.println("Order is cancelled...!!");
				conn.rollback();
				return;
			}
			int productId=rs.getInt("product_id");
			int quantity=rs.getInt("quantity");
			
			//restore stock
			
			sql="update products set stock= stock + ? where product_id = ?";
			PreparedStatement stockPs=conn.prepareStatement(sql);
			stockPs.setInt(1, quantity);
			stockPs.setInt(2, productId);
			stockPs.executeUpdate();
			
			// update orders...
			
			sql="update orders set payment_status = 'Cancelled', cancel_reason = ? where order_id = ?";
			PreparedStatement cancelPs=conn.prepareStatement(sql);
			cancelPs.setString(1, reason.name());
			cancelPs.setInt(2, orderId);
			cancelPs.executeUpdate();
			
			conn.commit();
			
			System.out.println("Order Cancelled Successfully...!!");
			System.out.println("Stock restored for products : "+productId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void viewOrders() throws SQLException {
	
		String sql="select * from orders";
		
		try(
				Connection conn=DataConnection.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
			)
		{
			boolean found=false;
			while(rs.next()) {
				found=true;
				System.out.println("Order Id      : "+rs.getInt("order_id"));
				System.out.println("Customer Id   : "+rs.getInt("customer_id"));
				System.out.println("Product Id    : "+rs.getInt("product_id"));
				System.out.println("Quantity      : "+rs.getInt("quantity"));
				System.out.println("Total Amount  :"+rs.getDouble("total_amount"));
				System.out.println("Order Status  : "+rs.getString("payment_status"));
//				System.out.println("Cancel Reason : "+rs.getString("cancel_reason"));
				
				System.out.println("---------------------------------");
			}
			
			if(!found) {
				System.out.println("No orders availble....!!!");
			}
		}
	}
	
	
	public void viewLowStock() {
		boolean stock=false;
		
		for(Inventory in:inventories.values()) {
			if(in.getStock()<=3) {
				in.displayAllProducts();
				System.out.println("--------------");
				stock=true;
			}
		}
		if(!stock) {
			System.out.println("No Low stock Products...!!");
		}
	}
	
}
