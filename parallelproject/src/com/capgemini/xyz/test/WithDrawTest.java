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

public class WithDrawTest {
	CustomerServiceInterface service = null;

	@Before
	public void setUp() throws Exception {
		service = new CustomerService();
	}

	// right inputs
	@Test
	public void checkWithDraw() {
		try {
			Customer customer = new Customer("Tushar", "t@g.c", "8286703935",
					"password", 5000);
			service.createCustomer(customer);

			String result = service.withDraw("8286703935", 2000);
			assertNotNull(result);
		} catch (CustomerExists e) {
			System.out.println(e.getMessage());
		} catch (InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		}
	}

	// wrong inputs
	// should print insufficient balance in console
	@Test
	public void checkWithDraw2() {
		try {
			Customer customer = new Customer("Tushar", "t@g.c", "8286703935",
					"password", 5000);
			service.createCustomer(customer);
			service.checkUser(customer.getMobileNumber(),
					customer.getPassword());

			String result = service.withDraw("8286703935", 9000);
			assertNotNull(result);
		} catch (CustomerExists e) {
			System.out.println(e.getMessage());
		} catch (InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		} catch (CustomerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	public void checkWithDraw3() {
		try {
			Customer customer = new Customer("Gunjan", "g@g.c", "9315851873",
					"Gunjan", 5000);
			service.createCustomer(customer);
			service.checkUser(customer.getMobileNumber(),
					customer.getPassword());

			String result = service.withDraw("9315851873", 5000);
			assertNotNull(result);
		} catch (CustomerExists e) {
			System.out.println(e.getMessage());
		} catch (InsufficientBalanceException e) {
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
