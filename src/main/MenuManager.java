package main;
/**
 * Manages the menus that are output into console.
 * @author Jacob Doney
 * @since 2023-08-03
 *
 */
import java.util.List;
import java.util.Scanner;

/**
 * MenuManager class controls the various sub menus for the inventory system.
 * @author Jacob Doney -NetID: JD1568
 * @since 2023-08-03
 */
public class MenuManager {
	/**
	 * Displays the sub menu for adding a new product.
	 * Ands new product to product list and records as in incoming_transaction.
	 * @param stockManager a StockManager object which stores a list of stores and products. a
	 * @param transactionsManager a TransactionManager object which added transaction to log files. 
	 */
	public void productMenu(StockManager stockManager, TransactionsManager transactionsManager) {
		Scanner scnr = new Scanner(System.in);
		IncomingTransaction tr = new IncomingTransaction();
		char usrInput = 'x';
		do {
			System.out.print("Enter product name: ");
			String name = scnr.nextLine();
			System.out.print("Enter product amount: ");
			int quantity = 0;
			
			// Validation check for user input.
			if(scnr.hasNextInt()) {
				quantity = scnr.nextInt();
			}
			else{
				System.out.println("Invlaid input: Returning to main menu.");
				return;
			}
			
			// Check if product is added to product list.
			Product newProduct = new Product(name, quantity);
			boolean added = stockManager.addProduct(newProduct);
			if(!added) {
				System.out.println("Product ID or Name Already in Use. Product Not Added To Inventory");
			}
			System.out.print("\nWould you like to add another product? (y/n): ");
			
			// Create transaction 
			tr.addProduct(newProduct, quantity);
			
			// Recurse if user wants to add another product
			String temp = scnr.next();
			temp = temp.toLowerCase();
			usrInput = temp.charAt(0);
			// Clear buffer
			scnr.nextLine();
		} while(usrInput == 'y');
		transactionsManager.addTransaction(tr);
	}
	
	/**
	 * Displays the menu and controls input for adding stores.
	 * @param stockManager a StockManager object which stores a list of stores and products. 
	 */
	public void storeMenu(StockManager stockManager) {
		Scanner scnr = new Scanner(System.in);
		System.out.print("Enter store name: ");
		String storeName = scnr.nextLine();
		System.out.print("Enter Store Address: ");
		String address = scnr.nextLine();

		Store myStore = new Store(storeName, address);
		
		// Adds store to list if not already added
		if(!stockManager.addStore(myStore)) {
			System.out.println("Store Name, ID, or Address Already in Use. This Store Has Not Been Added");
		}
		System.out.print("\nWould you like to add another store? (y/n): ");
		
		// Loop if user wants to add another store
		String temp = scnr.next();
		char usrInput = temp.charAt(0);
		if(usrInput == 'y' || usrInput == 'Y' ) {
			storeMenu(stockManager);
		}
	}
	
	/**
	 * Displays and controls commands for taking in incoming transactions; 
	 * @param stockManager a StockManager object which stores a list of stores and products. 
	 * @param transactionsManager a TransactionsManager object used to record transactions.
	 */
	public void incomingTransMenu(StockManager stockManager, TransactionsManager transactionsManager) {
		Scanner scnr = new Scanner(System.in);
		int userInput;
		boolean quit = false;
		IncomingTransaction tr = new IncomingTransaction();
		
		do
		{
			// Display products previously listed.
			System.out.println("List of available products for the transaction");
			List<Product> productList = stockManager.getProducts();
			for(int i = 0; i < productList.size(); i++) {
				System.out.println(productList.get(i).getID() + ": " + productList.get(i).getName());
			}
			
			// Get selection from user. 
			System.out.print("Select a product: ");
			if(scnr.hasNextInt()) {
				userInput = scnr.nextInt();
			}
			else {
				System.out.println("Invalid Input: returning to main menu.");
				return;	
			}
			
			// Retrieve product from productList
			int productID = -1;
			Product currentProduct = null;
			for(int i = 0; i < productList.size(); i++) {
				if(productList.get(i).getID().equals(userInput)) {
					productID = userInput;
					currentProduct = productList.get(i);
				}
			}
			if(productID == -1) {
				System.out.println("Invalid Input");
				return;
			}
			
			// Get amount to add from user.
			System.out.print("Enter product amount: ");
			if(scnr.hasNextInt()) {
				userInput = scnr.nextInt();
			}
			else {
				System.out.println("Invalid Input: returning to main menu.");
				return;	
			}
			
			// Create incoming transaction
			boolean added = tr.addProduct(currentProduct, userInput);
			if(!added) {
				System.out.println("Product already added during this transaction.");
				System.out.println("To add more " + currentProduct.getName() + ", start a new transaction.");
			}
			else {
				tr.updateProductStock(currentProduct, userInput);
			}
			
			// Loop if user wants to add another transaction
			System.out.print("\nWould you like to add another product? (y/n): ");
			String temp = scnr.next();
			char usrInput = temp.charAt(0);
			if(usrInput != 'y' && usrInput != 'Y') {
				quit = true;
				System.out.println(usrInput);
			}
		} while(!quit);
		
		transactionsManager.addTransaction(tr);
	}
	
