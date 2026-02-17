package com.ait.inventoryManagement;

public class Inventory {

	private int productId;
	private String productName;
	private String category;
	private double price;
	private int stock;
	
	public Inventory(int prodId,String prodName,String prodCate,double prodPrice, int prodStock) {
		this.productId=prodId;
		this.productName=prodName;
		this.category=prodCate;
		this.price=prodPrice;
		this.stock=prodStock;
	}
	public int getProdId() {
		return productId;
	}
	public String getProname() {
		return productName;
	}
	public String getCategory() {
		return category;
	}
	public double getPrice() {
		return price;
	}
	public int getStock() {
		return stock;
	}
	public void reduceStock(int qty) {
		stock-=qty;
	}
	
	public void displayAllProducts() {
		System.out.println("Product Id       : "+productId);
		System.out.println("Product Name     : "+productName);
		System.out.println("Product Category : "+category);
		System.out.println("Product Price    : "+price);
		System.out.println("Product Stock    : "+stock);
	}
	
}
