package Presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Business.Employee;
import Data.DAOFactory;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;

public class EmployeeDisplay extends JInternalFrame {
	// Instnce Variable
	private Data.EmployeeDAO empDAO1 = DAOFactory.getEmployeeDAO();
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtJobTitle;
	private JTextField txtPostalCode;
	private JPasswordField txtpassword1;
	private JTextField txtAddress;
	private JTextField txtUserName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static JTable employeeTable;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JRadioButton rdFemal;
	private JRadioButton rdMale;
	private int employeeNumber = 0;
	private JTextField textEmployeeNumber;
	JDateChooser dateChooser;
	//ModifyEmployee form=new ModifyEmployee();
	ModifyEmployee form=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDisplay frame = new EmployeeDisplay();
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

	public EmployeeDisplay() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		setTitle("Best Computer Employees Form");
		setNormalBounds(new Rectangle(0, 0, 1300, 600));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 901, 499);
		getContentPane().setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(43, 23, 332, 421);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Employee Information", TitledBorder.LEADING, TitledBorder.TOP, null,
				UIManager.getColor("CheckBoxMenuItem.selectionBackground")));
		getContentPane().add(panel_1);
//Components
		txtFirstName = new JTextField();
		txtFirstName.setBounds(138, 64, 171, 25);
		panel_1.add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(138, 95, 171, 25);
		panel_1.add(txtLastName);
		txtLastName.setColumns(10);

		txtJobTitle = new JTextField();
		txtJobTitle.setBounds(138, 126, 171, 25);
		panel_1.add(txtJobTitle);
		txtJobTitle.setColumns(10);

		txtPostalCode = new JTextField();
		txtPostalCode.setBounds(138, 214, 171, 25);
		panel_1.add(txtPostalCode);
		txtPostalCode.setColumns(10);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 66, 95, 25);
		panel_1.add(lblNewLabel);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLastName.setBounds(10, 97, 95, 25);
		panel_1.add(lblLastName);

		JLabel lblJobTitle = new JLabel("Job Title");
		lblJobTitle.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblJobTitle.setBounds(10, 128, 79, 25);
		panel_1.add(lblJobTitle);

		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDateOfBirth.setBounds(10, 159, 125, 25);
		panel_1.add(lblDateOfBirth);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 367, 107, 25);
		panel_1.add(lblNewLabel_1);

		txtpassword1 = new JPasswordField();
		txtpassword1.setBounds(140, 367, 171, 25);
		panel_1.add(txtpassword1);

		rdMale = new JRadioButton("Male");
		rdMale.setFont(new Font("Times New Roman", Font.BOLD, 16));
		rdMale.setBounds(190, 304, 109, 25);
		buttonGroup.add(rdMale);
		panel_1.add(rdMale);
		rdFemal = new JRadioButton("Femal");
		rdFemal.setFont(new Font("Times New Roman", Font.BOLD, 16));
		rdFemal.setBounds(6, 304, 131, 25);
		buttonGroup.add(rdFemal);
		panel_1.add(rdFemal);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(138, 183, 171, 25);
		panel_1.add(txtAddress);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAddress.setBounds(10, 185, 84, 25);
		panel_1.add(lblAddress);

		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(140, 336, 171, 25);
		panel_1.add(txtUserName);

		JLabel lblUser = new JLabel("User Name");
		lblUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUser.setBounds(10, 338, 107, 25);
		panel_1.add(lblUser);

		JLabel lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPostalCode.setBounds(10, 217, 125, 25);
		panel_1.add(lblPostalCode);
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(138, 245, 171, 25);
		panel_1.add(txtPhone);
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPhone.setBounds(10, 244, 120, 25);
		panel_1.add(lblPhone);
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(138, 273, 171, 25);
		panel_1.add(txtEmail);
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEmail.setBounds(10, 275, 120, 25);
		panel_1.add(lblEmail);

		textEmployeeNumber = new JTextField();
		textEmployeeNumber.setEnabled(false);
		textEmployeeNumber.setBounds(138, 33, 171, 25);
		panel_1.add(textEmployeeNumber);
		textEmployeeNumber.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Employee Id");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 33, 95, 25);
		panel_1.add(lblNewLabel_2);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(138, 154, 171, 25);
		panel_1.add(dateChooser);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(404, 31, 769, 413);
		getContentPane().add(scrollPane);
