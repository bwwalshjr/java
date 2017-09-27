import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * The inventory class handles virtually all of the computation necessary for this program to run, including uploading inventory from text files, 
 * displaying the current inventory, searching for items, and removing items from the list. This class also includes two Comparators used to sort
 * the ArrayList by name order and by expiration date order.
 * @author Brian Walsh
 */
public class Inventory {
	
	//declares the inventoryList, doesn't initialize it here, only in the constructor
	ArrayList<FoodProduct> inventoryList;
	
	/**
	 * The constructor for the Inventory class. Initializes the inventoryList to an empty ArrayList.
	 */
	public Inventory(){
		inventoryList = new ArrayList<>();
	}
	
	/**
	 * This method uploads the inventory from the inventory.txt file into the inventoryList ArrayList, assuming that the data in the inventory.txt file is separated by commas.
	 */
	public void uploadInventory() {
		File inputFile = new File("inventory.txt");
		
		try {
			Scanner fileReader = new Scanner(inputFile);
			
			while(fileReader.hasNextLine()) { //reads until the file is empty
				String itemInfo = fileReader.nextLine();
				
				String[] singleProductArray = itemInfo.split(","); //divides the line into Strings based on where the commas are
				String name = singleProductArray[0].trim(); //takes the first element of the array and trims the whitespace
				String upc = singleProductArray[1].trim();
				int quantity = Integer.parseInt(singleProductArray[2].trim()); //converts the quantity section of the string into an int
				Double price = Double.parseDouble(singleProductArray[3].trim()); //converts the price section of the String into a Double OBJECT, as necessary for the FoodProduct constructor, as inherited from Product.java
				String expiration = singleProductArray[4].trim();
				
				FoodProduct tempProduct = new FoodProduct(name, upc, quantity, price, expiration);
				
				inventoryList.add(tempProduct);
			}//while bracket
			
			System.out.println("The inventory in the file inventory.txt has been successfully imported.");
			
			fileReader.close();
		}//try bracket
		
		catch (FileNotFoundException e) {
			System.out.println("Inventory.txt not found.");
			e.printStackTrace();
		}//catch bracket
	}//method bracket
	
	/**
	 * This method uploads the inventory from a file of the user's choosing into the inventoryList ArrayList, assuming that the data in the inventory.txt file is separated by commas.
	 * @param fileName The name of the file that the user would like to update the inventory from.
	 */
	public void uploadInventory(String fileName) {
		File inputFile = new File(fileName);
		
		try {
			Scanner fileReader = new Scanner(inputFile);
			
			while(fileReader.hasNextLine()) { //reads until the file is empty
				String itemInfo = fileReader.nextLine();
				
				String[] singleProductArray = itemInfo.split(","); //splits the line into an Array of Strings based on comma locations
				String name = singleProductArray[0].trim(); //takes the first element of the array, trims the whitespace, and sets the name equal to that string
				String upc = singleProductArray[1].trim();
				int quantity = Integer.parseInt(singleProductArray[2].trim()); //converts the quantity section of the string into an int
				Double price = Double.parseDouble(singleProductArray[3].trim()); //converts the price section of the String into a Double OBJECT, as necessary for the FoodProduct constructor, as inherited from Product.java
				String expiration = singleProductArray[4].trim();
				
				boolean productInInventory = false; //assumes this product is not in inventory until it is found
				for(int index = 0; index < inventoryList.size(); index++) { //iterates through existing inventory
					if(name.equals(inventoryList.get(index).getName())) {
						productInInventory = true; //tells the if conditional that the product has been found and a new product does not need to be created
						int newQuantity = inventoryList.get(index).getQuantity() + quantity; //adds the existing quantity and new quantity together
						inventoryList.get(index).setQuantity(newQuantity); //sets the quantity of the product to the new quantity
					}//if name.equals bracket
				}//for bracket
				
				if(!productInInventory) {
					FoodProduct tempProduct = new FoodProduct(name, upc, quantity, price, expiration); //creates a new FoodProduct if the product is not already in the ArrayList
					inventoryList.add(tempProduct);
				}//if productInInventory bracket
				
			}//while bracket
			
			System.out.println("The inventory in the file inventory.txt has been successfully updated from the file " + fileName + ".");
			
			fileReader.close();
		}//try bracket
		
		catch (FileNotFoundException e) {
			System.out.println(fileName + " not found.");
			e.printStackTrace();
		}//catch bracket
	}//method bracket

