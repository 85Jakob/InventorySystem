package main;
/**
 * Creates an outgoing Transaction Object that represents an outgoing transaction.
 * @author Jacob Doney NetID: JD1568
 * @since 2023-08-03
 *
 */
public class OutgoingTransaction extends Transaction {
	
	/**
     * Default constructor
     */
    public OutgoingTransaction(Store store) {
    	this.store = store;
    }
    
    /**
     * Returns the Store associated with transaction.
     * @return store Store object associated with transaction
     */
    public Store getStore() {
    	return store;
    }


    /**
     * Updates product amount in inventory.
     * @param currentProduct Product going to store.
     * @param amount int value of how many products to transfer.
     */
    public void updateProductStock(Product currentProduct, int amount) {
    	amount = currentProduct.getNumberOfItems() - amount;
    	currentProduct.setNumberOfItems(amount);
    }
    
    /**
     * Prints out transaction type
     */
    @Override
    public String toString() {
    	return "Outgoing";
    }
    
    /**
     *  Store object associated with transaction
     */
    private Store store;

}
