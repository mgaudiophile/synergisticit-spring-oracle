package com.synergisticit.domain;

public class Payment {

	private int payment_id;
	private int order_id;
	private String status;
	
	public Payment() {
		
	}

	public Payment(int payment_id, int order_id, String status) {
		super();
		this.payment_id = payment_id;
		this.order_id = order_id;
		this.status = status;
	}

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Payment [payment_id=" + payment_id + ", order_id=" + order_id + ", status=" + status + "]";
	}
	
	
}