	/**
	 * Prints the ArrayList of FoodProducts after sorting it into alphabetical order of the products' names, using a Comparator inside this class.
	 */
	public void printByName() {
		
		Collections.sort(inventoryList, NAME_ORDER); //sorts by name
		
		System.out.format("%20s%15s%10s%10s%20s",  "Product Name", //prints formatted title string
				  							   	    "UPC", 
				  							   	    "Quantity",
				  							   	    "Cost",
				  							   		"Expiration Date");
		System.out.println();
		
		for(int index = 0; index < inventoryList.size(); index++) {
			
			System.out.format("%20s%15s%10s%10s%20s", inventoryList.get(index).getName(), //prints information for each product, formatted to match the title string
													  inventoryList.get(index).getUpc(), 
													  inventoryList.get(index).getQuantity(),
													  String.format("%.2f", inventoryList.get(index).getPrice()),
													  inventoryList.get(index).getExpiration());
			
			System.out.println();
		}//for loop iterating through list bracket
	}//method bracket
	
	/**
	 * Prints the ArrayList of FoodProducts after sorting it into expiration date order, soonest first, using a Comparator inside this class.
	 */
	public void printByExpiration() {
		
		Collections.sort(inventoryList, EXPIRATION_ORDER); //sorts by expiration date
		
		System.out.format("%20s%20s%15s%10s%10s", "Expiration Date", //prints formatted title string
											  	  "Product Name",
											  	  "UPC",
											  	  "Quantity",
											  	  "Cost");
		System.out.println();
		
		for (int index = 0; index < inventoryList.size(); index++) {
			
			System.out.format("%20s%20s%15s%10s%10s", inventoryList.get(index).getExpiration(), //prints information for each product, formatted to match the title string
												  	  inventoryList.get(index).getName(),
												  	  inventoryList.get(index).getUpc(),
												  	  inventoryList.get(index).getQuantity(),
												  	  String.format("%.2f", inventoryList.get(index).getPrice())); //String.format makes sure that the price is printed as $x.00 instead of $4.0 or similar
			System.out.println();
		}//for loop iterating through list bracket
	}//method bracket

	/**
	 * Searches for an item by name, and prints its information if it is found.
	 * @param itemName The name of the item that the user entered they would like to search for
	 */
	public void searchForItem(String itemName) {
		
		boolean itemFound = false; //tells the if conditional if a "Item not found" String needs to be printed
		
		for(int index = 0; index < inventoryList.size(); index++) {
			
			if(inventoryList.get(index).getName().equalsIgnoreCase(itemName)) { //checks for name equality, not case sensitive since users can make mistakes
			
				itemFound = true;
				System.out.println("Current stock report:");
				System.out.format("Item: " + //prints item information
								  inventoryList.get(index).getName() + " (UPC: " +
								  inventoryList.get(index).getUpc() + ") has " +  
						  		  inventoryList.get(index).getQuantity() + " in stock, priced at " +
						  		  String.format("$%.2f", inventoryList.get(index).getPrice()) + " and expiring " + //String.format makes sure that the price is printed as $x.00 instead of $4.0 or similar
						  		  inventoryList.get(index).getExpiration() + ".");
				System.out.println();
				
			}//if name equals bracket
		}//for loop iterating through list bracket
		
		if(!itemFound) {
			System.out.println(itemName + " is not a product carried by this store.");
			System.out.println();
		}//if item not found bracket
	}//method bracket
	
	/**
	 * Decreases the quantity of an item by one, given its UPC code. Also prints out updated information for the FoodProduct.
	 * @param upc The UPC code of the item that the user would like to decrement, passed in as a String.
	 */
	public void decrementItem(String upc) {
		
		boolean itemFound = false; //tells the if conditional if an "Item Not Found" string needs to be printed
		
		for(int index = 0; index < inventoryList.size(); index++) {
			
			if(inventoryList.get(index).getUpc().equals(upc)) {
				
				itemFound = true;
				
				if(inventoryList.get(index).getQuantity() == 0) {
					System.out.println("This item is already at 0 quantity.");
				} 
				
				else {
					
				inventoryList.get(index).setQuantity(inventoryList.get(index).getQuantity() - 1); //updates quantity to previous quantity minus one
				
				System.out.println("Updated stock information:");
				System.out.format("Item: " + //prints item information
						           inventoryList.get(index).getName() + " (UPC: " +
						           inventoryList.get(index).getUpc() + ") has " +  
						           inventoryList.get(index).getQuantity() + " in stock, priced at " +
						           String.format("$%.2f", inventoryList.get(index).getPrice()) + " and expiring " + //String.format makes sure that the price is printed as $x.00 instead of $4.0 or similar
						           inventoryList.get(index).getExpiration() + ".");
				System.out.println();
				}//else bracket
			}//if upc equals bracket
		}//for loop iterating through list bracket
		
		if(!itemFound) {
			System.out.println("ERROR: This item is not found in the inventory records.");
			System.out.println();
		}//if item not found bracket
	}//method bracket
	
