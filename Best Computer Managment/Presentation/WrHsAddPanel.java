package Presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WrHsAddPanel extends JPanel {
	private JLabel lblGoods, lblAdd, lblType, lblSize, lblQuantity, lblCode;
	private JTextField txtType, txtQuantity, txtSize, txtCode;
	private JButton btnAdd, btnClear, btnCancel;

	public void Initialize() {
		lblGoods = new JLabel("Goods");
		lblAdd = new JLabel("Add Goods");

		lblType = new JLabel("Type:");
		lblSize = new JLabel("Size:");
		lblQuantity = new JLabel("Quantity:");
		lblCode = new JLabel("Code:");

		txtType = new JTextField();
		txtQuantity = new JTextField();
		txtSize = new JTextField();
		txtCode = new JTextField();

		btnAdd = new JButton("Add Item");
		btnClear = new JButton("Clear");
		btnCancel = new JButton("Cancel");

		this.setLayout(new GridLayout(7, 6));

		this.add(lblGoods);
		this.add(lblAdd);

		this.add(lblType);
		this.add(txtType);

		this.add(lblSize);
		this.add(txtSize);

		this.add(lblQuantity);
		this.add(txtQuantity);

		this.add(lblCode);
		this.add(txtCode);

		this.add(btnAdd);
		this.add(btnCancel);
		this.add(btnClear);
	}

	public WrHsAddPanel() {
		Initialize();
		btnAdd.addActionListener(new AddButtonHandeler());
		btnClear.addActionListener(new ClearButtonHandeler());
		btnCancel.addActionListener(new CancelButtonHandeler());
	}

	private class AddButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String type = txtType.getText();
			double size = Double.parseDouble(txtSize.getText());
			int quantity = Integer.parseInt(txtQuantity.getText());
			int code = Integer.parseInt(txtCode.getText());
			String result = "Type: " + type + "Size: " + size + "Quantity: " + quantity + "Code:" + code;
			JOptionPane.showMessageDialog(null, result + "\n Products were Added \n Check Tracking", "Found",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private class ClearButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String type = txtType.getText();
			double size = Double.parseDouble(txtSize.getText());
			int quantity = Integer.parseInt(txtQuantity.getText());
			int code = Integer.parseInt(txtCode.getText());
			String result = "Type: " + type + "Size: " + size + "Quantity: " + quantity + "Code:" + code;
			JOptionPane.showMessageDialog(null, result + "\n Clear Information", "Found",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private class CancelButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Please Add New Products", "Cancel", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}