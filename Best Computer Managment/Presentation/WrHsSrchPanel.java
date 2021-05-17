package Presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WrHsSrchPanel extends JPanel {
	private JLabel lblGoods, lblAisle, lblAvblGoods, lblType, lblSize, lblQuantity;
	private JTextField txtType, txtQuantity, txtSize, txtAisle;

	private String[] type = { "Motherboard", "CPU", "GPU [Graphics Card]", "RAM [Memory]", "Power Supply Unit",
			"Cooling (CPU, Chassis)" };
	private String[] quantity = { "1", "2", "3", "4", "5", "6", "7", "8" };
	private String[] aisle = { "Aisle A", "Aisle B", "Aisle C", "Aisle D", "Aisle E", "Aisle F" };

	private JComboBox jcType;
	private JComboBox jcQnt;
	private JComboBox aisleSection;

	private JButton btnSearch, btnCancel;

	public void Initialize() {
		lblGoods = new JLabel("Goods");
		lblAvblGoods = new JLabel("Available Goods");
		lblType = new JLabel("Select Item:");
		lblSize = new JLabel("Size:");
		lblQuantity = new JLabel("Select Quantity:");
		lblAisle = new JLabel("Select Aisle:");

//		txtType = new JTextField();
		jcType = new JComboBox(type);
//		txtQuantity = new JTextField();
		jcQnt = new JComboBox(quantity);
//		txtSize = new JTextField();
//		txtAisle = new JTextField();
		aisleSection = new JComboBox(aisle);

		btnSearch = new JButton("Search");
		btnCancel = new JButton("Cancel");

		this.setLayout(new GridLayout(5, 2));

		this.add(lblGoods);
		this.add(lblAvblGoods);

		this.add(lblType);
		this.add(jcType);

//		this.add(lblSize);
//		this.add(txtSize);

		this.add(lblQuantity);
		this.add(jcQnt);

		this.add(lblAisle);
		this.add(aisleSection);

		this.add(btnSearch);
		this.add(btnCancel);
	}

	public WrHsSrchPanel() {
		Initialize();
		btnSearch.addActionListener(new SearchButtonHandeler());
		btnCancel.addActionListener(new CancelButtonHandeler());
	}

	private class SearchButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String type = txtType.getText();
			double size = Double.parseDouble(txtSize.getText());
			int quantity = Integer.parseInt(txtQuantity.getText());
			String aisle = txtAisle.getText();
			String result = "Type: " + type + "Size: " + size + "Quantity: " + quantity + "Aisle: " + aisle;
			JOptionPane.showMessageDialog(null, result + "\n 3 Seaech was Found", "Found",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private class CancelButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Search was not Found", "Cancel", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
