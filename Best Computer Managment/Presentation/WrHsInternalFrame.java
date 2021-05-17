package Presentation;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class WrHsInternalFrame extends JInternalFrame {
	private JPanel panel;

	public WrHsInternalFrame(JPanel panel) {
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Best Computer");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(720, 300);
		this.setResizable(true);

	}
}
