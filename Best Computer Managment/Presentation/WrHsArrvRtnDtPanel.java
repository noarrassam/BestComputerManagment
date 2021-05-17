package Presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class WrHsArrvRtnDtPanel extends JPanel {
	private JTable table;
	private JLabel lblCode;
	private JTextField txtScrch;
	private JButton btnReturn, btnCancel;
	private JComboBox jcArvRtn;

	public void Initialize() {
		String[] columnName = { "Product", "Quantity", "Arrival/Returning Date", "Status" };
		Object[][] data = { { "Motherboard", "3", "2020-02-10", "Arrived" }, { "CPU", "7", "2020-05-12", "Pending" },
				{ "RAM [Memory]", "8", "2020-01-15", "Returned" } };
		String[] AddRtn = { "Arrival Date", "Returning Date" };

		table = new JTable(data, columnName);
		table.setPreferredScrollableViewportSize(new Dimension(700, 50));

		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);

		lblCode = new JLabel("Enter Item Code:");

		txtScrch = new JTextField();
		txtScrch.setPreferredSize(new Dimension(100, 20));

		btnReturn = new JButton("Track");
		btnCancel = new JButton("Cancel");
		lblCode.setFont(new Font("Serif", Font.BOLD, 15));
		lblCode.setForeground(Color.blue);

		jcArvRtn = new JComboBox(AddRtn);

		this.setBackground(Color.ORANGE);

		JPanel jpcomboBox = new JPanel();
//		jpcomboBox.setLayout(new GridLayout(3, 3));
		jpcomboBox.setLayout(new FlowLayout());
		jpcomboBox.add(new JLabel("Select"));
		jpcomboBox.add(jcArvRtn);
		this.add(jpcomboBox, BorderLayout.NORTH);

		this.add(scrollPane);
		this.add(lblCode);
		this.add(txtScrch);

		this.add(btnReturn);
		this.add(btnCancel);
//		this.add(jcArvRtn);
//		jcArvRtn.setLayout(new GridLayout(3,3));
//		jcArvRtn.add(new JLabel("Select Message"));
//		this.add(jcArvRtn);
//		this.add(jcArvRtn, BorderLayout.NORTH);

	}

	public WrHsArrvRtnDtPanel() {
		Initialize();
		btnReturn.addActionListener(new TrkButtonHandeler());
		btnCancel.addActionListener(new CancelButtonHandeler());
	}

	private class TrkButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int code = Integer.parseInt(txtScrch.getText());
			String result = "Code: " + code;
			JOptionPane.showMessageDialog(null, result + "\n Item Return is Scheduled on Time", "Found",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private class CancelButtonHandeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Pending", "Cancel", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}