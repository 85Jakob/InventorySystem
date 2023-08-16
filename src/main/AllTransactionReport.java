package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
/**
 * Report to print out all incoming/outgoing transactions
 * @author Jacob Doney 
 * @since 2023-08-03
 */
public class AllTransactionReport implements Report {
	/**
     * Default constructor
     */
    public AllTransactionReport() {
    }

    /**
     * Prints out menu for transaction reports 
     * Then calls to collect the transactions
     */
    @Override
    public void printReport() {
    	Scanner scnr = new Scanner(System.in);
    	System.out.println("Select option to view transactions:");
		System.out.println("1: All Transactions");
		System.out.println("2: Transactions for Past 30 Days");
		System.out.print("Select and option: ");
		int input = -1;
		if(scnr.hasNextInt()){
			input = scnr.nextInt();
		}
		boolean past30Days;
		if(input == 1) {
			past30Days = false;
		}
		else if(input == 2) {
			past30Days = true;
		}
		else {
			System.out.println("Invalid input");
			return;
		}
    	System.out.println("\nList of all transactions:");
		collectOutgoingTransactions(past30Days);
		collectIncomingTransactions(past30Days);
    }

    /**
     * Collects and prints outgoing transactions
     * @param past30Days boolean that if true prints only the transactions entered within the past 30 days.
     */
    public void collectOutgoingTransactions(boolean past30Days) {
    	final int THRESHOLD = -30;
    	try {
            System.out.println("Outgoing Transactions");
            System.out.println("**********************************");
            File file = new File("res/Outgoing_Transactions.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
            	
                String line = scanner.nextLine();
                
                // Finds start of new transaction
                if (line.startsWith("Outgoing")) {
                	String[] transInfo = line.split(" ");
                    int transID = Integer.parseInt(transInfo[1].trim());
                    String transDate = transInfo[2].trim();
                    String transTime = transInfo[3].trim();
                    
                    // Flags transactions that are within 30 days
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    long diffInDays = 0;
                    try {
                        Date parsedDate = (Date) sdf.parse(transDate);
                        Date currentDate = new Date();
                        long diffInMsecs = parsedDate.getTime() - currentDate.getTime();
                        diffInDays = diffInMsecs / (24 * 60 * 60 * 1000);
                    }
                	catch (ParseException e) {
                		System.err.println(e.getMessage());
                	}
                    
                    // Output transactions based on user specifications
                    if((past30Days && diffInDays >= THRESHOLD ) || (!past30Days)) {
	                    System.out.println("Outgoing Transaction: " + transID + " at " + transDate + " " + transTime);
	                	line = scanner.nextLine();
	                    String[] storeInfo = line.split(":");
	                    int storeID = Integer.parseInt(storeInfo[0].trim());
	                    String storeName = storeInfo[1].trim();
	                    String storeAddress = storeInfo[2].trim();
	
	                    // Initialize inventory for the store if not already present
	                    System.out.println("Location: " + storeName + "(" + storeID + "): " + storeAddress);
	
	                    while (scanner.hasNextLine()) {
	                        String itemLine = scanner.nextLine();
	                        if (itemLine.isBlank()) {
	                            break; // Exit the loop if we encounter another transaction or the end of the file
	                        }
	
	                        // Extract item information
	                        String[] itemParts = itemLine.split(":");
	                        int itemID = Integer.parseInt(itemParts[0].trim());
	                        String itemName = itemParts[1].trim();
	                        int itemAmount = Integer.parseInt(itemParts[2].trim());
	                        System.out.println(itemName.trim() + "(" +  itemID + "): " + itemAmount);
	                    }
                    
                    	System.out.println();
                    }
                }
                
            }
            scanner.close();
    	}catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
    
    /**
     * Collects and prints outgoing transactions
     * @param past30Days boolean that if true prints only the transactions entered within the past 30 days.
     */
    public void collectIncomingTransactions(boolean past30Days) {
    	final int THRESHOLD = -30;
    	try {
            System.out.println("Incoming Transactions");
            System.out.println("**********************************");
            File file = new File("res/Incoming_Transactions.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
            	
                String line = scanner.nextLine();
                
                // Finds start of new transaction
                if (line.startsWith("Incoming")) {
                	String[] transInfo = line.split(" ");
                    int transID = Integer.parseInt(transInfo[1].trim());
                    String transDate = transInfo[2].trim();
                    String transTime = transInfo[3].trim();
                    
                    // Flags transaction that are within 30 days.
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    long diffInDays = 0;
                    try {
                        Date parsedDate = (Date) sdf.parse(transDate);
                        Date currentDate = new Date();
                        long diffInMsecs = parsedDate.getTime() - currentDate.getTime();
                        diffInDays = diffInMsecs / (24 * 60 * 60 * 1000);
                    }
                	catch (ParseException e) {
                		System.err.println(e.getMessage());
                	}	
                    
                    // Prints only records based on users specifications
                    if((past30Days && diffInDays >= THRESHOLD ) || (!past30Days)) {
	                    System.out.println("Incoming Transaction: " + transID + " at " + transDate + " " + transTime);
	                	
	                    while (scanner.hasNextLine()) {
	                        String itemLine = scanner.nextLine();
	                        if (itemLine.isBlank()) {
	                            break; // Exit the loop if we encounter another transaction or the end of the file
	                        }
	
	                        // Extract item information
	                        String[] itemParts = itemLine.split(":");
	                        int itemID = Integer.parseInt(itemParts[0].trim());
	                        String itemName = itemParts[1].trim();
	                        int itemAmount = Integer.parseInt(itemParts[2].trim());
	                        System.out.println(itemName.trim() + "(" +  itemID + "): " + itemAmount);
	                    }
                    
	                    System.out.println();
                    }
                }
            }
            scanner.close();
    	}catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}	
