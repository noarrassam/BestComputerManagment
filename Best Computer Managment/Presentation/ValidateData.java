package Presentation;

import java.sql.Date;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

public class ValidateData {
	public static boolean isPresent(JTextComponent c, String title) {
		if (c.getText().length() == 0) {
			showMessage(c, title + " is a required field.\nPlease re-enter. ");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}
		
	public static boolean isInt(JTextComponent c, String title) {
		try {
			int d = Integer.parseInt(c.getText());
			return true;
		} catch (NumberFormatException e) {
			showMessage(c, title + " must be valid number.\n Please re-enter. ");
			c.requestFocusInWindow();
			return false;
		}
	}
	
	public static boolean isDate(JDateChooser c, String title) {
		try {
			if(
				((JTextField)c.getDateEditor().getUiComponent()).getText().isEmpty()) 
				{
					showMessage(c, title + " is Invalid" );
				    return false;
				} 
					
			return true;

		} catch (NumberFormatException e) {
			c.requestFocusInWindow();
			return false;
		}
	}

	public static boolean isChecked(JCheckBox c, String title) {
		try {
			c.setSelected(true);
			return true;
		} catch (NumberFormatException e) {
			showMessageCheckBox(c, title + " must be valid number.\n Please re-enter. ");
			c.requestFocusInWindow();
			return false;
		}
	}
	
	private static void showMessage(JDateChooser c, String message) {

		JOptionPane.showMessageDialog(c, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
	}

	private static void showMessage(JTextField c, char[] message) {

		JOptionPane.showMessageDialog(c, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
	}

	private static void showMessage(JTextComponent c, String message) {
		JOptionPane.showMessageDialog(c, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
	}

	private static void showMessageCheckBox(JCheckBox c, String message) {
		JOptionPane.showMessageDialog(c, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
	}

}