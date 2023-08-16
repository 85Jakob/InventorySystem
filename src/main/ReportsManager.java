package main;

import java.util.*;

/**
 * Manages all the reports
 * @author Jacob Doney NetID: JD1568
 * @since 2023-08-03
 */
public class ReportsManager {

    /**
     * Default constructor
     */
    public ReportsManager() {
    }

    /**
     * Controls menu then calls for report to be generated
     * @param reportID char indicating what report to generate
     * @param stockManager StockManager Object that to access stores
     */
    public void generateReport(char reportID, StockManager stockManager) {
    	Report myReport; 
    	Scanner scnr;
    	switch(reportID) {
		case 'A':
		case 'a': 
				myReport = new AllItemsEnteredReport();
				myReport.printReport();
				break;
		case 'V':
		case 'v': 
				myReport = new AvailableItemsReport();
				myReport.printReport();
				break;
		case 'S':
		case 's':
				scnr = new Scanner(System.in);
				System.out.println("List of available stores:");
				List<Store> storeList = stockManager.getStores(); 
				System.out.println("0: all stores");
				for(int i = 0; i < storeList.size(); i++) {
					System.out.println(storeList.get(i).getID() + ": " + storeList.get(i).getName());
				}
				System.out.print("Select a store or type 0 for all: ");
				int userInput = 0;
				if(scnr.hasNextInt()) {
					userInput = scnr.nextInt();
				}
				else {
					System.out.println("Invalid input: Showing products for all stores");
				}
				int storeID = -1;
				Store currentStore = null;
				for(int i = 0; i < storeList.size(); i++) {
					if(storeList.get(i).getID().equals(userInput)) {
						storeID = userInput;
						currentStore = storeList.get(i);
					}
				}
				if( userInput == 0) {
					currentStore = null;
					storeID = 0;
				}
				if(storeID == -1) {
					System.out.println("Invalid Input");
					return;
				}
				myReport = new ProductByStoreReport(currentStore);
				myReport.printReport();
				break;
		case 'T':
		case 't': 
				myReport = new AllTransactionReport();
				myReport.printReport();
				break;
		case 'H':
		case 'h':
				myReport = new HighVolumeProductsReport();
				myReport.printReport();
				break;
		default: 
			System.out.println("Invalid Input.");
			break;
		}
    }

}