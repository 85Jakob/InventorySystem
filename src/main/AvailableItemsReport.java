package main;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Prints out all current items in inventory.
 * @author jacob
 * @since 2023-08-03
 */
public class AvailableItemsReport implements Report {

    /**
     * Default constructor
     */
    public AvailableItemsReport() {
    }

    /**
     * Prints out report
     */
    @Override
    public void printReport() {
    	System.out.println("\nAvailable Products Report:");
    	System.out.println("Products appear in the format: name(id): amount");
		collectAvailableItems();
    }

    /**
     * Prints out currently stocked items
     */
    private void collectAvailableItems() {
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
		        if(quantity > 0) {
		        	System.out.println(productName.trim() + "(" +  productID + "): " + quantity);
		        }

		      }
		      myReader.close();
		      System.out.println();
		} 
		catch (IOException e) {
		      e.printStackTrace();
		}
    }

}
