package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
/**
 * Report that shows most popular items.
 * @author Jacob Doney NetID: JD1568
 * @since 2023-08-03
 */
public class HighVolumeProductsReport implements Report{
	/**
	 * Default Constructor
	 */
	public void HighVolumProductsReport() {
		
	}
	
	/**
	 * Prints out all items in descending out based on volume
	 */
	@Override
	public void printReport() {
		System.out.println("\nMost incoming and outgoing items:");
    	System.out.println("Products appear in the format: name(id): amount");
		collectItemsEntered();
		
	}
	
	/**
	 * Sorts items and prints them out based on quantity amount.
	 */
	private void collectItemsEntered() {
		List<Product> topProducts = new ArrayList<Product>();
    	// Collect all items from inventory
		try {
		      File productFile = new File("res/Products.txt");
		      Scanner myReader = new Scanner(productFile);
		      while (myReader.hasNext()) {
		        int productID = myReader.nextInt();
		        String productName = "";
		        while (!myReader.hasNextInt()) {
		        	productName = productName + myReader.next() + " ";
		        }
		        int quantity = myReader.nextInt(); 
		        Product tempProduct = new Product();
		        tempProduct.setId(productID);
		        tempProduct.setName(productName.trim());
		        tempProduct.setNumberOfItems(quantity);
		        topProducts.add(tempProduct);

		      }
		      myReader.close();
		} 
		catch (IOException e) {
		      e.printStackTrace();
		}
    	try {       
    		File file = new File("res/Outgoing_Transactions.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
               
                // Find start of new transaction
                if (line.startsWith("Outgoing")) {
                    // Extract store information
                	line = scanner.nextLine();
                    String[] storeInfo = line.split(":");
                    while (scanner.hasNextLine()) {
                        String itemLine = scanner.nextLine();
                        if (itemLine.isBlank()) {
                            break; // Exit the loop if we encounter another transaction or the end of the file
                        }

                        // Extract item information
                        String[] itemParts = itemLine.split(":");
                        int itemID = Integer.parseInt(itemParts[0].trim());
                        int itemAmount = Integer.parseInt(itemParts[2].trim());
                        for(int i = 0; i < topProducts.size(); i++) {
                        	if(topProducts.get(i).getID().equals(itemID)) {
                        		itemAmount += topProducts.get(i).getNumberOfItems();
                        		topProducts.get(i).setNumberOfItems(itemAmount);
                        	}
                        }
                  
                    }
                }
            }
            scanner.close();
        } 
    	catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    	Collections.sort(topProducts);
    	for(int i = 0; i < topProducts.size(); i++) {
    		System.out.print(i+1 + ". " + topProducts.get(i).getName());
    		System.out.print("(" + topProducts.get(i).getID() + "): ");
    		System.out.println(topProducts.get(i).getNumberOfItems());
    	}
	}
    	
}