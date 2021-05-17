package Presentation;

import javax.swing.*;

public class HelperMessagePanel extends JPanel {

	/** The message to be displayed */

	private String message = "Welcome to Java";

	// constructor1
	public HelperMessagePanel() {

	}

	/** Construct a message panel with a specified message */
	// constructor2
	public HelperMessagePanel(String message) {
		this.message = message;
	}

	/** Return message */
	public String getMessage() {
		return message;
	}

	/** Set a new message */
	public void setMessage(String message) {
		this.message = message;
		repaint();
	}
}
