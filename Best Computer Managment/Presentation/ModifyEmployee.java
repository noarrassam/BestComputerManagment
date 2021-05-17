package Presentation;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Business.Employee;
import Data.DAOFactory;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;

public class ModifyEmployee extends JInternalFrame {
	private static Data.EmployeeDAO empDAO1 = DAOFactory.getEmployeeDAO();
    static JTextField txtFirstName;
	private static JTextField txtLastName;
	private static JTextField txtJobTitle;
	private static JTextField txtPostalCode;
	private static JTextField txtAddress;
	private static JTextField txtPhone;
	private static JTextField txtEmail;
	private static JTextField txtEmployeeNumber;
	private static JTextField txtUserName;
	private static JRadioButton rdMale;
	private static JRadioButton rdFemal;
	static JDateChooser dateChooser;
	private static JPasswordField txtpassword1;
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyEmployee frame = new ModifyEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModifyEmployee() {

		this.initialize();
		this.show();
		setTitle("Employee Edit Form");

	}

	public void initialize() {

		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(

				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),

				"Employee Information", TitledBorder.LEADING, TitledBorder.TOP, null,

				UIManager.getColor("CheckBoxMenuItem.selectionBackground")));
		panel.setBounds(10, 22, 431, 423);
		getContentPane().add(panel);

		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(138, 64, 171, 25);
		panel.add(txtFirstName);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(138, 95, 171, 25);
		panel.add(txtLastName);

		txtJobTitle = new JTextField();
		txtJobTitle.setColumns(10);
		txtJobTitle.setBounds(138, 126, 171, 25);
		panel.add(txtJobTitle);

		txtPostalCode = new JTextField();
		txtPostalCode.setColumns(10);
		txtPostalCode.setBounds(138, 214, 171, 25);
		panel.add(txtPostalCode);

		JLabel label = new JLabel("First Name");
		label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label.setBounds(10, 66, 95, 25);
		panel.add(label);

		JLabel label_1 = new JLabel("Last Name");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_1.setBounds(10, 97, 95, 25);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Job Title");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_2.setBounds(10, 128, 79, 25);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Date Of Birth");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_3.setBounds(10, 159, 125, 25);
		panel.add(label_3);

		rdMale = new JRadioButton("Male");
		rdMale.setFont(new Font("Times New Roman", Font.BOLD, 16));
		rdMale.setBounds(190, 304, 109, 25);
		panel.add(rdMale);

		rdFemal = new JRadioButton("Femal");
		rdFemal.setFont(new Font("Times New Roman", Font.BOLD, 16));
		rdFemal.setBounds(4, 304, 131, 25);
		panel.add(rdFemal);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(138, 183, 171, 25);
		panel.add(txtAddress);

		JLabel label_4 = new JLabel("Address");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_4.setBounds(10, 185, 84, 25);
		panel.add(label_4);

		JLabel label_5 = new JLabel("Postal Code");
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_5.setBounds(10, 217, 125, 25);
		panel.add(label_5);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(138, 245, 171, 25);
		panel.add(txtPhone);

		JLabel label_6 = new JLabel("Phone");
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_6.setBounds(10, 244, 120, 25);
		panel.add(label_6);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(138, 273, 171, 25);
		panel.add(txtEmail);

		JLabel label_7 = new JLabel("Email");
		label_7.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_7.setBounds(10, 280, 120, 25);
		panel.add(label_7);

		txtEmployeeNumber = new JTextField();
		txtEmployeeNumber.setEnabled(false);
		txtEmployeeNumber.setColumns(10);
		txtEmployeeNumber.setBounds(138, 33, 171, 25);
		panel.add(txtEmployeeNumber);

		JLabel label_8 = new JLabel("Employee Id");
		label_8.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_8.setBounds(10, 33, 95, 25);
		panel.add(label_8);

		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(138, 335, 171, 25);
		panel.add(txtUserName);

		JLabel lblPassword = new JLabel("PassWord");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPassword.setBounds(10, 370, 120, 25);
		panel.add(lblPassword);

		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsername.setBounds(10, 334, 120, 25);
		panel.add(lblUsername);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(138, 154, 171, 25);
		panel.add(dateChooser);
		
		txtpassword1 = new JPasswordField();
		txtpassword1.setBounds(138, 371, 171, 25);
		panel.add(txtpassword1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 446, 441, 79);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(

				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),

				"Operations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		// This button for edit
		JButton btnFind = new JButton("Edit");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Employee> employees = empDAO1.getEmployees();
				
				if (isValidData()) {
				//	int num1 = Integer.parseInt((txtEmployeeNumber.getText()));
				String firstName = txtFirstName.getText();
				String lastName = txtLastName.getText();
				String jobTitle = txtJobTitle.getText();
			//	String dateOfBirth = txtDateOfBirth.getText();
				Date date1=dateChooser.getDate();
				String dateOfBirth = new SimpleDateFormat("dd-MMM-yyyy").format(date1); 
				String address = txtAddress.getText();
				String postalCode = txtPostalCode.getText();
				String phone = txtPhone.getText();
				String email = txtEmail.getText();
				String userName = txtUserName.getText();
				String password = txtpassword1.getText();
				// for validation
				
					for (Employee e3 : employees) {
						if (txtEmployeeNumber.getText().length() > 0) {
							int num1 = Integer.parseInt((txtEmployeeNumber.getText()));
							if (num1 == e3.getemployeeNumber()) {
								// if ((txtFirstName.getText().equalsIgnoreCase(e3.getfirstName()))) {
								int agree1 = JOptionPane
										.showConfirmDialog(null,
												"Are you sure you want to update the " + e3.getfirstName() + " "
														+ e3.getlastName(),
												"Update Confirmation", JOptionPane.YES_NO_OPTION);
								if (agree1 == 0) {
									int num = e3.getemployeeNumber();
									e3.setEmployeeNumber(num);
									e3.setFirstName(firstName);
									e3.setLastName(lastName);
									e3.setJobTitle(jobTitle);
									e3.setDateOfBirth(dateOfBirth);
									e3.setAddress(address);
									e3.setPostalCode(postalCode);
									e3.setPhone(phone);
									e3.setEmail(email);
									String gender = "Femal";
									if (rdMale.isSelected())
										gender = "Male";
									else if (rdFemal.isSelected())
										gender = "Femal";
									e3.setGender(gender);
									e3.setuserName(userName);
									e3.setPassword(password);
									EmployeeDisplay.clearTable();
									EmployeeDisplay.AddRowToTable(new Object[] { e3.getemployeeNumber(),
											e3.getfirstName(), e3.getlastName(),e3.getjobTitle(), e3.getdateOfBirth(), e3.getaddress(),
											e3.getpostalCode(), e3.getphone(), e3.getemail(), e3.getgender(),
											e3.getuserName(), e3.getpasword(), });
									JOptionPane.showMessageDialog(null, e3.getfirstName() + " record is updated",
											"Updat Employee Record", JOptionPane.INFORMATION_MESSAGE);
									empDAO1.updateEmployee(employees);
								}

							}
						} else {
							JOptionPane.showMessageDialog(null, " The Employee is not Exit", "Updat Employee Record",
									JOptionPane.INFORMATION_MESSAGE);
							empDAO1.updateEmployee(employees);
							break;
						}
					}
				}

			}
		});
		btnFind.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnFind.setBounds(100, 18, 80, 30);
		panel_1.add(btnFind);
