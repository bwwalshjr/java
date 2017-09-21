import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ListOfNumbers {

    private List<Integer> list;
    private static final int SIZE = 10;

    public ListOfNumbers () {
        list = new ArrayList<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++) {//change SIZE to 5 to generate IndexOB exception
            list.add(new Integer(i));
        }
    }

    public void writeList() {
        PrintWriter out = null;

        try {
            System.out.println("Entering" + " try statement");

            out = new PrintWriter(new FileWriter("OutFile.txt")); //change "OutFile.txt" to "https://stackoverflow.com/questions/13216148/java-what-throws-an-ioexception" to generate IOException
            for (int i = 0; i < SIZE; i++) {
                out.println("Value at: " + i + " = " + list.get(i));
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IndexOutOfBoundsException: "
                               +  e.getMessage());
                                     
        } catch (IOException e) {
            System.err.println("Caught IOException: " +  e.getMessage());
                                     
        } finally {
            if (out != null) {
                System.out.println("Closing PrintWriter");
                out.close();
            } 
            else {
                System.out.println("PrintWriter not open");
            }
        }
    }
}