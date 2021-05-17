package Presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WrHsAddRtnPanel extends JPanel {
	private JLabel lblGoods, lblAdd, lblType, lblSize, lblQuantity, lblCode;
	private String[] AddRtn = { "Add Goods", "Return Goods" };
	private String[] type = { "Motherboard", "CPU", "GPU [Graphics Card]", "RAM [Memory]", "Power Supply Unit",
			"Cooling (CPU, Chassis)" };
	private String[] quantity = { "1", "2", "3", "4", "5", "6", "7", "8" };

	private JComboBox jcAddRtn;
	private JComboBox jcType;
	private JComboBox jcQnt;

	private JTextField txtType, txtQuantity, txtSize, txtCode;
	private JButton btnAddRtn, btnClear, btnCancel;

	public void Initialize() {
		lblGoods = new JLabel("Select Add/Return:");
		jcAddRtn = new JComboBox(AddRtn);

		lblType = new JLabel("Select Item:");
		jcType = new JComboBox(type);

		lblSize = new JLabel("Size:");
		lblQuantity = new JLabel("Select Quantity:");

		lblCode = new JLabel("Code:");
		jcQnt = new JComboBox(quantity);

//			txtType = new JTextField();

//			txtQuantity = new JTextField();
		txtSize = new JTextField();
		txtCode = new JTextField();

		btnAddRtn = new JButton("Confirm");

		btnAddRtn.setFont(new Font("Serif", Font.BOLD, 14));

		btnClear = new JButton("Clear");

		btnClear.setFont(new Font("Serif", Font.BOLD, 14));

		btnCancel = new JButton("Cancel");

		btnCancel.setFont(new Font("Serif", Font.BOLD, 14));

		this.setLayout(new GridLayout(6, 3));

		this.add(lblGoods);
		this.add(jcAddRtn);

		this.add(lblType);
		this.add(jcType);

//			this.add(lblSize);
//			this.add(txtSize);

		this.add(lblQuantity);
		this.add(jcQnt);

		this.add(lblCode);
		this.add(txtCode);

		this.add(btnAddRtn);
		this.add(btnCancel);
		this.add(btnClear);
	}

	public WrHsAddRtnPanel() {
		Initialize();
		btnAddRtn.addActionListener(new AddButtonHandeler());
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
			JOptionPane.showMessageDialog(null, result + "\n Goods were Added \n Check Tracking", "Found",
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
			JOptionPane.showMessageDialog(null, "Please Add New Item", "Cancel", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}