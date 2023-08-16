package main;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
/**
 * Manages and logs transactions
 * @author Jacob Doney NetID: JD1568
 * @since 2023-08-03
 */
public class TransactionsManager {
    /**
     * Default constructor
     */
    public TransactionsManager() {
    	transactionList = new ArrayList<Transaction>();
    }

    /**
     * Determines which file to add transaction to
     * @param tr Transaction object to be appended to file
     */
    public void addTransaction(Transaction tr) {
    	transactionList.add(tr);
    	String fileName = tr.toString() + "_Transactions.txt";                                                                                                                                        
    	appendToFile(fileName, tr);
    }

    /**
     * Adds transaction information to file
     * @param fileName String that is the fileName
     * @param tr Transaction object that will be added to file
     */
    public void appendToFile(String fileName, Transaction tr) {
        String filePath = "res/" + fileName;
    	File file = new File(filePath);
    	FileWriter fr = null;
    	BufferedWriter br = null;
    	PrintWriter pr = null;
    	if(!file.exists()) {
    		try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	try {
    		fr = new FileWriter(file, true);
    		br = new BufferedWriter(fr);
    		pr = new PrintWriter(br);
    		pr.println();
    		pr.print(tr.toString() + " ");
    		pr.print(tr.getID() + " ");
    		// Format date
    		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
    		pr.println(formatter.format(tr.getDate()));
    		// If Outgoing print out store information
    		if(tr.toString().equals("Outgoing")) {
    			OutgoingTransaction ot = (OutgoingTransaction) tr;
    			pr.println(ot.getStore().getID() + " : " + ot.getStore().getName() + " : " + ot.getStore().getAddress());
    		}
    		// print out products
    	    for(Product name: tr.getProductList().keySet()) {
    	    	String key = name.getName();
    	    	String keyID = name.getID().toString();
    	    	String value = tr.getProductList().get(name).toString();
    	    	pr.println(keyID + " : " + key + " : " + value);
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
     * List of all transactions
     */
    private List<Transaction> transactionList;
}
