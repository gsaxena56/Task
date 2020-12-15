package com.capgemini.xyz.ui;

import java.util.List;
import java.util.Scanner;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.bean.Transaction;
import com.capgemini.xyz.exception.CustomerExists;
import com.capgemini.xyz.exception.CustomerNotFoundException;
import com.capgemini.xyz.exception.IllegalFormatException;
import com.capgemini.xyz.exception.InsufficientBalanceException;
import com.capgemini.xyz.service.CustomerService;
import com.capgemini.xyz.service.CustomerServiceInterface;

public class CustomerUI 
{

	public static void main(String[] args) {
		
		// object instantiation
		Customer newCustomer = new Customer();
		Customer loggedCustomer = new Customer();
		CustomerServiceInterface service = new CustomerService();

		
		// mock data of two account
		Customer customer = new Customer("Tushar", "t@g.c", "8286703935",
				"password", 45000);
		Customer customer2 = new Customer("Mayur", "m@g.c", "9892622745",
				"password", 46000);

		//create accounts for mock data
		Customer customer3 = new Customer("Gunjan","g@g.c","9315851873",
				"Gunjan",50000);
		try {
			service.createCustomer(customer);
			service.createCustomer(customer2);
			service.createCustomer(customer3);
		} catch (CustomerExists e1) {
			e1.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);
		

		// Asks for Inputs
		for (;;) {
			System.out.println("Welcome to XYZ Wallet\n1) Register\n2) Login \n3) Exit");
			String homeChoice = "";

			while (true) {
				homeChoice = scan.next();
				try {
					//validate if input between 1-3
					if (service.validateHomeChoice(homeChoice))
						break;
				} catch (IllegalFormatException e) {
					System.out.println(e.getMessage());
				}
			

			if (homeChoice.equals("1"))// register code
			{
				while (true)// take name
				{
					System.out.println("Enter name with first letter in Capital");
					String temp = scan.next();
					try {
						//validate name
						if (service.validateName(temp)) {
							newCustomer.setName(temp);
							break;
						}
					} catch (IllegalFormatException e) {
						System.out.println(e.getMessage());
					}
				}

				while (true)// take email
				{
					System.out.println("Enter email Id");
					String temp = scan.next();
					try {
						//validate email
						if (service.validateEmail(temp)) {
							newCustomer.setEmail(temp);
							break;
						}
					} catch (IllegalFormatException e) {
						System.out.println(e.getMessage());
					}
				}

				while (true)// take mobile
				{
					System.out.println("Enter Mobile No");
					String temp = scan.next();
					try {
						//validate mobile number
						if (service.validateMobNo(temp)) {
							newCustomer.setMobileNumber(temp);
							break;
						}
					} catch (IllegalFormatException e) {
						System.out.println(e.getMessage());
					}
				}
				while (true)// set password
				{
					System.out.println("Set Password");
					String temp = scan.next();
					try {
						//validate password
						if (service.validatePassword(temp)) {
							newCustomer.setPassword(temp);
							break;
						}
					} catch (IllegalFormatException e) {
						System.out.println(e.getMessage());
					}
				

				// set balance
				newCustomer.setBalance(5000);

				// try to create new user and also checks if user exists
				try {
					Customer cust = service.createCustomer(newCustomer);
					System.out.println("New Customer Created");
					System.out.println(cust);
				} catch (CustomerExists e) {
					System.out.println(e.getMessage());
				}

			}// end of register
		}
			
			else if (homeChoice.equals("2"))// login code
			{
				while (true) {
					System.out.println("Enter Mobile no");
					String temp = scan.next();
					System.out.println("Enter Password");
					String pass = scan.next();
					try {
						//get customer from login
						loggedCustomer = service.checkUser(temp, pass);
						break;
					} catch (CustomerNotFoundException e) {
						System.out.println(e.getMessage());
					}
				}

				while (true)// start submenu
				{
					System.out.println("Welcome "+loggedCustomer.getName());
					System.out.println("1) Show Balance");
					System.out.println("2) Deposit");
					System.out.println("3) Withdraw");
					System.out.println("4) Fund Transfer");
					System.out.println("5) Print Transactions");
					System.out.println("6) Logout");
					String choice = "";
					boolean isExited = false;

					while(true)
					{
						choice = scan.next();
					try {
						//validate if input between 1-6
						if (service.validateMenuChoice(choice))
							break;
					} catch (IllegalFormatException e) {
						System.out.println(e.getMessage());
					}
					
					switch (choice) {
					case "1":
						System.out.println("Balance is Rs."+ service.checkBalance(loggedCustomer));
						break;

					case "2":
						while (true) {
							System.out.println("Enter amount to deposit");
							choice = scan.next();
							try {
								//validate if amount input is valid
								if (service.validateAmount(choice))
									break;
							} catch (IllegalFormatException e) {
								System.out.println(e.getMessage());
							}
						}

						String depositResult="";
						try {
							depositResult = service.deposit(
							loggedCustomer.getMobileNumber(),
							Double.parseDouble(choice));
						} catch (NumberFormatException e1) {
								System.out.println(e1.getMessage());
						} catch (CustomerNotFoundException e1) {
							System.out.println(e1.getMessage());
						}
						//print result from server
						System.out.println(depositResult);
						break;

					case "3":
						while (true) {
							System.out.println("Enter amount to withdraw");
							choice = scan.next();
							try {
								//validate if amount input is valid
								if (service.validateAmount(choice))
									break;
							} catch (IllegalFormatException e) {
								System.out.println(e.getMessage());
							}
						}//end of while

						try {
							String withdrawResult = service.withDraw(
									loggedCustomer.getMobileNumber(),
									Double.parseDouble(choice));
							System.out.println(withdrawResult);
						} catch (NumberFormatException
								| InsufficientBalanceException e) {
							System.out.println(e.getMessage());
						}
						break;

					case "4":
						
						String mob="";
						String amt="";
						
						while(true)
						{
							System.out.println("Enter mobile number of other user");
							 mob = scan.next();
							System.out.println("Enter the amount");
							 amt = scan.next();
							
							try {
								if(service.validateMobNo(mob) && service.validateAmount(amt)
										&& service.isValidUser(mob))										
									break;
							} catch (IllegalFormatException e) {
								System.out.println(e.getMessage());
							}catch (CustomerNotFoundException e) {
								System.out.println(e.getMessage());
							}						
						}

						//store fund transfer results
						try {
							String[] data = service.fundTransfer(
									loggedCustomer.getMobileNumber(), mob,
									Double.parseDouble(amt));
							for (String result : data) {
								System.out.println(result);
							}
						} catch (NumberFormatException
								| InsufficientBalanceException e) {
							System.out.println(e.getMessage());
						} catch (CustomerNotFoundException e) {
							System.out.println(e.getMessage());
						}
						break;

					case "5":
						List<Transaction> list = 
							service.printTransaction(loggedCustomer);
						list.stream().forEach(System.out::println);
						break;

					case "6":
						isExited = true;
					default:
						break;
					}

					if (isExited) {
						isExited = false;
						break;
					} else
						continue;
				}
			}// end of login code
			}
			else {
				//if choice is 3 ie exit
			}
				System.out.println("Exited Successfully. Thank You. Visit Again");
				break;
			}
		}// end of eternal loop
	}
	}
		
