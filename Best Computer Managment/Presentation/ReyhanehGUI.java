package Presentation;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Business.COrder;
import Data.DBAccess;
import net.proteanit.sql.DbUtils;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReyhanehGUI extends JInternalFrame {

	private JTable table;
	private JComboBox comboitemname, combosearchtype;
	private JList<String> list;

	// private DBAccess db = null;
	DBAccess db = new DBAccess();

	// launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReyhanehGUI frame = new ReyhanehGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// declare connection
	Connection connection = db.bdConnector();

	// Connection connection ;
	private JTextField txtorderid;
	private JTextField txtserialnum;
	private JTextField txtquantity;
	private JTextField txtsearch;
	private JTextField txtitemid;

	// update the table automatically
	public void refreshTable() {

		try {
			db.Refreshtabledb(table);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// read combobox from db
	public void fillComboBox() {

		try {
			db.loadcomboNamedb(comboitemname);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// load the list when we run the app
	public void loadlist() {

		try {
			db.loadListdb(list);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// create my own exception
	private void showException(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
	}

	// create the frame
	public ReyhanehGUI() {
		getContentPane().setBackground(new Color(255, 250, 240));

		setTitle(" Best Computer DB Application Management");
		setSize(1000, 800);
		getContentPane().setLayout(null);

		JButton btnLoadTb = new JButton("Load the table from DB");
		btnLoadTb.setFont(new Font("Bell MT", Font.BOLD, 14));
		btnLoadTb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					db.Loadtable(table);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnLoadTb.setBounds(529, 454, 186, 53);
		getContentPane().add(btnLoadTb);

		JScrollPane scrollPane = new JScrollPane();

		// click on rows in table by mouse
		scrollPane.addMouseListener(new MouseAdapter() {
		});

		scrollPane.setBounds(403, 145, 541, 293);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

				COrder order = null;
				try {
					order = db.ProductnameSearch(a);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtserialnum.setText(order.getpSerialnumber());
				txtorderid.setText(String.valueOf(order.getpOrderid()));
				txtquantity.setText(String.valueOf(order.getpQuantity()));
				comboitemname.setSelectedItem(order.getpProductName());
				txtitemid.setText(String.valueOf(order.getpItemid()));

			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.WHITE);
		table.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(table);

		JLabel lblorderid = new JLabel("Order ID");
		lblorderid.setFont(new Font("Bell MT", Font.BOLD, 14));
		lblorderid.setBounds(35, 172, 82, 13);
		getContentPane().add(lblorderid);

		JLabel lblName = new JLabel("Serial Number");
		lblName.setFont(new Font("Bell MT", Font.BOLD, 14));
		lblName.setBounds(35, 307, 106, 13);
		getContentPane().add(lblName);

		JLabel lblSurname = new JLabel("Quantity");
		lblSurname.setFont(new Font("Bell MT", Font.BOLD, 14));
		lblSurname.setBounds(35, 377, 82, 13);
		getContentPane().add(lblSurname);

		txtorderid = new JTextField();
		txtorderid.setFont(new Font("Arial", Font.BOLD, 13));
		txtorderid.setBounds(35, 188, 120, 29);
		getContentPane().add(txtorderid);
		txtorderid.setColumns(10);

		txtserialnum = new JTextField();
		txtserialnum.setFont(new Font("Arial", Font.BOLD, 13));
		txtserialnum.setBounds(35, 322, 120, 30);
		getContentPane().add(txtserialnum);
		txtserialnum.setColumns(10);

		txtquantity = new JTextField();
		txtquantity.setFont(new Font("Arial", Font.BOLD, 13));
		txtquantity.setBounds(35, 400, 120, 29);
		getContentPane().add(txtquantity);
		txtquantity.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Bell MT", Font.BOLD, 14));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validator.isInteger(txtorderid, "Order ID"))
					if (Validator.isInteger(txtitemid, "Item ID"))
						if (Validator.isperesnt(txtserialnum, "Serial Number"))
							if (Validator.isperesnt(txtquantity, "Quantity")) {

								String pSerialnumber = txtserialnum.getText();
								int pOrderid = Integer.parseInt(txtorderid.getText());
								int pItemid = Integer.parseInt(txtitemid.getText());
								int pQuantity = Integer.parseInt(txtquantity.getText());

								COrder order = new COrder(pSerialnumber, pOrderid, pItemid, pQuantity);
								try {
									db.addOrderItem(order);
								} catch (SQLException e1) {

									showException(e1);
								}
							}
				refreshTable();
			}
		});
		btnSave.setBounds(59, 461, 96, 38);
		getContentPane().add(btnSave);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Bell MT", Font.BOLD, 14));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validator.isInteger(txtorderid, "Order ID"))
					if (Validator.isInteger(txtitemid, "Item ID"))
						if (Validator.isperesnt(txtserialnum, "Serial Number"))
							if (Validator.isperesnt(txtquantity, "Quantity")) {

								String pSerialnumber = txtserialnum.getText();
								int pOrderid = Integer.parseInt(txtorderid.getText());
								int pItemid = Integer.parseInt(txtitemid.getText());
								int pQuantity = Integer.parseInt(txtquantity.getText());

								COrder order = new COrder(pSerialnumber, pOrderid, pItemid, pQuantity);

								try {
									db.UpdateOrderItem(order);
								} catch (SQLException e1) {

									showException(e1);
								}
							}
				refreshTable();
			}
		});
		btnUpdate.setBounds(179, 463, 96, 35);
		getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Bell MT", Font.BOLD, 14));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validator.isInteger(txtorderid, "Order ID"))
					if (Validator.isInteger(txtitemid, "Item ID"))
						if (Validator.isperesnt(txtserialnum, "Serial Number"))
							if (Validator.isperesnt(txtquantity, "Quantity")) {

								String pSerialnumber = txtserialnum.getText();
								int pOrderid = Integer.parseInt(txtorderid.getText());
								int pItemid = Integer.parseInt(txtitemid.getText());
								int pQuantity = Integer.parseInt(txtquantity.getText());

								COrder order = new COrder(pSerialnumber, pOrderid, pItemid, pQuantity);

								try {
									db.DeleteOrderItem(order);
								} catch (SQLException e1) {
									showException(e1);

								}
								refreshTable();
							}
			}
		});

		btnDelete.setBounds(59, 547, 96, 35);
		getContentPane().add(btnDelete);

		comboitemname = new JComboBox();
		comboitemname.setFont(new Font("Bell MT", Font.BOLD, 13));
		comboitemname.addActionListener(new ActionListener() {

			// get selected item
			public void actionPerformed(ActionEvent e) {

				try {
					db.ProductnameSearch(comboitemname, txtserialnum);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		comboitemname.setBounds(35, 78, 301, 38);
		getContentPane().add(comboitemname);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(185, 211, 151, 220);
		getContentPane().add(scrollPane_1);

		list = new JList();
		scrollPane_1.setViewportView(list);
		list.setFont(new Font("Arial", Font.BOLD, 13));

		txtsearch = new JTextField();
		txtsearch.setFont(new Font("Arial", Font.BOLD, 13));

		// search data in database
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {
					db.SearOrderItem(combosearchtype, txtsearch, table);

				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		txtsearch.setBounds(619, 74, 205, 29);
		getContentPane().add(txtsearch);
		txtsearch.setColumns(10);

		JLabel lblEnterAnyValue = new JLabel("Select a value to search:");
		lblEnterAnyValue.setFont(new Font("Bell MT", Font.BOLD, 15));
		lblEnterAnyValue.setBounds(403, 36, 186, 28);
		getContentPane().add(lblEnterAnyValue);

		combosearchtype = new JComboBox();
		combosearchtype.setFont(new Font("Bell MT", Font.BOLD, 13));
		combosearchtype.setModel(new DefaultComboBoxModel(new String[] { "OrderID", "ProductName" }));
		combosearchtype.setBounds(403, 75, 120, 29);
		getContentPane().add(combosearchtype);

		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setFont(new Font("Bell MT", Font.BOLD, 14));
		lblItemId.setBounds(35, 237, 71, 13);
		getContentPane().add(lblItemId);

		txtitemid = new JTextField();
		txtitemid.setFont(new Font("Arial", Font.BOLD, 13));
		txtitemid.setBounds(35, 250, 120, 32);
		getContentPane().add(txtitemid);
		txtitemid.setColumns(10);

		JButton btnCancle = new JButton("Cancle");
		btnCancle.setFont(new Font("Bell MT", Font.BOLD, 14));
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		btnCancle.setBounds(179, 545, 96, 38);
		getContentPane().add(btnCancle);

		JLabel lblReservedOrderNumbers = new JLabel("Reserved order numbers:");
		lblReservedOrderNumbers.setFont(new Font("Bell MT", Font.BOLD, 13));
		lblReservedOrderNumbers.setBounds(185, 172, 151, 29);
		getContentPane().add(lblReservedOrderNumbers);

		refreshTable();
		fillComboBox();
		loadlist();
	}
}
