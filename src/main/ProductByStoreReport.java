package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Report that shows products by store
 * @author Jacob Doney NetID: JD1568
 * @since 2023-08-03
 */
public class ProductByStoreReport implements Report {

    /**
     * Default constructor
     * @param store Store object
     */
    public ProductByStoreReport(Store store) {
    	this.store = store;
    }

    /**
     * Prints out report
     */
    @Override 
    public void printReport() {
        System.out.println("\nProducts By Store Report:");
        System.out.println("Products appear in the format: name(id): amount");
        collectProductsSentToStore();
    }

    /**
     * Collects the products sent to store
     */
    private void collectProductsSentToStore() {
    	try {
            File file = new File("res/Outgoing_Transactions.txt");
            Scanner scanner = new Scanner(file);
            
            Map<Integer, Map<Integer, Product>> storeInventory = new HashMap<Integer, Map<Integer, Product>>();
            
            while (scanner.hasNextLine()) {
            	
                String line = scanner.nextLine();
                // Finds new transaction
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
                            break; // Exit the loop if we encounter another transaction or the end of the file
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
            	if(store == null || store.getID().equals(storeID)) {
	                System.out.println("Store ID: " + storeID);
	                Map<Integer, Product> storeItems = storeInventory.get(storeID);
	                for (Integer itemID : storeItems.keySet()) {
	                    Product item = storeItems.get(itemID);
	                    System.out.println(item.getName() + "(" + item.getID() + "): " + item.getNumberOfItems());
	                }
	                System.out.println();
            	}
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
    
    /**
     *  Store object that represents a store
     */
    private Store store;


}