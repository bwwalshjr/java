import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StudentTester {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("ENter Input Filename: ");
		String inputFileName = keyboard.nextLine();
		
		System.out.println("Enter Minimum Credits: ");
		int minimumCredits = keyboard.nextInt();
		
		keyboard.nextLine();
		
		System.out.println("Enter Output Filename: " );
		String outputFileName = keyboard.nextLine();
		
		keyboard.close();
		
		ArrayList<Student> studentList = readFile(inputFileName);
				
		ArrayList<Student> outputList = new ArrayList<Student>();
		
		for(int i=0; i< studentList.size(); i++) {
			if(studentList.get(i).getCreditHours() > minimumCredits) {
				outputList.add(studentList.get(i));
			}
		}
		
		writeFile(outputFileName, outputList);
	
		System.out.println(inputFileName + " successfully processed");
	}
	
	public static ArrayList<Student> readFile(String fileName) {
		ArrayList<Student> studentList = null;
		
		if(fileName == null || fileName.equals("")) {
			return studentList;
		}
		
		File inputFile = new File(fileName);
		
		try {
			studentList = new ArrayList<>();
			
			Scanner fileReader = new Scanner(inputFile);
			
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				String regexParseString = "\\s*(\\s|,)\\s*";
				String[] stringValues = line.split(regexParseString);
				System.out.println(Arrays.toString(stringValues));
				Student tempStudent = new Student(stringValues[0], 
												  Integer.parseInt(stringValues[1]), 
												  Integer.parseInt(stringValues[2]), 
												  stringValues[3]);
				studentList.add(tempStudent);
			}
			
			fileReader.close();
			
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		return studentList;
	}

	public static void writeFile(String fileName, ArrayList<Student> studentList) {
		
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for(Student s: studentList) {
				writer.println(s);
			}
			writer.close();
		} 
		
		catch (IOException e) {
			System.out.println("File Error!");
			e.printStackTrace();
		}
		
	}
}
