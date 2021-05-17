package Presentation;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import Business.Employee;
import Data.DAOFactory;

public class Validator1 {
	private static Data.EmployeeDAO empDAO1 = DAOFactory.getEmployeeDAO();
	static Employee emp = null;

	public static boolean isExistName(JTextComponent c, JTextComponent b, String title) {
		emp = empDAO1.getEmployee(c.getText());
		String firstName = c.getText();
		String lastName = b.getText();
		if (emp != null) {
			if ((emp.getfirstName().equalsIgnoreCase(firstName)) && 
					(emp.getlastName().equalsIgnoreCase(lastName))) {
				showMessage(c, firstName + " " + lastName + " is already exist\n pleae reenter a New Employee");
				// showMessage(c, title + " is a required filed\n pleae reenter");
				c.requestFocusInWindow();

				return false;
			}
		}
		return true;

	}

	public static boolean isPresent(JTextComponent c, String title) {
		if (c.getText().length() == 0) {
			showMessage(c, title + " is a required filed\n pleae reenter");
			c.requestFocusInWindow();
			return false;
		}

		return true;

	}

	public static boolean isPassword(JTextComponent c, String title) {
		if (c.getText().length() < 6) {
			showMessage(c, title + " must be at least 6 characters \n pleae reenter");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}
	public static boolean isDate(JDateChooser d, String title) {
		if (d.getDate()==null) {
			//showMessage(null, title + " is a required filed\n pleae reenter");
			JOptionPane.showMessageDialog(d, title + " is a required filed\n pleae reenter", "invalid entry", JOptionPane.ERROR_MESSAGE);
			
			d.requestFocusInWindow();
			return false;
		}
		return true;
	}
	public static boolean isCheck(JRadioButton r, JRadioButton r1, String title) {
		if (!r.isSelected() && !r1.isSelected()) {

			showMessage(null, title + " is a required filed\n pleae reenter");
			r.requestFocusInWindow();
			return false;
		}

		return true;

	}

	public static boolean isInteger(JTextComponent c, String title) {
		try {
			int i = Integer.parseInt(c.getText());
			if (i <= 0) {
				showMessage(c, title + " must be a positive integer\n pleae reenter");
				c.setText("");
				c.requestFocusInWindow();
				return false;
			} else {
				return true;
			}
		} catch (NumberFormatException e) {
			showMessage(c, title + " must be a positive integer\n pleae reenter");
			c.setText("");
			c.requestFocusInWindow();
			return false;
		}

	}

	public static boolean isDouble(JTextComponent c, JTextComponent s, String title) {
		try {
			double scoreObtained = Double.parseDouble(c.getText());
			double scoreMax = Double.parseDouble(s.getText());
			if (scoreObtained < 0 || scoreObtained > scoreMax || scoreMax > 100 || scoreMax == 0) {
				showMessage(c, title + " must be positive and less than scoreMax \n pleae reenter");
				return false;

			} else {

				return true;
			}
		} catch (NumberFormatException e) {
			showMessage(c, title + " must be double \n pleae reenter");
			c.requestFocusInWindow();
			return false;
		}

	}

	public static void showMessage(JTextComponent c, String message) {
		JOptionPane.showMessageDialog(c, message, "invalid entry", JOptionPane.ERROR_MESSAGE);
	}

}
