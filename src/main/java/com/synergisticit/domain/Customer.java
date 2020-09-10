package com.synergisticit.domain;

public class Customer {

	private int customer_id;
	private String first_name;
	private String last_name;
	private Address addr;
	private long mobile;
	
	public Customer() {
		
	}

	public Customer(int customer_id, String first_name, String last_name, Address addr, long mobile) {
		super();
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.addr = addr;
		this.mobile = mobile;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", addr=" + addr + ", mobile=" + mobile + "]";
	}
	
	
}
