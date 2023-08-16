package main;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter; 
import java.io.PrintWriter;
import java.io.BufferedWriter;
/**
 * Manages the list of stores and product lists
 * @author Jacob Doney NetID: JD1568
 * @since 2023-08-03
 */
public class StockManager {
	/**
	 * Constructor creates the storeList and productList
	 * Generates files for store.txt and products.txt if not already present
	 * and imports information from files 
	 */
	public StockManager() {
		storeList = new ArrayList<Store>();
		productList = new ArrayList<Product>();
		createFiles();
		loadProducts();
		loadStores();
	}
	
	/**
	 * Adds a store to storeList
	 * @param newStore Store object to be added to store list
	 * @return boolean true if store is added to list
	 */
	public boolean addStore(Store newStore) {
		if(storeList.isEmpty()) {
			storeList.add(newStore);
		}
		else {
			// Checks to see if store is already in list 
			for(int i = 0; i < storeList.size(); i++) {
				if(storeList.get(i).getID().equals(newStore.getID())) {
					return false;
				}
				else if(storeList.get(i).getAddress().toLowerCase().equals(newStore.getAddress())) {
					return false;
				}
				else if(storeList.get(i).getName().toLowerCase().equals(newStore.getName())){
					return false;
				}
			}
			storeList.add(newStore);
		}
		return true;
	}
	
	/**
	 * Adds a product to products list and returns true if added
	 * @param newProduct Product object to be add to products list
	 * @return boolean true if product is added to list
	 */
	public boolean addProduct(Product newProduct) {
		if(productList.isEmpty()) {
			productList.add(newProduct);
		}
		else {
			// Check to see if product is already in list
			for(int i = 0; i < productList.size(); i++) {
				if(productList.get(i).getID().equals(newProduct.getID())) {
					return false;
				}
				if(productList.get(i).getName().toLowerCase().equals(newProduct.getName().toLowerCase())) {
					return false;
				}
			}
			productList.add(newProduct);
		}
		return true;
	}
	
	/**
	 * Returns list of products
	 * @return List<Store> a list of stores
	 */
	public List<Store> getStores() {
		return storeList;
	}
	
	/**
	 * Returns list of products
	 * @return List<Product> a list of products
	 */
	public List<Product> getProducts() {
		return productList;
	}
	
	/**
	 * Creates Products and Store files if not already created
	 */
	public void createFiles() {
		 try {
		      File productFile = new File("res/Products.txt");
		      productFile.createNewFile();
		      File storeFile = new File("res/Stores.txt");
		      storeFile.createNewFile();

		} 
		 catch (IOException e) {
		      e.printStackTrace();
		}
	}
	
	/**
	 * Write products to file.
	 */
	public void updateProductLog() {
		
		File file = new File("res/Products.txt");
    	FileWriter fr = null;
    	BufferedWriter br = null;
    	PrintWriter pr = null;
		try {
			fr = new FileWriter(file, false);
    		br = new BufferedWriter(fr);
    		pr = new PrintWriter(br);
    		Product currentProduct;
    		for(int i = 0; i < productList.size(); i++) {
    			currentProduct = productList.get(i);
    			pr.print(currentProduct.getID() + " ");
    			pr.print(currentProduct.getName() + " ");
    			pr.print(currentProduct.getNumberOfItems());
    			pr.println();
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			pr.close();
    			br.close();
    			fr.close();
    		} catch(IOException e) {
    			e.printStackTrace();
    		}
    	}
	}
	
	/**
	 * Writes Stores to file
	 */
	public void updateStoreLog() {
		File file = new File("res/Stores.txt");
    	FileWriter fr = null;
    	BufferedWriter br = null;
    	PrintWriter pr = null;
		try {
			fr = new FileWriter(file, false);
    		br = new BufferedWriter(fr);
    		pr = new PrintWriter(br);
    		Store currentStore;
    		for(int i = 0; i < storeList.size(); i++) {
    			currentStore = storeList.get(i);
	    		pr.print(currentStore.getID() + " ");
	    		pr.print(currentStore.getName() + ": ");
	    		pr.print(currentStore.getAddress());
	    		pr.println();
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			pr.close();
    			br.close();
    			fr.close();
    		} catch(IOException e) {
    			e.printStackTrace();
    		}
    	}
	}
	
	/**
	 * Loads products from file
	 */
	public void loadProducts() {
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
		        Product loadProduct = new Product(productName.trim(), quantity, productID);
		        addProduct(loadProduct);
		      }
		      myReader.close();
		} 
		catch (IOException e) {
		      e.printStackTrace();
		}
		  
	}
	
	/**
	 * Loads stores from file
	 */
	public void loadStores() {
		try {
		      File storesFile = new File("res/Stores.txt");
		      Scanner myReader = new Scanner(storesFile);
		      while (myReader.hasNext()) {
		        int storeID = myReader.nextInt();
		        String nameAddress = myReader.nextLine();
		        String[] aSplit = nameAddress.split(":");
		        Store loadStore = new Store(aSplit[0].trim(), aSplit[1].trim(), storeID);
		        addStore(loadStore);
		      }
		      myReader.close();
		} 
		catch (IOException e) {
		      e.printStackTrace();
		}
		  
	}
	/**
	 * List of all stores
	 */
	private List<Store> storeList;
	/**
	 * List of all products
	 */
	private List<Product> productList;
}
