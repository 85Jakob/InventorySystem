package main;

import java.util.*;

/**
 * Creates a transaction object that stores transaction information
 * @author Jacob Doney NetID: JD1568
 * @since 2023-08-03
 */
public class Transaction {

    /**
     * Default constructor store transaction ID, current date, and creates a hash map of products 
     */
    public Transaction() {
    	ID = IDCount++;
    	date = new Date();
    	productList = new HashMap<Product, Integer>();
    }

    /**
     * adds product to list if it is absent
     * @param pr Product to be record
     * @param numberOfItems number of items in transaction
     * @return boolean true if added
     */
    public boolean addProduct(Product pr, int numberOfItems) {
        return ((productList.putIfAbsent(pr, numberOfItems) == null));
    }
    /**
     * returns date of transaction
     * @return Date date of transaction
     */
    public Date getDate() {
    	return date;
    }
    /**
     * Returns ID of transaction
     * @return int ID of transaction 
     */
    public int getID() {
    	return ID;
    }
    /**
     * Returns map of Product and amount of product during transaction
     * @return map<Product, Integer> A lists of product during transaction and how much of that
     * product was entered
     */
    public Map<Product, Integer> getProductList(){
    	return productList;
    }

    /**
     * int value for ID of transaction
     */
    private int ID = 1;
    /**
     * int count of IDs.
     */
    private static int IDCount = 0;
    /**
     * Date of transaction
     */
    private Date date;
    /**
     * Map<Product, Integer> A list of products during transaction and how much of that
     * product was entered
     */
    private Map<Product, Integer> productList;

}
