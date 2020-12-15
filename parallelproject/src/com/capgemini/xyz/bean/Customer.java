package com.capgemini.xyz.bean;

public class Customer {

	private long customerId;
	private String name;
	private String email;
	private String mobileNumber;
	private String password;
	private double balance;

	// default constructor
	public Customer() {
		super();
	}

	// getters setters
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// parameterized constructor
	public Customer(String name, String email,
			String mobileNumber, String password, double balance) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.balance = balance;
	}

	// to string override
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name
				+ ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", password=" + password + ", balance=Rs. " + balance + "]";
	}
}
