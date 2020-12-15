package com.capgemini.xyz.test;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.exception.CustomerExists;
import com.capgemini.xyz.exception.CustomerNotFoundException;
import com.capgemini.xyz.exception.InsufficientBalanceException;
import com.capgemini.xyz.service.CustomerService;
import com.capgemini.xyz.service.CustomerServiceInterface;

public class DepositTest {
	CustomerServiceInterface service = null;

	@Before
	public void setUp() throws Exception {
		service = new CustomerService();
	}

	// right inputs
	@Test
	public void checkDeposit() {
		try {
			Customer customer = new Customer("Tushar", "t@g.c", "8286703935",
					"password", 5000);
			service.createCustomer(customer);

			String result = service.deposit(customer.getMobileNumber(), 2000);
			assertNotNull(result);
		} catch (CustomerExists e) {
			System.out.println(e.getMessage());
		} catch (CustomerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	// wrong inputs
	// should print no user as user don't exists in console
	@Test
	public void checkDeposit2() {
		try {
			Customer customer = new Customer();
			service.createCustomer(customer);
			service.checkUser(customer.getMobileNumber(),
					customer.getPassword());
			String result = service.deposit(customer.getMobileNumber(), 9000);
			assertNotNull(result);
		} catch (CustomerExists e) {
			System.out.println(e.getMessage());
		} catch (CustomerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	@After
	public void destroy() throws Exception {
		service = null;
	}
}