//Table to employee Display
		
		employeeTable = new JTable();
		employeeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				SelectRowToTable();
			}
		});
		employeeTable.setSurrendersFocusOnKeystroke(true);
		employeeTable.setCellSelectionEnabled(true);
		employeeTable.setColumnSelectionAllowed(true);
		employeeTable.setFont(new Font("Times New Roman", Font.BOLD, 14));
		scrollPane.setViewportView(employeeTable);
		employeeTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "First Name", "Last Name",
				"Job Title", "DOB", "Address", "Zip Code", "Phone", "Email", "Gender", "UserName" }));
		employeeTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		employeeTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		employeeTable.getColumnModel().getColumn(2).setPreferredWidth(130);
		employeeTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		employeeTable.getColumnModel().getColumn(4).setPreferredWidth(160);
		employeeTable.getColumnModel().getColumn(5).setPreferredWidth(140);
		employeeTable.getColumnModel().getColumn(6).setPreferredWidth(55);
		employeeTable.getColumnModel().getColumn(8).setPreferredWidth(100);
		employeeTable.getColumnModel().getColumn(8).setPreferredWidth(140);
		employeeTable.getColumnModel().getColumn(9).setPreferredWidth(75);
		employeeTable.getColumnModel().getColumn(10).setPreferredWidth(120);
		JTableHeader head = employeeTable.getTableHeader();
		head.setFont(new Font("Times New Roman", Font.BOLD, 16));

		employeeTable.setBackground(Color.white);
		employeeTable.setForeground(Color.black);
		Font font = new Font("", 1, 16);
		employeeTable.setFont(font);
		employeeTable.setRowHeight(25);

		JPanel panel_2 = new JPanel();
		scrollPane.setRowHeaderView(panel_2);
		panel_2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(43, 446, 1130, 78);
		getContentPane().add(panel);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Operations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
