import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GUI {

	private JFrame frmInventoryManagementSystem;
	private JTextField txtFileName;
	private JTextField txtSearchName;
	private JTextField txtDecrementUPC;
	private JTextField txtDiscontinueName;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					GUI window = new GUI();
					window.frmInventoryManagementSystem.setVisible(true);
				} //try bracket
				
				catch (Exception e) {
					e.printStackTrace();
				}//catch bracket
			}//run method bracket
		});//Runnable bracket
	}//main bracket

	/**
	 * Create the application.
	 */
	public GUI() {
		Inventory inventoryList = new Inventory();
		initialize(inventoryList);
	}

	/** 
	 * Initialize the contents of the frame.
	 */
	private void initialize(Inventory inventoryList) {
		frmInventoryManagementSystem = new JFrame();
		frmInventoryManagementSystem.setTitle("Inventory Management System");
		frmInventoryManagementSystem.setBounds(100, 100, 1000, 600);
		frmInventoryManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInventoryManagementSystem.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 11, 46, 14);
		frmInventoryManagementSystem.getContentPane().add(label);
		
		//upload inventory button
		JButton btnUploadInventory = new JButton("Upload");
		
		btnUploadInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryList.uploadInventory(txtFileName.getText());
			}
		});
		
		btnUploadInventory.setBounds(10, 11, 128, 23);
		frmInventoryManagementSystem.getContentPane().add(btnUploadInventory);
		btnUploadInventory.setFocusPainted(false);
		btnUploadInventory.setContentAreaFilled(false);
		
		//inventory upload text field
		txtFileName = new JTextField();
		txtFileName.setToolTipText("Enter file name");
		txtFileName.setBounds(140, 11, 114, 23);
		frmInventoryManagementSystem.getContentPane().add(txtFileName);
		txtFileName.setColumns(10);
		
		//print by name button
		JButton btnAlphabeticalPrint= new JButton("Print List Alphabetically");
		
		btnAlphabeticalPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryList.printByName();
			}
		});
		
		btnAlphabeticalPrint.setBounds(10, 36, 244, 23);
		frmInventoryManagementSystem.getContentPane().add(btnAlphabeticalPrint);
		btnAlphabeticalPrint.setFocusPainted(false);
		btnAlphabeticalPrint.setContentAreaFilled(false);
		
		//print by expiration button
		JButton btnExpirationPrint = new JButton("Print List by Expiration");
		
		btnExpirationPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryList.printByExpiration();
			}
		});
		
		btnExpirationPrint.setBounds(10, 61, 244, 23);
		frmInventoryManagementSystem.getContentPane().add(btnExpirationPrint);
		btnExpirationPrint.setFocusPainted(false);
		btnExpirationPrint.setContentAreaFilled(false);
		
		//search for item button
		JButton btnSearch = new JButton("Search");
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
      		  inventoryList.searchForItem(txtSearchName.getText());
			}
		});
		
		btnSearch.setBounds(10, 86, 128, 23);
		frmInventoryManagementSystem.getContentPane().add(btnSearch);
		btnSearch.setFocusPainted(false);
		btnSearch.setContentAreaFilled(false);
		
		//search for item text field
		txtSearchName = new JTextField();
		txtSearchName.setToolTipText("Enter item name");
		txtSearchName.setBounds(140, 86, 114, 23);
		frmInventoryManagementSystem.getContentPane().add(txtSearchName);
		txtSearchName.setColumns(10);
		
		//decrement button
		JButton btnDecrement = new JButton("Decrement");
		
		btnDecrement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryList.decrementItem(txtDecrementUPC.getText());
			}
		});
		
		btnDecrement.setBounds(10, 111, 128, 23);
		frmInventoryManagementSystem.getContentPane().add(btnDecrement);
		btnDecrement.setFocusPainted(false);
		btnDecrement.setContentAreaFilled(false);
		
		//decrement text field
		txtDecrementUPC = new JTextField();
		txtDecrementUPC.setToolTipText("Enter item UPC");
		txtDecrementUPC.setBounds(140, 111, 114, 23);
		frmInventoryManagementSystem.getContentPane().add(txtDecrementUPC);
		txtDecrementUPC.setColumns(10);
		
		//discontinue item button
		JButton btnDiscontinue = new JButton("Discontinue");
		
		btnDiscontinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryList.discontinueItem(txtDiscontinueName.getText());
			}
		});
		
		btnDiscontinue.setBounds(10, 136, 128, 23);
		frmInventoryManagementSystem.getContentPane().add(btnDiscontinue);
		btnDiscontinue.setFocusPainted(false);
		btnDiscontinue.setContentAreaFilled(false);
		
		//discontinue item text field
		txtDiscontinueName = new JTextField();
		txtDiscontinueName.setToolTipText("Enter item name");
		txtDiscontinueName.setColumns(10);
		txtDiscontinueName.setBounds(140, 136, 114, 23);
		frmInventoryManagementSystem.getContentPane().add(txtDiscontinueName);
		
		//quit button
		JButton btnQuit = new JButton("Quit");
		
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryList.writeInventory();
				System.out.println("The program will now exit.");
				//System.exit(10);
			}
		});
		
		btnQuit.setBounds(10, 488, 244, 62);
		frmInventoryManagementSystem.getContentPane().add(btnQuit);
		btnQuit.setFocusPainted(false);
		btnQuit.setContentAreaFilled(false);
		
		//main display text area
		textArea = new JTextArea();
		textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		textArea.setBounds(263, 11, 711, 539);
		textArea.setEditable(false);
		//frmInventoryManagementSystem.getContentPane().add(editorPane);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(263, 11, 711, 539);
		frmInventoryManagementSystem.getContentPane().add(scrollPane);
		
		PrintStream out = new PrintStream(new TextAreaOutputStream(textArea));
		System.setOut(out);
		System.setErr(out);
		
		System.out.println("Welcome to the Foods B Us Inventory Management System.");
		
		inventoryList.uploadInventory();
		
		
		
	}
}
