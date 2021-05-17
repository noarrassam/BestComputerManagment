package Presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WrHsRtnDtPanel extends JPanel {
	private JTable table;
	private JLabel lblCode;
	private JTextField txtScrch;
	private JButton btnReturn, btnCancel;

	public void Initialize() {
		String[] columnName = { "Product", "Size", "Color", "Returning Date", "Status" };
		Object[][] data = { { "Jeans", "10", "Blue", "2020-02-01", "Arrived" },
				{ "Shirt", "40", "White", "2020-05-05", "Pending" },
				{ "Dress", "30", "Pink", "2020-02-10", "Pending" } };

		table = new JTable(data, columnName);
		table.setPreferredScrollableViewportSize(new Dimension(700, 50));

		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);

		lblCode = new JLabel("Enter Product Code:");

		txtScrch = new JTextField();
		txtScrch.setPreferredSize(new Dimension(100, 20));

		btnReturn = new JButton("Track");
		btnCancel = new JButton("Cancel");
		lblCode.setFont(new Font("Serif", Font.BOLD, 15));
		lblCode.setForeground(Color.blue);

		this.setBackground(Color.ORANGE);

		this.add(scrollPane);
		this.add(lblCode);
		this.add(txtScrch);

		this.add(btnReturn);
		this.add(btnCancel);
	}

	public WrHsRtnDtPanel() {
		Initialize();
		btnReturn.addActionListener(new TrkButtonHandeler());
		btnCancel.addActionListener(new CancelButtonHandeler());
	}

	private class TrkButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int code = Integer.parseInt(txtScrch.getText());
			String result = "Code: " + code;
			JOptionPane.showMessageDialog(null, result + "\n Dress Return is Scheduled on Time", "Found",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private class CancelButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Pending", "Cancel", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}