//This button or Save Employee
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(30, 32, 150, 30);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// This Methods for validation
				ArrayList<Employee> employees = empDAO1.getEmployees();
				int num = 1;
				for (Employee e2 : employees) {
					num = e2.getemployeeNumber();
				}
				num++;
				textEmployeeNumber.setText(String.valueOf(num));
				if (isValidData()) {

					// String employeeNumbe=textEmployeeNumber.getText();
					String firstName = txtFirstName.getText();
					String lastName = txtLastName.getText();
					String jobTitle = txtJobTitle.getText();
					//String dateOfBirth = txtDateOfBirth.getText();
					Date date1=dateChooser.getDate();
					String dateOfBirth = new SimpleDateFormat("dd-MMM-yyyy").format(date1);  
					String address = txtAddress.getText();
					String postalCode = txtPostalCode.getText();
					String phone = txtPhone.getText();
					String email = txtEmail.getText();
					String gender = "Femal";
					if (rdMale.isSelected())
						gender = "Male";
					else if (rdFemal.isSelected())
						gender = "Femal";
					String userName = txtUserName.getText();
					// @SuppressWarnings("deprecation")
					String password = txtpassword1.getText();

					// Create New Object(Employee) and Save this Employee
					Employee employee = new Employee(num, firstName, lastName, jobTitle, dateOfBirth, address,
							postalCode, phone, email, gender, userName, password);
					if (empDAO1.addEmployee(employee)) {
						JOptionPane.showMessageDialog(null,
								employee.getfirstName() + "  " + employee.getlastName() + " is saved", "New Employee",
								JOptionPane.INFORMATION_MESSAGE);
						// These Commands to clear Text Boxes after addition
						clearTextBoxes();
						// These command to show only the saved current Employee
						AddRowToTable(new Object[] { employee.getemployeeNumber(), employee.getfirstName(),
								employee.getlastName(), employee.getjobTitle(), employee.getdateOfBirth(),
								employee.getaddress(), employee.getpostalCode(), employee.getphone(),
								employee.getemail(), employee.getgender(), employee.getuserName(),
								employee.getpasword(), });
					}
				}
			}
		});
		panel.setLayout(null);
		// buttonGroup.add(btnNewButton_1);
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(btnAdd);
//This Button for Modify
		JButton btnModify = new JButton("Modify");
		btnModify.setBounds(220, 33, 150, 30);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				MainForm.formModifyEmployee();
				
			 form = new ModifyEmployee();
				MainForm.desktop.add(form, Integer.valueOf(5));
				form.setSize(400, 600);

				form.requestFocus(false);

				form.setIconifiable(true);
				form.setLocation(50, 10);
				form.setClosable(true);
				form.moveToFront();
				BasicInternalFrameUI ui = (BasicInternalFrameUI) form.getUI();
				for (MouseListener listner : ui.getNorthPane().getMouseListeners()) {
					ui.getNorthPane().removeMouseListener(listner);
				}
				
			}
		});
		btnModify.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(btnModify);
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextBoxes();
				clearTable();
			}
		});
		btnReset.setBounds(610, 32, 150, 30);
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(btnReset);

		JButton btnDisplay = new JButton("Dsiplay");
		btnDisplay.setBounds(800, 32, 150, 30);
		btnDisplay.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				ArrayList<Employee> employees = empDAO1.getEmployees();
				for (Employee emp1 : employees) {
					AddRowToTable(new Object[] { emp1.getemployeeNumber(), emp1.getfirstName(), emp1.getlastName(),
							emp1.getjobTitle(), emp1.getdateOfBirth(), emp1.getaddress(), emp1.getpostalCode(),
							emp1.getphone(), emp1.getemail(), emp1.getgender(), emp1.getuserName(),
							emp1.getpasword(), });
				}
			}
		});
		panel.add(btnDisplay);
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				clearTextBoxes();
			}
		});
		btnClose.setBounds(980, 32, 140, 30);
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(btnClose);
//This button for Find
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextBoxes();
				String name = JOptionPane.showInputDialog(null, "Pease,Enter Employee Name");
				Employee e2 = empDAO1.getEmployee(name);
			//	boolean found = false;
				// for (Employee e2 : employees) {
				// if ((name.equals(e2.getfirstName()))) {
				if (e2!=null) {
				int num = e2.getemployeeNumber();
				textEmployeeNumber.setText(String.valueOf(num));
				txtFirstName.setText(e2.getfirstName());
				txtLastName.setText(e2.getlastName());
				txtJobTitle.setText(e2.getjobTitle());
				//txtDateOfBirth.setText(e2.getdateOfBirth());
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
				clearTable();
				AddRowToTable(new Object[] { e2.getemployeeNumber(), e2.getfirstName(), e2.getlastName(),
						e2.getjobTitle(), e2.getdateOfBirth(), e2.getaddress(), e2.getpostalCode(), e2.getphone(),
						e2.getemail(), e2.getgender(), e2.getuserName(), e2.getpasword(), });
			//	found = true;
				}else {
				
					JOptionPane.showMessageDialog(null, name + " is Not Exist", "Find Employee",
							JOptionPane.INFORMATION_MESSAGE);
					clearTable();
				}

			}
		});
		btnFind.setBounds(440, 32, 150, 30);
		btnFind.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(btnFind);
	}

