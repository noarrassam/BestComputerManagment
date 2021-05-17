package Presentation;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class Validator {

	public static boolean isperesnt(JTextComponent c, String title) {
		if (c.getText().length() == 0) {
			showMessage(c, title + " is a required field.\n Please re-enter.");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}

	public static boolean isInteger(JTextComponent c, String title) {
		try {
			int i = Integer.parseInt(c.getText());
			return true;
		} catch (NumberFormatException e) {
			showMessage(c, title + " is a required field.\n Please re-enter.");
			c.requestFocusInWindow();
			return false;

		}
	}

	public static boolean isDouble(JTextComponent c, String title) {
		try {
			double d = Double.parseDouble(c.getText());
			return true;
		} catch (NumberFormatException e) {
			showMessage(c, title + " is a required field.\n Please re-enter.");
			c.requestFocusInWindow();
			return false;

		}
	}

	private static void showMessage(JTextComponent c, String message) {
		JOptionPane.showMessageDialog(c, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
	}

}
