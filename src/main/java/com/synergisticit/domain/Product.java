package com.synergisticit.domain;

public class Product {

	private int prod_id;
	private int cat_id;
	private String prod_name;
	private int price;
	private int stock;
	private String vendor;
	
	public Product() {
		
	}

	public Product(int prod_id, int cat_id, String prod_name, int price, int stock, String vendor) {
		super();
		this.prod_id = prod_id;
		this.cat_id = cat_id;
		this.prod_name = prod_name;
		this.price = price;
		this.stock = stock;
		this.vendor = vendor;
	}

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Product [prod_id=" + prod_id + ", cat_id=" + cat_id + ", prod_name=" + prod_name + ", price=" + price
				+ ", stock=" + stock + ", vendor=" + vendor + "]";
	}
	
	
}
