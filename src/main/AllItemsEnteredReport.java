package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Prints out all the products that are in stores and in inventory
 * @author Jacob Doney - NetID: JD1568
 * @since 2023/08/03
 */
public class AllItemsEnteredReport implements Report {

    /**
     * Default constructor
     */
    public AllItemsEnteredReport() {
    }

    /**
     * Prints out reports and calls collItemsEntered
     */
    public void printReport() {
    	System.out.println("\nAll Products Entered Report:");
    	System.out.println("Products appear in the format: name(id): amount");
		collectItemsEntered();
    }

    /**
     * Collects all the items entered and prints them out.
     */
    private void collectItemsEntered() {
    	try {
    		  System.out.println("Current Inventory Not In Stores");
    		  System.out.println("**********************************");
		      File productFile = new File("res/Products.txt");
		      Scanner myReader = new Scanner(productFile);
		      while (myReader.hasNext()) {
		        int productID = myReader.nextInt();
		        String productName = "";
		        while (!myReader.hasNextInt()) {
		        	productName = productName + myReader.next() + " ";
		        }
		        int quantity = myReader.nextInt(); 
		        System.out.println(productName.trim() + "(" +  productID + "): " + quantity);
		        

		      }
		      myReader.close();
		      System.out.println();
		} 
		catch (IOException e) {
		      e.printStackTrace();
		}
    	try {
            System.out.println("All Products Sent To Stores");
            System.out.println("**********************************");
            File file = new File("res/Outgoing_Transactions.txt");
            Scanner scanner = new Scanner(file);
            
            Map<Integer, Map<Integer, Product>> storeInventory = new HashMap<Integer, Map<Integer, Product>>();
            
            while (scanner.hasNextLine()) {
            	
                String line = scanner.nextLine();
      
                if (line.startsWith("Outgoing")) {
                    // Extract store information
                	line = scanner.nextLine();
                    String[] storeInfo = line.split(":");
                    int storeID = Integer.parseInt(storeInfo[0].trim());
                    String storeName = storeInfo[1].trim();
                    String storeAddress = storeInfo[2].trim();

                    // Initialize inventory for the store if not already present
                    storeInventory.putIfAbsent(storeID, new HashMap<>());

                    while (scanner.hasNextLine()) {
                        String itemLine = scanner.nextLine();
                        if (itemLine.isBlank()) {
                            break; // Exit the loop at end of transaction.
                        }

                        // Extract item information
                        String[] itemParts = itemLine.split(":");
                        int itemID = Integer.parseInt(itemParts[0].trim());
                        String itemName = itemParts[1].trim();
                        int itemAmount = Integer.parseInt(itemParts[2].trim());

                        // Update the inventory for the store
                        Map<Integer, Product> storeItems = storeInventory.get(storeID);
                        Product product = new Product();
                        if (storeItems.containsKey(itemID)) {
                        	itemAmount += storeItems.get(itemID).getNumberOfItems();
                        }
                        product.setId(itemID);
                        product.setName(itemName);
                        product.setNumberOfItems(itemAmount);
                        storeItems.put(itemID, product);
                    }
                }
            }

            // Output the inventory for each store
            for (int storeID : storeInventory.keySet()) {
                System.out.println("Store ID: " + storeID);
                Map<Integer, Product> storeItems = storeInventory.get(storeID);
                for (Integer itemID : storeItems.keySet()) {
                    Product item = storeItems.get(itemID);
                    System.out.println(item.getName() + "(" + item.getID() + "): " + item.getNumberOfItems());
                }
                System.out.println();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

}