	/**
	 * Finds an item given its name and removes it from the ArrayList, after printing its information.
	 * @param name The name of the item the user would like to remove from inventory.
	 */
	public void discontinueItem(String name) {
		
		boolean itemFound = false;
		
		for(int index = 0; index < inventoryList.size(); index++) {
			
			if(inventoryList.get(index).getName().equalsIgnoreCase(name)) { //not case sensitive to allow for user mistakes
				
				itemFound = true;
				System.out.println("The item " + //prints item information before it is all removed
								  inventoryList.get(index).getName() +
								  " (UPC: " + 
								  inventoryList.get(index).getUpc() + 
								  ") has been removed from inventory.");
				inventoryList.remove(index);
				System.out.println();
			}//if name equals bracket
		}//for loop iterating through list bracket
		
		if(!itemFound) {
			System.out.println("ERROR: This item is not found in the inventory records.");
			System.out.println();
		}//if item not found bracket
	}//method bracket
	
	/**
	 * Writes the existing inventory to a file upon quitting the program.
	 */
	public void writeInventory() {
		
		try {
			PrintWriter writer = new PrintWriter("inventory.txt", "UTF-8");

			for(int index = 0; index < inventoryList.size(); index++) {//iterates through entire list and prints each line
				 
				writer.println(inventoryList.get(index).getName() + ", " +
						  	   inventoryList.get(index).getUpc() + ", " +
						       inventoryList.get(index).getQuantity() + ", " + 
						       inventoryList.get(index).getPrice() + ", " + 
						       inventoryList.get(index).getExpiration());
			}//for bracket
			
			System.out.println("The inventory has been saved to inventory.txt");
			
			writer.close();
		}//try bracket
		
		catch(IOException e){
			System.out.println("There was an error writing the inventory.");
			e.printStackTrace();
		}//catch bracket
	}//method bracket
	
	/**
	 * This comparator uses item name to determine ordering of the ArrayList.
	 */
	static final Comparator<FoodProduct> NAME_ORDER = new Comparator<FoodProduct>() {
		
		public int compare(FoodProduct product1, FoodProduct product2) {
			
			int comparison = product1.getName().compareTo(product2.getName());
			return comparison;
			
		}//compare bracket
	};//Comparator bracket
	
	/**
	 * This Comparator uses expiration date to determine ordering of the ArrayList. Since the expiration date is stored as a String, the expiration date is parsed by index location to separate it into day, month, and year.
	 * After separation, it is converted to an Int from a String.
	 */
	static final Comparator<FoodProduct> EXPIRATION_ORDER = new Comparator<FoodProduct>() {
		
		public int compare(FoodProduct product1, FoodProduct product2) {
			int comparison;
			
			int product1Year = Integer.parseInt(product1.getExpiration().substring(4)); //takes the substring of the expiration date from the last two digits and converts it to an int year
			int product2Year = Integer.parseInt(product2.getExpiration().substring(4));
			
			comparison = product1Year - product2Year;
			
			if(comparison == 0) { //if year is equal, go to month
				int product1Month = Integer.parseInt(product1.getExpiration().substring(0, 2)); //takes the substring of the expiration date from the first two digits and converts it to an int month
				int product2Month = Integer.parseInt(product2.getExpiration().substring(0, 2));
				
				comparison = product1Month - product2Month;
				
				if(comparison == 0) { //if month is equal, go to day
					int product1Day = Integer.parseInt(product1.getExpiration().substring(2,4)); //takes the substring of the expiration date from the middle two digits and converts it to an int day
					int product2Day = Integer.parseInt(product2.getExpiration().substring(2, 4));
					
					comparison = product1Day - product2Day;
					
				}//if month is equal statement bracket
			}//if year is equal statement bracket
			
			return comparison;
			
		}//method bracket
	};//comparator bracket
	
}//class bracket