//This Method for validate 
	public boolean isValidData() {

		if (!Validator1.isPresent(txtFirstName, "First Name")) {
			// if (!Validator1.isPresent1(txtLastName, "Last Name"))
			return false;
		}
		if (!Validator1.isPresent(txtLastName, "Last Name"))
			return false;
		if (!Validator1.isExistName(txtFirstName, txtLastName,"First Name")) {
			return false;
		}

		if (!Validator1.isPresent(txtJobTitle, "JobTitle"))
			return false;
	//	if (!Validator1.isPresent(txtDateOfBirth, "DateOfBirth"))
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
		if (!Validator1.isPassword(txtpassword1, "password"))
			return false;
		// if (!Validator.isDouble(txtScoreObtained, txtMaxScore, "Score Obtained"))
		// return false;
		// if(!Validator.isDouble(txtMaxScore," Max Score ")) return false;
		return true;
	}

//This Static Method populate the table
	public static void AddRowToTable(Object[] datarow) {

		DefaultTableModel model1 = (DefaultTableModel) employeeTable.getModel();

		model1.addRow(datarow);
	}

//This method to clear the table
	public static void clearTable() {
		employeeTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "First Name", "Last Name",
				"Job Title", "DOB", "Address", "Zip Code", "Phone", "Email", "Gender", "UserName" }));
		JTableHeader head = employeeTable.getTableHeader();
		head.setFont(new Font("Times New Roman", Font.BOLD, 16));
		employeeTable.setBackground(Color.white);
		employeeTable.setForeground(Color.black);
		Font font = new Font("", 1, 16);
		employeeTable.setFont(font);
		employeeTable.setRowHeight(25);
		employeeTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		employeeTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		employeeTable.getColumnModel().getColumn(2).setPreferredWidth(130);
		employeeTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		employeeTable.getColumnModel().getColumn(4).setPreferredWidth(160);
		employeeTable.getColumnModel().getColumn(5).setPreferredWidth(140);
		employeeTable.getColumnModel().getColumn(6).setPreferredWidth(55);
		employeeTable.getColumnModel().getColumn(8).setPreferredWidth(100);
		employeeTable.getColumnModel().getColumn(8).setPreferredWidth(140);
		employeeTable.getColumnModel().getColumn(9).setPreferredWidth(75);
		employeeTable.getColumnModel().getColumn(10).setPreferredWidth(120);
	}