//this button for delete
		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Employee> employees = empDAO1.getEmployees();
			//	if (isValidData()) {
					for (Employee e2 : employees) {
						if ((txtFirstName.getText().equalsIgnoreCase(e2.getfirstName()))) {
							// employees.iterator().next();

							int agree1 = JOptionPane.showConfirmDialog(
									null, "Are you sure you want to delete the " + e2.getfirstName() + " "
											+ e2.getlastName() + " Record",
									"Delete Confirmation", JOptionPane.YES_NO_OPTION);
							if (agree1 == 0) {
								empDAO1.removeEmployee(employees, e2);

								JOptionPane.showMessageDialog(null, "  Employee " + e2.getfirstName() + " is Deleted",
										"Delete Employee", JOptionPane.INFORMATION_MESSAGE);
								EmployeeDisplay.RemoveRowToTable();
								clearTextBoxes();
								break;
							}
						}

					}
				}
			//}

		});
		button_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_2.setBounds(201, 17, 80, 30);
		panel_1.add(button_2);

		JButton button_4 = new JButton("Close");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		button_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_4.setBounds(291, 17, 98, 30);
		panel_1.add(button_4);

		JButton button_5 = new JButton("Find");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Pease,Enter Employee Name");
				//ArrayList<Employee> employees = empDAO1.getEmployees();
				Employee e2= empDAO1.getEmployee(name);
			//	boolean found = false;
				//for (Employee e2 : employees) {
				//	if ((name.equalsIgnoreCase(e2.getfirstName()))) {
				if (e2!=null) {
						int num = e2.getemployeeNumber();
						txtEmployeeNumber.setText(String.valueOf(num));
						txtFirstName.setText(e2.getfirstName());
						txtLastName.setText(e2.getlastName());
						txtJobTitle.setText(e2.getjobTitle());
					//	txtDateOfBirth.setText(e2.getdateOfBirth());
						String date2=e2.getdateOfBirth();
						Date dateOfBirth1;
						try {
							dateOfBirth1 = new SimpleDateFormat("dd-MMM-yyyy").parse(date2);
							dateChooser.setDate(dateOfBirth1);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						txtAddress.setText(e2.getaddress());
						txtPostalCode.setText(e2.getpostalCode());
						txtPhone.setText(e2.getphone());
						txtEmail.setText(e2.getemail());
						if (e2.getgender().equals("Male"))
							rdMale.setSelected(true);
						else if (e2.getgender().equals("Femal"))
							rdFemal.setSelected(true);
						txtUserName.setText(e2.getuserName());
						txtpassword1.setText(e2.getpasword());
						//txtpassword1.setText(e2.getpasword());
						EmployeeDisplay.clearTable();
						EmployeeDisplay.AddRowToTable(new Object[] { e2.getemployeeNumber(), e2.getfirstName(),
								e2.getlastName(),e2.getjobTitle(), e2.getdateOfBirth(), e2.getaddress(), e2.getpostalCode(),
								e2.getphone(), e2.getemail(), e2.getgender(), e2.getuserName(), e2.getpasword(), });
					//	found = true;
				//	}
				//}
				}else {
					JOptionPane.showMessageDialog(null, name + " is Not Exist", "Find Employee",
							JOptionPane.INFORMATION_MESSAGE);
					EmployeeDisplay.clearTable();
					clearTextBoxes();
				}
			}
		});
		button_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_5.setBounds(10, 17, 80, 30);
		panel_1.add(button_5);

	}

	public boolean isValidData() {
	//	if(!Validator1.isInteger(txtEmployeeNumber,"EmployeeNumber"))
		//return false;
		if (!Validator1.isPresent(txtFirstName, "First Name"))
			return false;
		if (!Validator1.isPresent(txtLastName, "Last Name"))
			return false;
		if (!Validator1.isPresent(txtJobTitle, "JobTitle"))
			return false;
		//if (!ValidatorAdnan.isPresent(txtDateOfBirth, "DateOfBirth"))
		//	return false;
		
		if (!Validator1.isDate(dateChooser, "DateOfBirth"))
			return false;
		
		if (!Validator1.isPresent(txtAddress, "Address"))
			return false;
		if (!Validator1.isPresent(txtPostalCode, "PostalCode "))
			return false;
		if (!Validator1.isPresent(txtPhone, "Phone"))
			return false;
		if (!Validator1.isPresent(txtEmail, "Email"))
			return false;
		if ((!Validator1.isCheck(rdMale, rdFemal, "Gender")))
			return false;

		if (!Validator1.isPresent(txtUserName, "UserName "))
			return false;
		if (!Validator1.isPresent(txtpassword1, "password"))
			return false;
		// if (!Validator.isDouble(txtScoreObtained, txtMaxScore, "Score Obtained"))
		// return false;
		// if(!Validator.isDouble(txtMaxScore," Max Score ")) return false;
		return true;
	}

	public void clearTextBoxes() {
		txtEmployeeNumber.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtJobTitle.setText("");
	//	txtDateOfBirth.setText("");
		dateChooser.setDate(null);
		txtAddress.setText("");
		txtPostalCode.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
		rdMale.setSelected(false);
		txtUserName.setText("");
		txtpassword1.setText("");
	}
	public static void fillTextModifyEmployee1(String name) {
		Employee e2= empDAO1.getEmployee(name);
		//	boolean found = false;
			//for (Employee e2 : employees) {
			//	if ((name.equalsIgnoreCase(e2.getfirstName()))) {
			if (e2!=null) {
					int num = e2.getemployeeNumber();
				
					txtEmployeeNumber.setText(String.valueOf(num));
					txtFirstName.setText(e2.getfirstName());
					txtLastName.setText(e2.getlastName());
					txtJobTitle.setText(e2.getjobTitle());
				//	txtDateOfBirth.setText(e2.getdateOfBirth());
					String date2=e2.getdateOfBirth();
					Date dateOfBirth1;
					try {
						dateOfBirth1 = new SimpleDateFormat("dd-MMM-yyyy").parse(date2);
						dateChooser.setDate(dateOfBirth1);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					txtAddress.setText(e2.getaddress());
					txtPostalCode.setText(e2.getpostalCode());
					txtPhone.setText(e2.getphone());
					txtEmail.setText(e2.getemail());
					if (e2.getgender().equals("Male"))
						rdMale.setSelected(true);
					else if (e2.getgender().equals("Femal"))
						rdFemal.setSelected(true);
					txtUserName.setText(e2.getuserName());	
					txtpassword1.setText(e2.getpasword());
		
	}
		
	}
	public static void fillTextModifyEmployee(String EmployeeNumber,String FirstName,
			String LastName,String JobTitle, Date date2,String address,
			String postalCode,String phone,String email,String username) {
		txtEmployeeNumber.setText(EmployeeNumber);
		txtFirstName.setText(FirstName);
		txtLastName.setText(LastName);
		txtJobTitle.setText(JobTitle);
		dateChooser.setDate(date2);
		txtAddress.setText(address);
		txtPostalCode.setText(postalCode);
		txtPhone.setText(phone);
		txtEmail.setText(email); 
		txtUserName.setText(username);
	}
}
