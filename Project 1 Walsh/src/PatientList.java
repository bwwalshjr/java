import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class contains the ArrayList of Patients, and does much of the operations to display, search, and manipulate the list.
 * @author Brian Walsh
 */
public class PatientList {

	private ArrayList<Patient> patientList;
	private Scanner keyboard;
	
	/**
	 * Constructor for the PatientList class, creates the Scanner used in the findPatient method and initializes patientList to an empty ArrayList.
	 */
	public PatientList() {
		patientList = new ArrayList<>();
		keyboard = new Scanner(System.in);
	}
	
	/**
	 * Adds a Patient to the patientList.
	 * @param patientName The Patient object to be added to the patientList.
	 */
	public void addPatient(Patient patientName) {
		patientList.add(patientName);
	}
	
	/**
	 * Finds the next Patient in the list, sorted by priority, then time if priorities are equal. Prints the Patient's name and removes it from the list.
	 */
	public void findNextPatient() {
		
		if(patientList.size() == 0) {
			System.out.println("The patient list is empty.");
			return;
		}
		
		Collections.sort(patientList, PRIORITY_AND_TIME_ORDER);//uses the priority and time comparator. Sorts by priority first, then time if priority is equal.
		
		System.out.println("Patient " +
						   patientList.get(0).getFirstName() + " " +
						   patientList.get(0).getLastName() + " with ID " +
						   patientList.get(0).getIdString() + " is ready to be seen.");
		
		patientList.remove(0);
	}
	
	/**
	 * Finds a specific patient given a first and last name. Sorts by priority and time before searching the ArrayList so that positions can be printed accurately.
	 */
	public void findSpecificPatient() {
		System.out.println("Please enter the patient's last name:");
		String searchLast = keyboard.nextLine();
		System.out.println("Please enter the patient's first name:");
		String searchFirst = keyboard.nextLine();
		
		Collections.sort(patientList, PRIORITY_AND_TIME_ORDER); //sorts before searching so that the positions of found patients can be printed accurately.
		
		boolean found = false;//informs the if statement after the for loop as to whether the patient was found and printed or not
		
		for(int index = 0; index < patientList.size(); index++) {
			String patientLast = patientList.get(index).getLastName();
			String patientFirst = patientList.get(index).getFirstName();
			
			if(searchLast.equalsIgnoreCase(patientLast) && searchFirst.equalsIgnoreCase(patientFirst)) { //equalsIgnoreCase just in case the User forgets capital letters or something
				System.out.println("There are " + index + " people ahead of " + searchFirst + " " + searchLast + " (ID: " + patientList.get(index).getIdString() + ").");
				found = true;//makes sure the "Patient not found" String does not print if a Patient was indeed found.
			}
		}
		
		if(!found) {
			System.out.println("Patient " + searchFirst + " " + searchLast + " not found.");
		}
	}
	
	/**
	 * Uses the comparator of priority and time to sort the list before printing the list, formatted to line up neatly. Sorts into descending priority.
	 */
	public void printPatientsByPriority() {
		Collections.sort(patientList, PRIORITY_AND_TIME_ORDER);
		
		System.out.format("%10s%10s%15s%15s%15s", "Priority", //formats the output to look neat. Numbers can be changed to allow for larger names.
				  							  	  "ID",
				  							  	  "Arrival Time",
				  							  	  "Last Name",
				  							  	  "First Name");
		System.out.println("");
		
		for(int index = 0; index < patientList.size(); index++) {
			System.out.format("%10s%10s%15s%15s%15s", patientList.get(index).getPriorityString(), //formats the output to look neat. Numbers can be changed to allow for larger names.
								  					  patientList.get(index).getIdString(),
								  					  patientList.get(index).getTimeString(),
								  					  patientList.get(index).getLastName(),
								  					  patientList.get(index).getFirstName());
			System.out.println("");
		}
	}
	
	/**
	 * Uses the comparator of ID to sort the list before printing, formatted to line up neatly. Sorts into ascending ID number.
	 */
	public void printPatientsById() {
		Collections.sort(patientList, ID_ORDER);
		
		System.out.format("%10s%10s%15s%30s", "ID", //formats the output to look neat. Numbers can be changed to allow for larger names.
											  "Priority",
											  "Arrival Time",
				  							  "Last Name",
				  							  "First Name");
		System.out.println("");
		
		for(int index = 0; index < patientList.size(); index++) {
			System.out.format("%10s%10s%15s%15s%15s", patientList.get(index).getIdString(), //formats the output to look neat. Numbers can be changed to allow for larger names.
								  					  patientList.get(index).getPriorityString(),
								  					  patientList.get(index).getTimeString(),
								  					  patientList.get(index).getLastName(),
								  					  patientList.get(index).getFirstName());	
			System.out.println("");
		}
	}
	
	/**
	 * Creates a Comparator to sort the patientList into ascending ID numbers.
	 */
	static final Comparator<Patient> ID_ORDER = new Comparator<Patient>() {
		
		public int compare(Patient patient1, Patient patient2) {
			int comparison;
			comparison = patient1.getIdNumber() - patient2.getIdNumber();
			return comparison;
		}//compare bracket
		
	};//Comparator bracket
	
	/**
	 * Creates a Comparator to sort the patientList into descending priority. Sorts by time in the situation that the priorities for two Patients are equal.
	 */
	static final Comparator<Patient> PRIORITY_AND_TIME_ORDER = new Comparator<Patient>() {
		
		public int compare(Patient patient1, Patient patient2) {
			int comparison;
			comparison = patient2.getPriority() - patient1.getPriority();
			
			if(comparison == 0) {
				comparison = patient1.getHour() - patient2.getHour();
				if(comparison == 0) {
					comparison = patient1.getMinute() - patient2.getMinute();
				}
			}
			
			return comparison;
		}//compare bracket
		
	};//Comparator bracket
	
}//class bracket
