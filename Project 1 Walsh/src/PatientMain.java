import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * This main program contains the main menu that allows the User to manipulate the PatientList class. Most of the work is done inside the PatientList class, but new Patients are instantiated
 * and added to the patientList from this menu. Also, there are two File I/O methods used to maintain continuity with patient IDs in the system.
 * @author Brian Walsh
 */
public class PatientMain {

	/**
	 * The main method of the program initializes a patientList and allows the User to interact with the PatientList class through an infinite while loop with a switch statement for the User's choices.
	 * @param args The argument list should be empty for this main method. None are used.
	 */
	public static void main(String[] args) {
	
	int currentId = readFile();//will throw file I/O exception if the user has not quit the program before, but will initialize to 0 anyways
	
	PatientList patientList = new PatientList();
	
	System.out.println("Please select your option from the following menu: " );	
	
	Scanner keyboard = new Scanner(System.in);
	
		while(true) {//this loop runs until they choose "Quit", in which case the program ends.
			
			System.out.println("");
			
			System.out.println("E: Enter a new patient");
			System.out.println("N: Find next patient and remove him/her from the list");
			System.out.println("F: Find the position of a specific patient");
			System.out.println("S: Display the list of patients sorted by priority");
			System.out.println("P: Print the list of patients sorted by patient ID");
			System.out.println("Q: Quit");
			
			String response = keyboard.nextLine();
			response = response.toLowerCase(); //to ensure that there is no issue with lower-case letters being entered instead of upper-case, etc.
			
			switch (response) {
			case "e": System.out.println("Enter the patient's last name:"); //this case creates a new Patient, then passes the Patient to the patientList class to be added
					  String last = keyboard.nextLine();
					  
					  System.out.println("Enter the patient's first name:");
					  String first = keyboard.nextLine();
					  
					  System.out.println("Enter the patient's priority:");
					  String priority = keyboard.nextLine();
					  int priorityInt = Integer.parseInt(priority); //since the priority passed into the Patient constructor must be an int, but the primitive type int doesn't always like leading 0's that the User will sometimes use.
					  
					  LocalDateTime time = LocalDateTime.now();
					  int hour = time.getHour(); //parses the time into individual hour and minute to make comparison easier.
					  int minute = time.getMinute();
					  
					  Patient newPatient = new Patient(first, last, priorityInt, currentId, hour, minute);
					  patientList.addPatient(newPatient);
					  
					  currentId++; //makes sure the ID increases for the next patient
					  break;
			
			case "n": patientList.findNextPatient();
					  break;
			
			case "f": patientList.findSpecificPatient();
					  break;
			
			case "s": patientList.printPatientsByPriority();
				      break;
				      
			case "p": patientList.printPatientsById();
					  break;
					  
			case "q": writeFile(currentId);
					  keyboard.close();//written to get rid of the warning for the Scanner never being closed
					  System.exit(0);
					  break;
					  
			default: System.out.println("Pick a valid option.");
					 break;
			
			}//switch bracket
		}//while bracket
	}
	
	/**
	 * This method reads a file for the ID number that the system should begin with on this run.
	 * @return The ID number that the system should begin with, saved from the last time the User used the quit() function.
	 */
	public static int readFile() {
		File inputFile = new File("idFile.txt"); //names the file for the User
		int currentIdNumber = 1; //starts the ID at 1 if none are read in
		
		try {
			Scanner fileReader = new Scanner(inputFile);
			currentIdNumber = fileReader.nextInt();
			fileReader.close();
		}
		
		catch (FileNotFoundException e) {
			System.out.println("ID source file not found");
			e.printStackTrace();
		}
		
		return currentIdNumber;
	}
	
	/**
	 * This method writes the ID number that the system should start with to a file (which is the same file the System reads from when it starts).
	 * @param idToWrite The ID number that the system should start with on the next run.
	 */
	public static void writeFile(int idToWrite) {
		
		try {
			PrintWriter writer = new PrintWriter("idFile.txt", "UTF-8");
			writer.println(idToWrite);
			writer.close();
		}
		
		catch(IOException e){
			System.out.println("There was an error writing the ID file");
			e.printStackTrace();
		}
		
	}
}
