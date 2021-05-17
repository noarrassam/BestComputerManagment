package Presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.prefs.Preferences;

import javax.naming.directory.SearchResult;
import javax.swing.*;
import Business.RgstrEmp;
import Data.DAOFactory;
import Data.DAOFactoryRg;
import Data.RgstrDAO;
//import Presentation.WrHsRgstrPanel;

public class WrHsLognPanel extends JFrame 
{
	private RgstrDAO rDAO = DAOFactoryRg.getRgstDAO();
	private Data.EmployeeDAO empDAO1 = DAOFactory.getEmployeeDAO();
	private JLabel lblUsrName, lblPass, lblLogn, lblEmpty, lblEmpty1, lblRgstr, lblEmp, lblEmpty2, lblTest;
	private JTextField txtUsrName, txtPass;
	private JButton btnLogin, btnCancel;
	private JCheckBox jchkRmember;
	private static Scanner x, y;
	private JRadioButton gGuest, gEmp;
	private JPasswordField passField;
	private JPanel srch;
	private JPanel content;
	private JPanel addRtn;
	private JComboBox jcRgstr;
	private CardLayout card;
	private JLabel l1;

	public WrHsLognPanel() 
	{
		Initialize();
		btnLogin.addActionListener(new LogButtonHandeler());
		btnCancel.addActionListener(new CancelButtonHandeler());
	}

