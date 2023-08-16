package main;

import java.util.Scanner;

/**
 * The Inventory program Implements a toy inventory system.
 * The user may add products and/or stores. 
 * They will also be able to increase the number of products, 
 * send products to a store, and generate reports. 
 * Each action is logged to a .txt file
 * 
 * @author Jacob Doney - NetID: JD1568
 * @since 2023-08-03
 */
public class Main {
	/**
	 * Main method for inventory projects 
	 * @param args
	 */
	public static void main(String args[]) {
		
		stockManager = new StockManager();
		transactionsManager = new TransactionsManager();
		reportsManager = new ReportsManager();
		menuManager = new MenuManager();
		Scanner scnr = new Scanner(System.in);
		char usrInput = 'x';
		boolean quit = false;
		do
		{
			// Main program menu
			System.out.println("Inventory Management System Menu");
			System.out.println("**********************************");
			System.out.println("p: Add Product");
			System.out.println("s: Add Store");
			System.out.println("i: Perform Incoming Transaction");
			System.out.println("o: Perform Outgoing Transaction");
			System.out.println("r: Generate Reports");
			System.out.println("x: Exit Program");
			System.out.println();
			scnr.reset();
			System.out.print("Input an action: ");
		    
			String temp = scnr.next();
			usrInput = temp.charAt(0);
			boolean valid = false;
			
			while(!valid) {
				switch(usrInput) {
				case 'P':
				case 'p': menuManager.productMenu(stockManager, transactionsManager);
						stockManager.updateProductLog();
						valid = true;
						break;
				case 'S':
				case 's': menuManager.storeMenu(stockManager);
						stockManager.updateStoreLog();
						valid = true;
						break;
				case 'I':
				case 'i': menuManager.incomingTransMenu(stockManager, transactionsManager);
						stockManager.updateProductLog();
						valid = true;
						break;
				case 'O':
				case 'o': menuManager.outgoingTransMenu(stockManager, transactionsManager);
				        stockManager.updateProductLog();
						valid = true;
						break;
				case 'R':
				case 'r': menuManager.reportsMenu(reportsManager, stockManager);
						valid = true;
						break;
				case 'X':
				case 'x':
						stockManager.updateProductLog();
						stockManager.updateStoreLog();
					    quit = true;
				        valid = true;
						break;
				default: 
					System.out.println();
					System.out.println("Invalid Input.");
					System.out.print("Input an action: ");
					temp = scnr.next();
					usrInput = temp.charAt(0);
					break;
				}
			    System.out.println();
			}
			
			scnr.reset();
			
		}while(!quit);
		scnr.close();
	}
	
	/**
	 * Manages Stores and Products
	 */
	private static StockManager stockManager;
	/**
	 * Manages Transactions
	 */
	private static TransactionsManager transactionsManager;
	/**
	 * Manages reports
	 */
	private static ReportsManager reportsManager;
	/**
	 * Manages menus
	 */
	private static MenuManager menuManager;

}
