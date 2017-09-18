/**
 * This class holds a student's data, including name, ID number, credit hours, and whether they are enrolled.
 * @author Brian Walsh
 *
 */
public class Student {

	private String name;
	private int idNumber;
	private int creditHours;
	private String enrolled;
	
	/**
	 * This default constructor initializes all data to 0 or null (as applicable), but is not used by the tester class.
	 */
	Student(){
		name = null;
		idNumber = 0;
		creditHours = 0;
		enrolled = null;
	}
	
	/**
	 * This constructor initializes data to values passed in.
	 * @param name the name of the student
	 * @param idNumber the ID number of the student
	 * @param creditHours the credit hours the student has taken
	 * @param enrolled whether the student is enrolled or not.
	 */
	Student(String name, int idNumber, int creditHours, String enrolled){
		this.name = name;
		this.idNumber = idNumber;
		this.creditHours = creditHours;
		this.enrolled = enrolled;
	}
	
	
	/**
	 * This is a getter method for the name variable.
	 * @return Returns the name of the student.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * This is a getter method for the ID variable.
	 * @return Returns the ID number for the student.
	 */
	public int getIdNumber(){
		return this.idNumber;
	}
	
	
	/**
	 * This is a getter method for the credit hours variable.
	 * @return Returns the credit hours the student has taken.
	 */
	public int getCreditHours(){
		return this.creditHours;
	}
	
	/**
	 * This is a getter method for the enrolled string variable.
	 * @return Returns the string describing whether a student is enrolled or not.
	 */
	public String getEnrolled() {
		return this.enrolled;
	}
	
	/**
	 * This is a setter method for the name variable.
	 * @param name The name variable that you wish to set the object's name to.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This is a setter method for the ID number variable.
	 * @param idNumber The ID number variable that you wish to set the object's ID to.
	 */
	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	
	/**
	 * This is a setter method for the credit hours variable.
	 * @param creditHours The credit hours variable that you wish to set the object's credit hours to.
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	
	/**
	 * This is a setter method for the enrolled string variable.
	 * @param enrolled The string variable that describes whether a student is enrolled that you wish to set the object's enrolled string to.
	 */
	public void setEnrolled(String enrolled) {
		this.enrolled = enrolled;
	}
	
}
