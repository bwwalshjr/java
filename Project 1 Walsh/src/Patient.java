/**
 * The Patient class stores data about a patient, such as first name, last name, priority, ID number, and the hour and minute that they arrived. It also formats its data for
 * use in some cases, such as the correct format for ID number and priority number.
 * NOTE: This class does not contain setter methods for ANY of its data, as no values should be changed once the patient has been originally entered.
 * @author Brian Walsh
 */

public class Patient{

	private String firstName;
	private String lastName;
	private int priority;
	private int idNumber;
	private int hour;
	private int minute;
	
	/**
	 * The constructor for the Patient class immediately takes in all the information that the Patient class stores.
	 * @param first The first name of the patient.
	 * @param last The last name of the patient.
	 * @param priority The priority of the patient.
	 * @param id The ID number of the patient.
	 * @param hour The hour the patient arrived.
	 * @param minute The minute the patient arrived.
	 */
	Patient(String first, String last, int priority, int id, int hour, int minute){
		this.firstName = first;
		this.lastName = last;
		this.priority = priority;
		this.idNumber = id;
		this.hour = hour;
		this.minute = minute;
	}
	
	/**
	 * Getter method for this patient's first name.
	 * @return The string containing this Patient's first name. 
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Getter method for this patient's last name.
	 * @return The String containing this patient's last name.
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Getter method for this patient's priority.
	 * @return The int containing this patient's priority.
	 */
	public int getPriority() {
		return this.priority;
	}
	
	/**
	 * Getter method for this patient's priority, but formatted as a String to match the required three digit format when entered priority is less than three digits.
	 * @return A String containing this patient's priority.
	 */
	public String getPriorityString() {
		String priorityString = "";
		
		String intString = Integer.toString(this.priority);
		int lengthOfInt = intString.length();
		
		switch (lengthOfInt) {//this switch statement determines number of leading 0's that the priority string is missing and adds them.
			case 1: priorityString = ("00" + intString);
					break;
			case 2: priorityString = ("0" + intString);
					break;
			case 3: priorityString = ("" + intString);
		}
		
		return priorityString;
	}
	
	/**
	 * The getter method for this patient's ID number.
	 * @return The int form of this patient's ID number.
	 */
	public int getIdNumber() {
		return this.idNumber;
	}
	
	/**
	 * The getter method for this patient's ID number, but formatted as a String to match the required six digit format when generated ID number is less than six digits.
	 * @return A String containing this patient's priority.
	 */
	public String getIdString() {
		String idString = "";
		
		String intString = Integer.toString(this.idNumber);
		int lengthOfInt = intString.length();
		
		switch (lengthOfInt) { //this switch statement determines number of leading 0's that the ID string is missing and adds them.
			case 1: idString = ("00000" + intString);
					break;
			case 2: idString = ("0000" + intString);
					break;
			case 3: idString = ("000" + intString);
					break;
			case 4: idString = ("00" + intString);
					break;
			case 5: idString = ("0" + intString);
					break;
			case 6: idString = ("" + intString);
					break;
		}
		
		return idString;
	}
	
	/**
	 * Getter method for this patient's hour of arrival.
	 * @return The hour of this patient's arrival, as an int.
	 */
	public int getHour() {
		return this.hour;
	}
	
	/**
	 * Getter method for this patient's minute of arrival.
	 * @return The minute of this patient's arrival, as an int.
	 */
	public int getMinute() {
		return this.minute;
	}
	
	/**
	 * Getter method for this patient's time of arrival, formatted as a String. If minute is single-digit, a '0' is concatenated to it to make sure it displays correctly.
	 * For example, 8:04 instead of 8:4
	 * @return The time of this patient's arrival, formatted as a String.
	 */
	public String getTimeString() {
		String timeString;
		
		String intString = Integer.toString(this.minute);
		int lengthOfInt = intString.length();
		
		if(lengthOfInt == 1) {
			intString = "0" + intString; //adds a 0 if the minute is single-digit, to match the format hh:mm
		}
		timeString = (this.hour + ":" + intString); //concatenates hour and minute together with a ':'
		return timeString;
	}
	
}//class bracket
