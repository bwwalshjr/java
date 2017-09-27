import java.util.Scanner;

class ProductTester {

   public static void main(String args[]) {
       
	   Scanner keyboard = new Scanner(System.in); //for user input
	  
	   System.out.println("Welcome to the Foods B Us Inventory Management System.");
	   
	   Inventory inventoryList = new Inventory();
	   
	   inventoryList.uploadInventory(); //starts the inventory from the inventory.txt file
	   
	   System.out.println("Please choose an option from the following menu:");

	   while(true) { //loops until the user selects Quit from the menu
		   
		   System.out.println("");
		   
		   System.out.println("U: Upload product information for a delivery");
		   System.out.println("P: Print the current inventory sorted by product name");
		   System.out.println("E: List the current inventory sorted by expiration date");
		   System.out.println("S: Search for an item by product name");
		   System.out.println("D: Decrement the quantity for an item");
		   System.out.println("R: Remove/discontinue an item");
		   System.out.println("Q: Quit the system");
		   
		   String response = keyboard.nextLine().toLowerCase(); //makes user input NOT case sensitive
		   
		   switch (response) {
		        case "u": System.out.println("Enter the file name that you would like to upload delivery information from.");
		        		  String fileName = keyboard.nextLine();
		        		  inventoryList.uploadInventory(fileName);//updates the inventoryList using the information read in from the file passed in
		        		  break;
		        		  
		        case "p": inventoryList.printByName();
		        		  break;
		        		  
		        case "e": inventoryList.printByExpiration();
		        		  break;
		        		  
		        case "s": System.out.println("Enter the name of the product you would liked to search for.");
		        		  String searchItem = keyboard.nextLine();
		        		  inventoryList.searchForItem(searchItem);
		        		  break;
		        		  
		        case "d": System.out.println("Enter the UPC of the product you would like to decrement the quantity of.");
		        		  String decrementItemUPC = keyboard.nextLine();
		        		  inventoryList.decrementItem(decrementItemUPC);
		        		  break;
		        		  
		        case "r": System.out.println("Enter the name of the product you would like to discontinue.");
		        		  String discontinuedItem = keyboard.nextLine();
		        		  inventoryList.discontinueItem(discontinuedItem);
		        		  break;
		        		  
		        case "q": inventoryList.writeInventory();
		        		  System.out.println("The program will now exit.");
		        		  keyboard.close();
		        		  System.exit(0);
		        		  break;
		        		  
		        default:  System.out.println("Pick a valid option.");
		        		  break;
		        		  
		   }//switch bracket   
	   }//while bracket
   }//main method bracket
}//class bracket