	/**
	 * Displays and controls commands for outgoing transactions; 
	 * @param stockManager a StockManager object which stores a list of stores and products. 
	 * @param transactionsManager a TransactionsManager object used to record transactions.
	 */
	public void outgoingTransMenu(StockManager stockManager,TransactionsManager transactionsManager) {
		Scanner scnr = new Scanner(System.in);
		int userInput;
		int storeID = -1;
		Store currentStore = null;
		
		// Display list of stores
		System.out.println("List of available stores for the transaction");
		List<Store> storeList = stockManager.getStores(); 
		for(int i = 0; i < storeList.size(); i++) {
			System.out.println(storeList.get(i).getID() + ": " + storeList.get(i).getName());
		}
		
		// Get user input
		System.out.print("Select a store: ");
		if(scnr.hasNextInt()) {
			userInput = scnr.nextInt();
		}
		else {
			System.out.println("Invalid Input: returning to main menu.");
			return;	
		}
		
		// Retrieve store from list
		for(int i = 0; i < storeList.size(); i++) {
			if(storeList.get(i).getID().equals(userInput)) {
				storeID = userInput;
				currentStore = storeList.get(i);
			}
		}
		if(storeID == -1) {
			System.out.println("Invalid Input");
			return;
		}
		
		// Start of products to store loop
		boolean quit = false;
		OutgoingTransaction tr = new OutgoingTransaction(currentStore);
		do
		{
			// list available products
			System.out.println("List of available products for the transaction");
			List<Product> productList = stockManager.getProducts();
			for(int i = 0; i < productList.size(); i++) {
				System.out.print(productList.get(i).getID() + ": " + productList.get(i).getName());
				System.out.println(" | Current Amount: " + productList.get(i).getNumberOfItems());
			}
			
			// Get user input
			System.out.print("Select a product: ");
			if(scnr.hasNextInt()) {
				userInput = scnr.nextInt();
			}
			else {
				System.out.println("Invalid Input: returning to main menu.");
				return;	
			}
			
			// Get product from list
			int productID = -1;
			Product currentProduct = null;
			for(int i = 0; i < productList.size(); i++) {
				if(productList.get(i).getID().equals(userInput)) {
					productID = userInput;
					currentProduct = productList.get(i);
				}
			}
			if(productID == -1) {
				System.out.println("Invalid Input");
				return;
			}
			
			// Get Amount to send to stores
			System.out.print("Enter product amount: ");
			if(scnr.hasNextInt()) {
				userInput = scnr.nextInt();
			}
			else {
				System.out.println("Invalid Input: returning to main menu.");
				return;	
			}
			
			// Validate input
			while(userInput < 0 || userInput > currentProduct.getNumberOfItems()) {
				System.out.print("Invalid Amount: Current number of ");
				System.out.println(currentProduct.getName() + " is " + currentProduct.getNumberOfItems());
				System.out.print("Enter product amount: ");
				userInput = scnr.nextInt();
			}
			
			// Add Transaction
			boolean added = tr.addProduct(currentProduct, userInput);
			if(!added) {
				System.out.println("Product already added during this transaction.");
				System.out.println("To add more " + currentProduct.getName() + ", start a new transaction.");
			}
			else {
				tr.updateProductStock(currentProduct, userInput);
			}
			
			// loop to send more products
			System.out.print("\nWould you like to add another product? (y/n): ");
			String temp = scnr.next();
			char usrInput = temp.charAt(0);
			if(usrInput != 'y' && usrInput != 'Y') {
				quit = true;
				System.out.println(usrInput);
			}
		} while(!quit);
		transactionsManager.addTransaction(tr);
	}
	
	/**
	 * Displays Reports sub menu and controls flow of user input
	 * @param reportsManager a ReportsManager object that manages that reports
	 * @param stockManager a StockManager object that holds a list of stores and products
	 */
	public void reportsMenu(ReportsManager reportsManager, StockManager stockManager) {
		Scanner scnr = new Scanner(System.in);
		char usrInput;
		String input;
		boolean quit = false;
		do
		{
			System.out.println("\nReports Sub Menu");
			System.out.println("**********************************");
			System.out.println("a: All Products Report");
			System.out.println("v: Available Products Report");
			System.out.println("s: Store Products Report");
			System.out.println("t: All Transaction Report");
			System.out.println("h: High Volume Products Report");
			System.out.println("x: Return to Main Menu");
			System.out.println();
			scnr.reset();
			System.out.print("Input an action: ");
			input = scnr.nextLine();
			usrInput = input.charAt(0);
			if(usrInput == 'x' || usrInput == 'X') {
				quit = true;
			}
			else {
				reportsManager.generateReport(usrInput, stockManager);
			
				scnr.reset();
				System.out.print("Would you like to view another report? (y/n): ");
				
				input = scnr.nextLine();
				usrInput = input.charAt(0);
				if(usrInput != 'y' && usrInput != 'Y') {
					quit = true;
				}
			}
		}while(!quit);
	}
}
