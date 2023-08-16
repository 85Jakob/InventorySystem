package main;
/**
 * Creates an Incoming Transaction Object that represents an incoming transaction.
 * @author Jacob Doney NetID: JD1568
 * @since 2023-08-03
 *
 */
public class IncomingTransaction extends Transaction{
    /**
     * Default constructor
     */
    public IncomingTransaction() {
    }

    /**
     * Updates quantity of product in productList.
     * @param currentProduct Product being added to inventory
     * @param amount int value of how many products are being added
     */
    public void updateProductStock(Product currentProduct, int amount) {
        amount = currentProduct.getNumberOfItems() + amount;
    	currentProduct.setNumberOfItems(amount);	
    }
    
    /**
     * Prints the transaction type.
     */
    @Override
    public String toString() {
    	return "Incoming";
    }

}
