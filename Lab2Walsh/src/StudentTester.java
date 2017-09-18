import java.util.ArrayList;
import java.util.Scanner;

public class StudentTester {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter the number of students you would like to enter, followed by the data for the students.");
		System.out.println("Use the format name,id,credits,enrolled");
		
		int size = keyboard.nextInt();
		keyboard.nextLine();
		
		Student[] studentList = new Student[size];
		
		for(int i=0; i<size; i++) {
			studentList[i] = new Student();
			
			String studentInfo = keyboard.nextLine();
						
			int firstComma = studentInfo.indexOf(',');
			String studentName = studentInfo.substring(0, firstComma);
			studentList[i].setName(studentName);
			
			int secondComma = studentInfo.indexOf(',', firstComma + 1);
			String idNumberString = studentInfo.substring(firstComma + 1, secondComma);
			int idNumber = Integer.parseInt(idNumberString);
			studentList[i].setIdNumber(idNumber);
			
			int thirdComma = studentInfo.indexOf(',', secondComma + 1);
			String creditHoursString = studentInfo.substring(secondComma + 1, thirdComma);
			int creditHours = Integer.parseInt(creditHoursString);
			studentList[i].setCreditHours(creditHours);
			
			int lineLength = studentInfo.length();
			String enrolled = studentInfo.substring(thirdComma + 1, lineLength);
			studentList[i].setEnrolled(enrolled);
			
			//System.out.println("Name: " + studentName);
			//System.out.println("ID: " + idNumber);
			//System.out.println("Credit Hours: " + creditHours);
			//System.out.println("Enrolled: " + enrolled);
		}
		
		for(int i=0; i< studentList.length; i++) {
			if(studentList[i].getCreditHours() > 30) {
				System.out.println(studentList[i].getName() + "," + studentList[i].getIdNumber() + "," + studentList[i].getCreditHours() + "," + studentList[i].getEnrolled());
			}
		}
		
	}
	
}
