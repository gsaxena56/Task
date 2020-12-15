package com.capgemini.xyz.service;

import java.util.List;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.bean.Transaction;
import com.capgemini.xyz.dao.CustomerDAO;
import com.capgemini.xyz.dao.ICustomerDAO;
import com.capgemini.xyz.exception.CustomerExists;
import com.capgemini.xyz.exception.CustomerNotFoundException;
import com.capgemini.xyz.exception.IllegalFormatException;
import com.capgemini.xyz.exception.InsufficientBalanceException;

public class CustomerService implements CustomerServiceInterface {

	//object instantiation 
	ICustomerDAO customerDAO = new CustomerDAO();

	//withdraw amount
	//minimum balance should be 100
	@Override
	public String withDraw(String mobileNumber, double amount) throws InsufficientBalanceException{
		try {
			return customerDAO.withDraw(mobileNumber, amount);
		} catch (InsufficientBalanceException e) {
			throw new InsufficientBalanceException(e.getMessage());
		}
	}

	//deposit amount in wallet
	@Override
	public String deposit(String mobileNumber, double amount) throws CustomerNotFoundException{
		try {
			return customerDAO.deposit(mobileNumber, amount);
		} catch (Exception e) {
			throw new CustomerNotFoundException(e.getMessage());
		}
	}

	//store withdraw result and deposit result in array
	@Override
	public String[] fundTransfer(String fromCust, String toCust, double amount) throws InsufficientBalanceException, CustomerNotFoundException {
		
		String[] result = new String[2];
		try
		{
			result[0] = customerDAO.withDraw(fromCust, amount);
			result[1] = customerDAO.deposit(toCust, amount);
			return result;
		}catch(InsufficientBalanceException e)
		{
			throw new InsufficientBalanceException(e.getMessage());
		}
		catch(CustomerNotFoundException e)
		{
			//if wrong mobile number
			throw new CustomerNotFoundException(e.getMessage());
		}
	
		
	}

	//returns records in list
	@Override
	public List<Transaction> printTransaction(Customer customer) {
		return customerDAO.printTransaction(customer);
	}

	//create user
	@Override
	public Customer createCustomer(Customer customer) throws CustomerExists{
		try {
			return customerDAO.createCustomer(customer);
		} catch (CustomerExists e) {
			throw new CustomerExists(e.getMessage());
		}
	}

	//login method
	@Override
	public Customer checkUser(String username, String password) throws CustomerNotFoundException{
		try {
			return customerDAO.checkUser(username, password);
		} catch (CustomerNotFoundException e) {
			throw new CustomerNotFoundException(e.getMessage());
		}
	}
	
	@Override
	public double checkBalance(Customer customer) {
		return customerDAO.checkBalance(customer);
	}

	
	//-------validation codes below------------//
	@Override
	public boolean validateName(String name) throws IllegalFormatException{
		if(name.matches(userNamePattern))
			return true;
		else
			throw new IllegalFormatException("name should be between 2 - 9 letters with 1st letter capital");
	}

	@Override
	public boolean validateMobNo(String mobileno)  throws IllegalFormatException{
		if(mobileno.matches(usermobilePattern))
			return true;
		else
			throw new IllegalFormatException("Enter Valid 10 digit mobile number.");
	}

	@Override
	public boolean validateEmail(String email)  throws IllegalFormatException{
		if(email.matches(useremailPattern))
			return true;
		else
			throw new IllegalFormatException("Enter Valid Email");
	}

	@Override
	public boolean validatePassword(String password)  throws IllegalFormatException{
		if(password.matches(userpasswordPattern))
			return true;
		else
			throw new IllegalFormatException("Enter atleast 6-12 character");
	}

	@Override
	public boolean validateHomeChoice(String userHChoice)  throws IllegalFormatException{
		if(userHChoice.matches(userHomeChoice))
			return true;
		else
			throw new IllegalFormatException("Please choose 1 or 2 or 3");
	}

	@Override
	public boolean validateAmount(String useramt)  throws IllegalFormatException{
		if(useramt.matches(userAmount))
			return true;
		else
			throw new IllegalFormatException("Enter in numbers and should be more than Rs.10");
	}

	@Override
	public boolean validateMenuChoice(String userMChoice)
			throws IllegalFormatException {
		if(userMChoice.matches(userMenuChoice))
			return true;
		else
			throw new IllegalFormatException("Please choose between 1 - 6");
	}

	@Override
	public boolean isValidUser(String mobileNumber)
			throws CustomerNotFoundException {
		try
		{
			return customerDAO.isValidUser(mobileNumber);			
		}catch(CustomerNotFoundException e)
		{
			throw new CustomerNotFoundException(e.getMessage());
		}

	}
}
