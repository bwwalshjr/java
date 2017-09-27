/**
 * The FoodProduct class extends the Product class, but stores the additional information of UPC and expiration date, both stored as Strings. There is no setter methods for these two data members as all 
 * interaction with these data members should be through the constructor.
 * @author Brian Walsh
 */
public class FoodProduct extends Product{

	private String upc;
	private String expiration;
	
	/**
	 * The constructor for the FoodProduct class, which requires all fields' information.
	 * @param name The name of the product, stored as a String.
	 * @param upc The UPC code of the product, stored as a String.
	 * @param quantity The current quantity of the product, stored as an int.
	 * @param price The price of the product, stored as a Double *Object*, not primitive!
	 * @param expiration The expiration date of the product, stored as a String in the format MMDDYY.
	 */
	FoodProduct(String name, String upc, int quantity, Double price, String expiration){
		this.name = name;
		this.upc = upc;
		this.quantity = quantity;
		this.price = price;
		this.expiration = expiration;
	}
	
	/**
	 * The getter method for the UPC code; returns the UPC as a String.
	 * @return The UPC code for this product, as a String.
	 */
	public String getUpc() {
		return this.upc;
	}
	
	/**
	 * The getter method for the expiration date; returns the expiration date as a String in the format MMDDYY.
	 * @return The expiration date for this product, as a String, in the format MMDDYY.
	 */
	public String getExpiration() {
		return this.expiration;
	}

}