//This Table to delete rows from table
	public static void RemoveRowToTable() {
		// int i=Integer.parseInt((String) object);
		DefaultTableModel model1 = (DefaultTableModel) employeeTable.getModel();
		 int i = employeeTable.getSelectedRow();
		 if (i >= 0)
		model1.removeRow(i);
		// else
		// JOptionPane.showMessageDialog(null, "no row");
	}

	public void SelectRowToTable() {
		// int i=Integer.parseInt((String) object);
		DefaultTableModel model1 = (DefaultTableModel) employeeTable.getModel();
		int i = employeeTable.getSelectedRow();
		if (i >= 0) {
			String name=model1.getValueAt(i, 1).toString();
			//textEmployeeNumber.setText(model1.getValueAt(i, 0).toString());
			//txtFirstName.setText(model1.getValueAt(i, 1).toString());
			//txtLastName.setText(model1.getValueAt(i, 2).toString());
			//txtJobTitle.setText(model1.getValueAt(i, 3).toString());
		//	txtDateOfBirth.setText(model1.getValueAt(i, 4).toString());
			//String date2=model1.getValueAt(i, 4).toString();
		//	Date dateOfBirth1;
			//try {
		//		dateOfBirth1 = new SimpleDateFormat("dd-MMM-yyyy").parse(date2);
			//	dateChooser.setDate(dateOfBirth1);
		//	} catch (ParseException e1) {
				// TODO Auto-generated catch block
			//	e1.printStackTrace();
		//	}
		//	txtAddress.setText(model1.getValueAt(i, 5).toString());
		//	txtPostalCode.setText(model1.getValueAt(i, 6).toString());
		//	txtPhone.setText(model1.getValueAt(i, 7).toString());
		//	txtEmail.setText(model1.getValueAt(i, 8).toString());
		//	String gender = model1.getValueAt(i, 9).toString();

			//if (gender.equals("Male"))
			//	rdMale.setSelected(true);
			//else if (gender.equals("Femal"))
			//	rdFemal.setSelected(true);

		//	txtUserName.setText(model1.getValueAt(i, 10).toString());
			// txtpassword1.setText(model1.getValueAt(i, 11).toString());
			/*ModifyEmployee.setFirstName(textEmployeeNumber.getText(),txtFirstName.getText(),
					txtLastName.getText(),txtJobTitle.getText(),dateChooser.getDate(), 
					txtAddress.getText(),txtPostalCode.getText(),txtPhone.getText(),
					txtEmail.getText(),txtUserName.getText());*/
		//	ModifyEmployee f=new ModifyEmployee();
		///	if (ModifyEmployee.component != null)
			//ModifyEmployee.setFirstName(model1.getValueAt(i, 1).toString());
			//ModifyEmployee.fillTextModifyEmployee1(model1.getValueAt(i, 1).toString()); 
			
			Employee e2= empDAO1.getEmployee(name);
			//	boolean found = false;
				//for (Employee e2 : employees) {
				//	if ((name.equalsIgnoreCase(e2.getfirstName()))) {
				if (e2!=null) {
						int num = e2.getemployeeNumber();
					
						textEmployeeNumber.setText(String.valueOf(num));
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
			
			final JInternalFrame[] frames = MainForm.desktop.getAllFrames();

			if( Arrays.asList(frames).contains(form)) {
			ModifyEmployee.fillTextModifyEmployee1(model1.getValueAt(i, 1).toString());
			//	ModifyEmployee.fillTextModifyEmployee(textEmployeeNumber.getText(),txtFirstName.getText(),
				//txtLastName.getText(),txtJobTitle.getText(),dateChooser.getDate(), 
				//txtAddress.getText(),txtPostalCode.getText(),txtPhone.getText(),
				//txtEmail.getText(),txtUserName.getText());
			
			}
		}
		// model1.removeRow(0);
	
	
	}

	public void clearTextBoxes() {
		textEmployeeNumber.setText("");
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

//this method to find Employee
	public void findEmployee(String s) {
		ArrayList<Employee> employees = empDAO1.getEmployees();
		// DefaultTableModel model1 = (DefaultTableModel) employeeTable.getModel();
		// employeeTable.setModel(null);
		// MainForm.formEmployeeFind();
		for (Employee e2 : employees) {
			if ((s.equals(e2.getfirstName()))) {
				int num = e2.getemployeeNumber();
				JOptionPane.showMessageDialog(null, "  Employee " + e2.getfirstName() + " is Deleted",
						"Delete Employee", JOptionPane.INFORMATION_MESSAGE);
				textEmployeeNumber.setText(String.valueOf(num));
				txtFirstName.setText(e2.getfirstName());
				txtLastName.setText(e2.getlastName());
				txtJobTitle.setText(e2.getjobTitle());
				//txtDateOfBirth.setText(e2.getdateOfBirth());
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
				employeeTable.setModel(
						new DefaultTableModel(new Object[][] {}, new String[] { "Id", "First Name", "Last Name",
								"Job Title", "DOB", "Address", "Zip Code", "Phone", "Email", "Gender", "UserName" }));
				AddRowToTable(new Object[] { e2.getemployeeNumber(), e2.getfirstName(), e2.getlastName(),
						e2.getjobTitle(), e2.getdateOfBirth(), e2.getaddress(), e2.getpostalCode(), e2.getphone(),
						e2.getemail(), e2.getgender(), e2.getuserName(), e2.getpasword(), });
			}
		}
	}
}
