package Presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import Presentation.WrHsLognPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Business.RgstrEmp;
import Data.DAOFactory;
import Data.DAOFactoryRg;
import Data.RgstrDAO;

public class WrHsRgstrPanel extends JFrame // JInternalFrame
{
	private RgstrDAO rDAO = DAOFactoryRg.getRgstDAO();
	private JLabel lblFrstNm, lblLstNm, lblUsrName, lblPass, lblPhoneNum, lblRgstr, lblGender, lblEmpty, lblEmpty1;
	private JTextField txtUsrName, txtFname, txtLname, txtPhoneNum;
	private JButton btnRgstr, btnCancel;
	private JRadioButton gMale, gFemale;
	private JLayeredPane layeredPan;
	private JPanel contentPan;
	private JPanel login;
	private CardLayout cardlayout;
	// private String[] msg = {"Login", "SignUp"};
	// private JComboBox comb;
	private JPasswordField passField;
	private static Scanner x;

	public WrHsRgstrPanel() {
		Initialize();
		btnRgstr.addActionListener(new RgstrButtonHandeler());
		btnCancel.addActionListener(new CancelButtonHandeler());
	}

	public void Initialize() {
		this.setLayout(new GridLayout(9, 5));

		lblRgstr = new JLabel("Register Now!");
		lblEmpty = new JLabel("");

		lblFrstNm = new JLabel("First Name:");
		lblLstNm = new JLabel("Last Name:");

		lblUsrName = new JLabel("Username:");
		lblPass = new JLabel("Password:");

		lblGender = new JLabel("Gender:");
		lblEmpty1 = new JLabel("");

		txtFname = new JTextField();
		txtLname = new JTextField();

		txtUsrName = new JTextField();
		// passField = new JTextField();
		passField = new JPasswordField(15);

		lblPhoneNum = new JLabel("Phone Number:");
		txtPhoneNum = new JTextField();

		gMale = new JRadioButton("Male");
		gFemale = new JRadioButton("Female");

		btnRgstr = new JButton("Register");
		btnCancel = new JButton("Cancel");

		this.add(lblRgstr);
		this.add(lblEmpty);

		this.add(lblFrstNm);
		this.add(txtFname);

		this.add(lblLstNm);
		this.add(txtLname);

		this.add(lblUsrName);
		this.add(txtUsrName);

		this.add(lblPass);
		this.add(passField);
		// this.add(txtPass);

		this.add(lblPhoneNum);
		this.add(txtPhoneNum);

		this.add(lblGender);
		this.add(lblEmpty1);
		this.add(gMale);
		this.add(lblEmpty1);
		this.add(gFemale);
		
		lblRgstr.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblUsrName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFrstNm.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLstNm.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPass.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPhoneNum.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 15));
		gMale.setFont(new Font("Times New Roman", Font.BOLD, 13));
		gFemale.setFont(new Font("Times New Roman", Font.BOLD, 13));

		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(gMale);
		btnGrp.add(gFemale);
		gMale.setMnemonic('M');
		gFemale.setMnemonic('F');
		gMale.setSelected(true);
		this.add(btnRgstr);
		this.add(btnCancel);

		gMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Gender is Male");
			}
		});

		gFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Gender is Female");
			}
		});

		passField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = " ";
				char[] password = passField.getPassword();
				for (int x = 0; x < password.length; x++) {
					pass += password[x];
				}
				JOptionPane.showMessageDialog(null, pass);
//					 System.out.println("Field=" + passField.getPassword());
//					 JPasswordField txtField = (JPasswordField) e.getSource();
//					 char[] password = txtField.getPassword();
			}
		});
	}

	private class RgstrButtonHandeler implements ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (isValidData()) {
				String firstName = txtFname.getText();
				String lastName = txtLname.getText();
				String userName = txtUsrName.getText();
				// char[] pass = passField.getPassword();
				String pass = new String(passField.getPassword());
				// String pass = passField.getText();
				int phnNum = Integer.parseInt(txtPhoneNum.getText());

				String gender = "Male";
				if (gFemale.isSelected())
					gender = "Female";

				RgstrEmp rgstr = new RgstrEmp();
				rgstr.setFirstName(firstName);
				rgstr.setLastName(lastName);
				rgstr.setUserName(userName);
				rgstr.setPass(pass);
				rgstr.setPhoneNum(phnNum);
				rgstr.setGender(gender);

				boolean found = false;

				String tempUserName = "";
				int tempPhonN;

				try {
					x = new Scanner(new File("Register.txt"));
					// y = new Scanner(new File("Register.txt"));
					
					while (x.hasNext() && !found) 
					{
						tempUserName = x.next();
						if (tempUserName.equals(txtUsrName.getText())) 
						{
							found = true;
							JOptionPane.showMessageDialog(null, "\n Username is Already Exist ", "Found",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					if (tempUserName != txtUsrName.getText() && !found) {
						rDAO.addRgstr(rgstr);
						String result = " First Name: " + firstName + " Last Name: " + lastName + " User Name: "
								+ userName + " Password: " + pass + " Phone Number: " + phnNum + " Gender: " + gender;
						JOptionPane.showMessageDialog(null, result + "\n Registration has Completed", "Now Login",
								JOptionPane.INFORMATION_MESSAGE);

						dispose();
						WrHsLognPanel rgstr1 = new WrHsLognPanel();
						rgstr1.setSize(720, 300);
			 			rgstr1.setVisible(true);
			 			rgstr1.setTitle("Best Computer LogIn Form");
			 			rgstr1.setLocation(300, 100);
					}

				} catch (IOException ioe) {
					ioe.printStackTrace();
					System.err.println("Error");
				}

				finally {
					x.close();
					//System.out.println(!found);
				}
			}
		}
	}

	private class CancelButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Registration Cancelled\nPress Ok to Exit", "Cancel",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
			WrHsLognPanel rgstr = new WrHsLognPanel();
			rgstr.setSize(720, 300);
 			rgstr.setVisible(true);
 			rgstr.setLocation(300, 100);
		}
	}

	public void SwitchPanels(JPanel panel) {
		layeredPan.removeAll();
		layeredPan.add(panel);
		layeredPan.repaint();
		layeredPan.revalidate();
		layeredPan.setSize(600, 600);
		layeredPan.setVisible(true);
	}

	public boolean isValidData() {
		if (!ValidateData.isPresent(txtFname, "First Name "))
			return false;

		if (!ValidateData.isPresent(txtLname, "Last Name "))
			return false;

		if (!ValidateData.isPresent(txtUsrName, "UserName "))
			return false;

		// if(!ValidateData.isPresent(passField,"Password ")) return false;

		if (!ValidateData.isPresent(passField, "Password "))
			return false;

		if (!ValidateData.isPresent(txtPhoneNum, "Pnone Number "))
			return false;

		if (!ValidateData.isInt(txtPhoneNum, "Phone Number "))
			return false;

		return true;
	}

}