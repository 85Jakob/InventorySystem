package main;

/**
 * Store class that creates a Store object that represents a store
 * @author Jacob Doney NetID: JD1568
 * @since 2023-08-01
 */
public class Store {
	/**
	 * Constructor to create store with ID generated for it
	 * @param name String name for store
	 * @param address String address for store
	 */
	public Store(String name, String address) {
		this.name = name;
		this.address = address; 
		this.ID = IDCount++;
	}
	
	/**
	 * Constructor to create store with user input ID
	 * @param name String name of store
	 * @param address String address of store
	 * @param ID int ID of store
	 */
	public Store(String name, String address, int ID) {
		this.name = name;
		this.address = address; 
		this.ID = ID;
	    IDCount = ID + 1;
	}
	
	/**
	 * returns name of store
	 * @return String name of store
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns address of store
	 * @return String address for store
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * returns store id
	 * @return Integer value for store ID
	 */
	public Integer getID() {
		return ID;
	}
	/**
	 * String name for store
	 */
	private String name; 
	/**
	 * String address for store
	 */
	private String address; 
	/**
	 * int ID value for store
	 */
	private int ID;
	/**
	 * int IDCount keeps track of ID values
	 */
	private static int IDCount = 1;
	
	
}
