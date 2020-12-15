package com.capgemini.xyz.dao;

import java.util.List;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.bean.Transaction;
import com.capgemini.xyz.exception.CustomerExists;
import com.capgemini.xyz.exception.CustomerNotFoundException;
import com.capgemini.xyz.exception.InsufficientBalanceException;

public interface ICustomerDAO {

	Customer createCustomer(Customer customer) throws CustomerExists;

	String withDraw(String mobileNumber, double amount)	throws InsufficientBalanceException;

	String deposit(String mobileNumber, double amount) throws CustomerNotFoundException;

	Customer checkUser(String username, String password) throws CustomerNotFoundException;

	List<Transaction> printTransaction(Customer customer);

	boolean isValidUser(String mobileNumber) throws CustomerNotFoundException;
	
	double checkBalance(Customer customer);

	long ids = 10002;
}
