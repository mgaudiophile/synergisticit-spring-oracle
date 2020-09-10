package com.synergisticit.domain;

import java.sql.Date;

public class Order_Table {

	private int order_id;
	private int customer_id;
	private int prod_id;
	private Date order_date;
	private int quantity;
	private int total_price;
	private String order_status;
	
	public Order_Table() {
		
	}

	public Order_Table(int order_id, int customer_id, int prod_id, Date order_date, int quantity, int total_price,
			String order_status) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.prod_id = prod_id;
		this.order_date = order_date;
		this.quantity = quantity;
		this.total_price = total_price;
		this.order_status = order_status;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	@Override
	public String toString() {
		return "Order_Table [order_id=" + order_id + ", customer_id=" + customer_id + ", prod_id=" + prod_id
				+ ", order_date=" + order_date + ", quantity=" + quantity + ", total_price=" + total_price
				+ ", order_status=" + order_status + "]";
	}
	
	
}
