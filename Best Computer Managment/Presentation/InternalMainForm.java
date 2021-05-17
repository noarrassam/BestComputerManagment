package Presentation;

import javax.swing.*;

/*This Internal Frame*/
public class InternalMainForm extends JInternalFrame {
	private JPanel productpanel;

	public InternalMainForm() {

	}

	public InternalMainForm(JPanel productPanel) {
		this.productpanel = productPanel;
		this.add(productPanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// this.setTitle("Product Form");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(600, 600);
		this.setResizable(true);
		this.setVisible(true);
	}

	public void intialize() {
	}
}
