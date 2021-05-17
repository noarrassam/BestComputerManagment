package Presentation;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

public class ValidatorAdnan {

	public static boolean isperesnt(JTextComponent c, String title) {
		if (c.getText().length() == 0) {
			showMessage(c, title + " is a required field.\n Please re-enter.");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	
	
	public static boolean isDouble(JTextComponent c, String title) {
		try {
			double discount = Double.parseDouble(c.getText());
			
			if (discount < 0 || discount > 70 ) {
				showMessage(c, title + " must be positive and less than 70% \n pleae reenter");
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
	
	

	public static boolean isCheck(JRadioButton r, JRadioButton r1, String title) {
		if (!r.isSelected() && !r1.isSelected()) {

			showMessage(null, title + " is a required filed\n pleae reenter");
			r.requestFocusInWindow();
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