	public void Initialize() 
	{	
		
//		setLayout(new BorderLayout());
//		setContentPane(new JLabel(new ImageIcon("BestComputer.png")));
//		setLayout(new FlowLayout());
//		l1 = new JLabel();
//		add(l1);
//		setSize(200, 200);
		
		lblEmpty = new JLabel("");
		lblLogn = new JLabel("Login Now!");

		lblUsrName = new JLabel("Username:");
		lblPass = new JLabel("Password:");

		txtUsrName = new JTextField();
		// txtPass = new JTextField();
		passField = new JPasswordField(15);

		btnLogin = new JButton("Login");
		btnCancel = new JButton("Cancel");
		// jchkRmember = new JCheckBox("Remember me");

		lblEmpty1 = new JLabel("");
		
		gGuest = new JRadioButton("Login as a Intern");
		gEmp = new JRadioButton("Login as a Employee");
		gGuest.setSelected(true);
		
		ButtonGroup group = new ButtonGroup();
		group.add(gGuest);
		group.add(gEmp);
		gGuest.setMnemonic('T');
		gEmp.setMnemonic('E');
		lblRgstr = new JLabel("<HTML><U>Create a Login Account</U></HTML");
		lblRgstr.setForeground(Color.BLUE.darker());
//		lblRgstr.setDisplayedMnemonic('R');
		lblRgstr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						
		this.setLayout(new GridLayout(7,7));
		
		
//		setLayout(new BorderLayout());
//		setContentPane(new JLabel(new ImageIcon("BestComputer.png")));
//		setLayout(new FlowLayout());
//		l1 = new JLabel();
//		add(l1);
//		setSize(200, 200);
		
		this.add(lblLogn);
		this.add(lblEmpty);

		this.add(lblUsrName);
		this.add(txtUsrName);
		
		this.add(lblPass);
		// this.add(txtPass);
		this.add(passField);
		this.add(btnLogin);
		this.add(btnCancel);

		this.add(gGuest);
		this.add(lblEmpty1);
		this.add(gEmp);
		this.add(lblRgstr);
				
		lblLogn.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblUsrName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPass.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRgstr.setFont(new Font("Times New Roman", Font.BOLD, 15));
		gGuest.setFont(new Font("Times New Roman", Font.BOLD, 13));
		gEmp.setFont(new Font("Times New Roman", Font.BOLD, 13));
									
//		JPanel jpcomboBox = new JPanel();
//		jpcomboBox.setLayout(new FlowLayout());
//		jpcomboBox.add(lblRgstr);
//		jpcomboBox.add(jcRgstr);
//		this.add(jpcomboBox, BorderLayout.NORTH);
				
//		jcRgstr.setSelectedIndex(0);

//		jcRgstr.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				if(e.getSource() == jcRgstr)
//				{
//					JComboBox jcombo = (JComboBox)e.getSource();
//					String msg = (String)jcombo.getSelectedItem();
//					switch(msg)
//					{
//					case "As an Intern":
//						dispose();
//						WrHsRgstrPanel rgstr = new WrHsRgstrPanel();
//						rgstr.setSize(720, 300);
//						rgstr.setVisible(true);
//						break;
//					case "As an Employee":
//						dispose();
//						EmployeeDisplay rgstrEmp = new EmployeeDisplay();
//						rgstrEmp.setSize(1200, 600);
//						rgstrEmp.setVisible(true);						
//						break;
//					default: lblTest.setText("Woops");									
//					}
//				}
//			}
//		});
		
		passField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = " ";
				char[] password = passField.getPassword();
				for (int x = 0; x < password.length; x++) {
					pass += password[x];
				}
				JOptionPane.showMessageDialog(null, pass);
			}
		});	
		
		lblRgstr.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                   if(e.getSource() == lblRgstr) {
                		dispose();
            			WrHsRgstrPanel rgstr = new WrHsRgstrPanel();
            			rgstr.setSize(720, 300);
             			rgstr.setVisible(true);
             			rgstr.setLocation(300, 100);
                   }
               }
           });
	}

	private class LogButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (isValidData()) 
			{
				String pass = new String(passField.getPassword());

				boolean found = false;

				String tempUserName = "";
				String tempPass = "";

				String tempUserNameEmp = "";
				String tempPassEmp = "";

				try {
					x = new Scanner(new File("Register.txt"));
					y = new Scanner(new File("Employees.txt"));

					while (x.hasNext()&& !found) 
					{
						tempUserName = x.next();
						tempPass = x.next();

						if (tempUserName.equals(txtUsrName.getText()) && tempPass.equals(pass) && gGuest.isSelected()) 
						{
							found = true;
							String result = "User Name: " + tempUserName + "Password: " + tempPass;
							JOptionPane.showMessageDialog(null, result + "\n Logged in Successfully", "Found", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
							MainForm mainFrame = new MainForm();							
							mainFrame.setSize(1300, 700);
							mainFrame.setVisible(true);
						}}
					while (y.hasNext()&& !found) 
					{
						tempUserNameEmp = y.next();
						tempPassEmp = y.next();
						if (tempUserNameEmp.equals(txtUsrName.getText()) && tempPassEmp.equals(pass) && gEmp.isSelected()) 
						{
							found = true;
							String result = "User Name: " + tempUserNameEmp + "Password: " + tempPassEmp;
							JOptionPane.showMessageDialog(null, result + "\n Logged in Successfully", "Found", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							MainForm mainFrame = new MainForm();
							mainFrame.setSize(1300, 700);
							mainFrame.setVisible(true);
						}
					}
				} 
				catch (IOException ioe) 
				{
					ioe.printStackTrace();
					System.err.println("Error");
				}

				finally 
				{
					if (tempUserName != txtUsrName.getText() && tempPassEmp != pass && !found) 
					{
						JOptionPane.showMessageDialog(null, "\n Invalid Username or Password ", "Not Found",
								JOptionPane.INFORMATION_MESSAGE);
					}
					x.close();
				}
			}
		}
	}

	private class CancelButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Login Cancelled\nPress Ok to Exit", "Cancel", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	public boolean isValidData() 
	{
		if (!ValidateData.isPresent(txtUsrName, "User Name "))
			return false;
		if (!ValidateData.isPresent(passField, "Password "))
			return false;
//		if(!ValidateData.isChecked(jchkRmember, "Password ")) return false;
		return true;
	}

//	public boolean isChecked() 
//	{
//		if (!ValidateData.isChecked(jchkRmember, "User Name "))
//			return false;
//		return true;
//	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//new MainForm().setVisible(true);
		WrHsLognPanel inFm=new WrHsLognPanel();
		inFm.setSize(720, 300);
		inFm.setVisible(true);
		//MainForm.this.desktop.add(inFm);
		inFm.setLocation(300, 100);
		inFm.setTitle("Best Computer LogIn Form");
		inFm.toFront();		

	}
}