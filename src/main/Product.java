package main;
/**
 * Creates a Product Object that represents an inventory item
 * @author Jacob Doney NetID:JD1568
 * @since 2023-08-03
 *
 */
public class Product implements Comparable<Product>{
	/**
	 * Default Constructor
	 */
	public Product(){
	}
	
	/**
	 * Constructor that generates and ID for product
	 * @param name String products name
	 * @param numberOfItems int quantity of product
	 */
	public Product(String name, int numberOfItems) {
		this.name = name;
		this.ID = IDCount++;
		this.numberOfItems = numberOfItems;
	}
	
	/**
	 * Constructor that takes in name, quantity, and id
	 * @param name String products name
	 * @param numberOfItems int quantity of product
	 * @param ID int value representing ID of product
	 */
	public Product(String name, int numberOfItems, int ID) {
		this.name = name;
		this.ID = ID;
		this.numberOfItems = numberOfItems;
		IDCount = ID + 1;
	}
	
	/**
	 * Sets the ID of product
	 * @param ID int value representing the products ID
	 */
	public void setId(int ID) {
		this.ID = ID;
	}
	/**
	 * Sets the name of product
	 * @param name String for name of product
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets number of items of product
	 * @param numberOfItems int the added amount of product
	 */
	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	
	/**
	 * Returns ID of product
	 * @return int indicating ID of product
	 */
	public Integer getID() {
		return ID;
	}
	/**
	 * Returns name of product
	 * @return String name of product
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns number of items
	 * @return int value for number of products
	 */
	public Integer getNumberOfItems() {
		return numberOfItems;
	}
	
	/**
	 * Compares Products based on number of Items
	 * @return int indicating if the compared product has more or less quantity
	 */
	@Override
	public int compareTo(Product otherProduct) {
		if(this.numberOfItems < otherProduct.numberOfItems) {
			return 1;
		}
		if(this.numberOfItems > otherProduct.numberOfItems) {
			return -1;
		}
		return 0;
	}
	/**
	 * Products name
	 */
	private String name;
	/**
	 * Products ID
	 */
	private int ID;
	/**
	 * Continuous count of IDs
	 */
	private static int IDCount = 1;
	/**
	 * Quantity of product
	 */
	private int numberOfItems;
	
	

}
