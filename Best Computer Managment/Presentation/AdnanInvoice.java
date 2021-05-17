package Presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import Business.AdnanStoreInvoice;
import Business.AdnanStoreInvoiceDetails;
import Business.Employee;
import Data.AdnanInvoiceDBAccess;
import Data.AdnanInvoiceDetailesDBAccess;
import Data.Constants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AdnanInvoice extends JInternalFrame {
	// Instance Variable
	private JTextField txtItemPrice;
	private JTextField txtDiscount;
	private JTextField txtQuantity;
	private JComboBox comboItemCode;
	private JComboBox comItemName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static JTable itemTable;
	private JTextField txtTax;
	private JTextField txtTotalPrice;
	private JTextField txtNetPrice;
	private JTextField txtInvoiceId;
	private JTextField txtPrice;
	private JTextField textInvoicTax;
	private JTextField txtInvoiceTotalPrice;
	private JButton btnSaveInvoice;
	private JButton btnNewInvoice;
	private JLabel lblcurentDate = null;
	DefaultTableModel model = null;
	// variables for calculation purpose
	private double invoicePrice = 0;
	private double invoiceTax = 0;
	private double invoiceTotalPriace = 0;
	// AdnanInvoiceDBAccess db = new AdnanInvoiceDBAccess();
	AdnanInvoiceDetailesDBAccess db = new AdnanInvoiceDetailesDBAccess();
	ArrayList<AdnanStoreInvoiceDetails> itemsDetailes = new ArrayList<>();
	DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
	Date current = new Date();
	Calendar cal = Calendar.getInstance();
	private static DecimalFormat df = new DecimalFormat("0.0");

//this constructor
	public AdnanInvoice() {
		setTitle("Best Computer Invoice Form");
		setNormalBounds(new Rectangle(0, 0, 1300, 600));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 901, 499);
		getContentPane().setLayout(null);
		JPanel ItemPanel = new JPanel();
		ItemPanel.setBounds(20, 31, 344, 415);
		ItemPanel.setLayout(null);
		// This for Panel Information
		ItemPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Item Information", TitledBorder.LEADING, TitledBorder.TOP, null,
				UIManager.getColor("CheckBoxMenuItem.selectionBackground")));
		getContentPane().add(ItemPanel);
		txtItemPrice = new JTextField();
		txtItemPrice.setEnabled(false);
		txtItemPrice.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtItemPrice.setBounds(131, 91, 171, 25);
		ItemPanel.add(txtItemPrice);
		txtItemPrice.setColumns(10);
		txtDiscount = new JTextField();
		txtDiscount.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtDiscount.setBounds(131, 163, 171, 25);
		ItemPanel.add(txtDiscount);
		txtDiscount.setColumns(10);
		JLabel lblItemCode = new JLabel("Item Code");
		lblItemCode.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblItemCode.setBounds(23, 34, 95, 25);
		ItemPanel.add(lblItemCode);
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblItemName.setBounds(23, 65, 118, 25);
		ItemPanel.add(lblItemName);
		JLabel lblItemPrice = new JLabel("Item Price");
		lblItemPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblItemPrice.setBounds(23, 96, 79, 25);
		ItemPanel.add(lblItemPrice);
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(131, 127, 171, 25);
		ItemPanel.add(txtQuantity);
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblQuantity.setBounds(23, 132, 84, 25);
		ItemPanel.add(lblQuantity);
		JLabel lblDiscount = new JLabel("Discount(%)");
		lblDiscount.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDiscount.setBounds(23, 165, 125, 25);
		ItemPanel.add(lblDiscount);
		txtTax = new JTextField();
		txtTax.setEnabled(false);
		txtTax.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTax.setColumns(10);
		txtTax.setBounds(131, 239, 171, 25);
		ItemPanel.add(txtTax);
		JLabel lblItemTax = new JLabel("Tax ");
		lblItemTax.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblItemTax.setBounds(21, 241, 120, 25);
		ItemPanel.add(lblItemTax);
		txtTotalPrice = new JTextField();
		txtTotalPrice.setEnabled(false);
		txtTotalPrice.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtTotalPrice.setColumns(10);
		txtTotalPrice.setBounds(131, 275, 171, 25);
		ItemPanel.add(txtTotalPrice);
		JLabel lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTotalPrice.setBounds(21, 277, 120, 25);
		ItemPanel.add(lblTotalPrice);
		txtNetPrice = new JTextField();
		txtNetPrice.setEnabled(false);
		txtNetPrice.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtNetPrice.setColumns(10);
		txtNetPrice.setBounds(131, 199, 171, 25);
		ItemPanel.add(txtNetPrice);
		JLabel lblNetPrice = new JLabel("Net Price");
		lblNetPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNetPrice.setBounds(23, 204, 120, 25);
		ItemPanel.add(lblNetPrice);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(374, 143, 834, 258);
		getContentPane().add(scrollPane);
		itemTable = new JTable();
		// This method to select table row
		itemTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				SelectRowToTable();
			}
		});
		// These Lines for a Table
		itemTable.setSurrendersFocusOnKeystroke(true);
		itemTable.setCellSelectionEnabled(true);
		itemTable.setColumnSelectionAllowed(true);
		itemTable.setFont(new Font("Times New Roman", Font.BOLD, 14));
		scrollPane.setViewportView(itemTable);
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Item Name", "Unit Price", "Quantity",
				"Discount", "Net Price", "Tax", "Total" });
		itemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Item Name", "Unit Price",
				"Quantity", "Discount", "Net Price", "Tax", "Total" }));
		itemTable.getColumnModel().getColumn(0).setPreferredWidth(68);
		itemTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		itemTable.getColumnModel().getColumn(2).setPreferredWidth(61);
		itemTable.getColumnModel().getColumn(3).setPreferredWidth(56);
		itemTable.getColumnModel().getColumn(4).setPreferredWidth(58);
		itemTable.getColumnModel().getColumn(5).setPreferredWidth(56);
		itemTable.getColumnModel().getColumn(6).setPreferredWidth(55);
		itemTable.getColumnModel().getColumn(7).setPreferredWidth(90);
		JTableHeader head = itemTable.getTableHeader();
		head.setFont(new Font("Times New Roman", Font.BOLD, 16));
		itemTable.setBackground(Color.white);
		itemTable.setForeground(Color.black);
		Font font = new Font("", 1, 16);
		itemTable.setFont(font);
		itemTable.setRowHeight(25);
		// This method for delete
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// this to initialize the variables
				invoicePrice = Double.parseDouble(txtPrice.getText());
				invoiceTax = Double.parseDouble(textInvoicTax.getText());
				invoiceTotalPriace = Double.parseDouble(txtInvoiceTotalPrice.getText());
				String serial = (String) comboItemCode.getSelectedItem();
				int rowNum = 0;
				for (int i = 0; i < itemsDetailes.size(); i++) {
					if (serial.equals(itemsDetailes.get(i).getSerialNumber())) {
						rowNum = i;
						// this to new calculation after delete
						int agree1 = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to delete the item " + itemsDetailes.get(i).getSerialNumber()
										+ "  " + comItemName.getSelectedItem(),
								"Delete Confirmation", JOptionPane.YES_NO_OPTION);
						if (agree1 == 0) {
							invoicePrice = invoicePrice - itemsDetailes.get(i).getNetPrice();
							invoiceTax = invoiceTax - itemsDetailes.get(i).getTotalItemTax();
							invoiceTotalPriace = invoiceTotalPriace - itemsDetailes.get(i).getTotalItemPrice();
							// This to remove all items from ArrayList
							itemsDetailes.remove(itemsDetailes.get(i));
						}
					}
				}
				// These commands to change the old values by the updated values
				DefaultTableModel model2 = (DefaultTableModel) itemTable.getModel();
				int rowNum1 = 0;
				for (int i = 0; i < (itemTable.getRowCount()); i++) {// For each row
					for (int j = 0; j < itemTable.getColumnCount(); j++) {// For each column in that row
						if (itemTable.getModel().getValueAt(i, j).equals(serial)) {// Search the model
							rowNum1 = i;
						}
					} // For loop inner
				} //

				// These command to display in Textboxes and table
				model2.removeRow(rowNum1);
				invoicePrice = (double) Math.round(invoicePrice * 100.00) / 100.00;
				txtPrice.setText(String.valueOf(invoicePrice));
				invoiceTax = (double) Math.round(invoiceTax * 100.00) / 100.00;
				textInvoicTax.setText(String.valueOf(invoiceTax));
				invoiceTotalPriace = (double) Math.round(invoiceTotalPriace * 100.00) / 100.00;
				txtInvoiceTotalPrice.setText(String.valueOf(invoiceTotalPriace));
				clearTextBoxex();
			}
		});
		btnDeleteItem.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDeleteItem.setBounds(23, 372, 103, 30);
		ItemPanel.add(btnDeleteItem);
		// This button to add items to invoice and it calculate price and tax automatic
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdnanStoreInvoiceDetails itemQuantity = null;
				if (isValidData()) {
					int invoiceID = Integer.parseInt(txtInvoiceId.getText());
					String serialNumber = (String) comboItemCode.getSelectedItem();
					String itemName = (String) comItemName.getSelectedItem();
					// These Commands to calculate invoice tax and price automatically
					double unitPrice = Double.parseDouble(txtItemPrice.getText());
					int Quantity = Integer.parseInt(txtQuantity.getText());
					itemQuantity = db.getProduct(serialNumber);
					double discountPercentage = (Double.parseDouble(txtDiscount.getText())) / 100.00;
					double discount = (unitPrice * Quantity) * discountPercentage;
					discount = (double) Math.round(discount * 100.00) / 100.00;
					double netPrice = (unitPrice * Quantity) - discount;
					netPrice = (double) Math.round(netPrice * 100.00) / 100.00;
					txtNetPrice.setText(String.valueOf(netPrice));
					double tax = Constants.TAX;
					double totalTax = (netPrice * tax);
					totalTax = (double) Math.round(totalTax * 100.00) / 100.00;
					txtTax.setText(String.valueOf(totalTax));
					double totalPrice = netPrice + totalTax;
					totalPrice = (double) Math.round(totalPrice * 100.00) / 100.00;
					txtTotalPrice.setText(String.valueOf(totalPrice));
					AdnanStoreInvoiceDetails item = new AdnanStoreInvoiceDetails(invoiceID, serialNumber, unitPrice,
							Quantity, discount, totalTax, totalPrice, netPrice);
					itemsDetailes.add(item);
					Object[] items = new Object[] { item.getSerialNumber(), itemName, item.getUnitPrice(),
							item.getQuantity(), item.getDiscount(), item.getNetPrice(), item.getTotalItemTax(),
							item.getTotalItemPrice() };
					AddRowToTable(items);
					// These Commands to add taxes and prices of items
					// to the total invoice tax and price autoamtically
					invoicePrice = invoicePrice + Double.parseDouble(txtNetPrice.getText());
					invoicePrice = (double) Math.round(invoicePrice * 100.00) / 100.00;
					txtPrice.setText(String.valueOf(invoicePrice));
					invoiceTax = invoiceTax + Double.parseDouble(txtTax.getText());
					invoiceTax = (double) Math.round(invoiceTax * 100.00) / 100.00;
					textInvoicTax.setText(String.valueOf(invoiceTax));
					invoiceTotalPriace = invoiceTotalPriace + Double.parseDouble(txtTotalPrice.getText());
					invoiceTotalPriace = (double) Math.round(invoiceTotalPriace * 100.00) / 100.00;
					txtInvoiceTotalPrice.setText(String.valueOf(invoiceTotalPriace));
					// this code to warning message that items availability is low
					if (itemQuantity.gettotalQuantity() < 10) {
						JOptionPane.showMessageDialog(null,
								"Friendly Reminder, The available quantity of " + itemQuantity.getSerialNumber() + " / "
										+ itemQuantity.getProductName() + " item is lower than  "
										+ itemQuantity.gettotalQuantity(),
								"Product Availability", JOptionPane.WARNING_MESSAGE);
					}

					// This Method to clear the text boxes

					clearTextBoxex();
				}
			}
		});
		btnAddItem.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAddItem.setBounds(23, 331, 103, 30);
		ItemPanel.add(btnAddItem);
		JButton btnEditItem = new JButton("Edit Item");
		btnEditItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isValidData()) {
					// This to initialize the variables
					invoicePrice = Double.parseDouble(txtPrice.getText());
					invoiceTax = Double.parseDouble(textInvoicTax.getText());
					invoiceTotalPriace = Double.parseDouble(txtInvoiceTotalPrice.getText());
					String serial = (String) comboItemCode.getSelectedItem();
					// This command for Agreement
					int agree1 = JOptionPane
							.showConfirmDialog(null,
									"Are you sure you want to updat the item " + comboItemCode.getSelectedItem() + "  "
											+ comItemName.getSelectedItem(),
									"Update Confirmation", JOptionPane.YES_NO_OPTION);
					if (agree1 == 0) {

						if (itemsDetailes != null) {
							for (AdnanStoreInvoiceDetails itemDetail : itemsDetailes) {
								if (serial.equals(itemDetail.getSerialNumber())) {
									itemDetail.setSerialNumber(serial);
									String itemName = (String) comItemName.getSelectedItem();
									itemDetail.setProductName(itemName);
									double ItemPrice = Double.parseDouble(txtItemPrice.getText());
									itemDetail.setUnitPrice(ItemPrice);
									int newQuantity = Integer.parseInt(txtQuantity.getText());
									itemDetail.setQuantity(newQuantity);
									// These commands to update calculation tax and price after changes
									// new discount
									double newDiscountPercentage = (Double.parseDouble(txtDiscount.getText())) / 100.00;
									double newDiscount = ItemPrice * newQuantity * newDiscountPercentage;
									newDiscount = (double) Math.round(newDiscount * 100.00) / 100.00;
									itemDetail.setDiscount(newDiscount);
									txtDiscount.setText(String.valueOf(itemDetail.getDiscount()));
									// new calculation for Net Price
									double netPrice = (ItemPrice * newQuantity);
									double netPriceAfterDiscount = netPrice - newDiscount;
									netPriceAfterDiscount = (double) Math.round(netPriceAfterDiscount * 100.00)
											/ 100.00;
									invoicePrice = invoicePrice - itemDetail.getNetPrice();
									itemDetail.setNetPrice(netPriceAfterDiscount);
									invoicePrice = invoicePrice + itemDetail.getNetPrice();
									invoicePrice = (double) Math.round(invoicePrice * 100.00) / 100.00;
									txtNetPrice.setText(String.valueOf(itemDetail.getNetPrice()));
									// new calculation for Tax
									invoiceTax = invoiceTax - itemDetail.getTotalItemTax();
									double totalTax = netPriceAfterDiscount * Constants.TAX;
									totalTax = (double) Math.round(totalTax * 100.00) / 100.00;
									itemDetail.setTotalItemTax(totalTax);
									invoiceTax = invoiceTax + itemDetail.getTotalItemTax();
									invoiceTax = (double) Math.round(invoiceTax * 100.00) / 100.00;
									txtTax.setText(String.valueOf(itemDetail.getTotalItemTax()));
									// new calculation for Total price
									invoiceTotalPriace = invoiceTotalPriace - itemDetail.getTotalItemPrice();
									double totalPrice = netPriceAfterDiscount + totalTax;
									totalPrice = (double) Math.round(totalPrice * 100.00) / 100.00;
									itemDetail.setTotalItemPrice(totalPrice);
									invoiceTotalPriace = invoiceTotalPriace + itemDetail.getTotalItemPrice();
									invoiceTotalPriace = (double) Math.round(invoiceTotalPriace * 100.00) / 100.00;
									txtTotalPrice.setText(String.valueOf(itemDetail.getTotalItemPrice()));
									// these commmand to update invoice tax and price after update child items
									txtPrice.setText(String.valueOf(invoicePrice));
									textInvoicTax.setText(String.valueOf(invoiceTax));
									txtInvoiceTotalPrice.setText(String.valueOf(invoiceTotalPriace));

								}
							}
						}

					}
					// These commands to change the old values by the updated values
					DefaultTableModel model2 = (DefaultTableModel) itemTable.getModel();
					for (int i = 0; i < itemTable.getRowCount(); i++) {// For each row
						for (int j = 0; j < itemTable.getColumnCount(); j++) {// For each column in that row
							if (itemTable.getModel().getValueAt(i, j).equals(serial)) {// Search the model
								model2.setValueAt(comboItemCode.getSelectedItem(), i, 0);
								model2.setValueAt(comItemName.getSelectedItem(), i, 1);
								model2.setValueAt(txtItemPrice.getText(), i, 2);
								model2.setValueAt(txtQuantity.getText(), i, 3);
								model2.setValueAt(txtDiscount.getText(), i, 4);
								model2.setValueAt(txtNetPrice.getText(), i, 5);
								model2.setValueAt(txtTax.getText(), i, 6);
								model2.setValueAt(txtTotalPrice.getText(), i, 7);

							}
						} // For loop inner
					} // For loop outer
				}
			}
		});
		btnEditItem.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnEditItem.setBounds(182, 329, 120, 30);
		ItemPanel.add(btnEditItem);
		JButton btnResete = new JButton("Reset");
		btnResete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextBoxex();
			}
		});
		btnResete.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnResete.setBounds(182, 370, 120, 30);
		ItemPanel.add(btnResete);
		comItemName = new JComboBox();
		comItemName.setFont(new Font("Times New Roman", Font.BOLD, 13));
		comItemName.setBackground(Color.WHITE);
		comItemName.setBounds(131, 65, 171, 22);
		ItemPanel.add(comItemName);
		comboItemCode = new JComboBox();
		comboItemCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboItemCode.getSelectedItem() != null) {
					String item = (String) comboItemCode.getSelectedItem();
					AdnanStoreInvoiceDetails product;
					product = db.getProduct(item);
					String serialNum = product.getSerialNumber();
					comboItemCode.setSelectedItem(serialNum);
					String itemName = product.getProductName();
					comItemName.setSelectedItem(itemName);
					txtItemPrice.setText(String.valueOf((product.getUnitPrice())));
				}

			}
		});
		comboItemCode.setFont(new Font("Times New Roman", Font.BOLD, 13));
		comboItemCode.setBackground(UIManager.getColor("Button.highlight"));
		comboItemCode.setBounds(131, 34, 171, 22);
		ItemPanel.add(comboItemCode);
		JPanel OperationPanel = new JPanel();
		OperationPanel.setBounds(10, 453, 1200, 80);
		getContentPane().add(OperationPanel);
		OperationPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Operations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		// This Method to start a New Invoice and give automatic number
		btnNewInvoice = new JButton("New Invoice");
		btnNewInvoice.setBounds(10, 32, 120, 30);
		btnNewInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// This to initilais the variables price and taxes
				invoicePrice = 0;
				invoiceTax = 0;
				invoiceTotalPriace = 0;
				txtPrice.setText("");
				textInvoicTax.setText("");
				txtInvoiceTotalPrice.setText("");
				// this command for Table header
				itemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Item Name",
						"Unit Price", "Quantity", "Discount", "Net Price", "Tax", "Total" }));
				ArrayList<AdnanStoreInvoice> invoices = new ArrayList<AdnanStoreInvoice>();
				invoices = db.getAllInvoices();
				int invoiceId1;
				if (invoices.size() == 0) {
					invoiceId1 = 1;
					txtInvoiceId.setText(String.valueOf(invoiceId1));
				} else {
					AdnanStoreInvoice lastInvoice = invoices.get(invoices.size() - 1);
					invoiceId1 = lastInvoice.getInvoiceID() + 1;
					txtInvoiceId.setText(String.valueOf(invoiceId1));
				}
				// this code to fill ComboBox
				comboItemCode.removeAllItems();
				comItemName.removeAllItems();
				ArrayList<AdnanStoreInvoiceDetails> products = new ArrayList<>();
				products = db.getAllProduct();
				for (AdnanStoreInvoiceDetails product : products) {
					comboItemCode.addItem(product.getSerialNumber());
					comItemName.addItem(product.getProductName());
				}
			}
		});
		OperationPanel.setLayout(null);
		// buttonGroup.add(btnNewButton_1);
		btnNewInvoice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		OperationPanel.add(btnNewInvoice);
		// This Method for Modify
		JButton btnEditInvoice = new JButton("Edit Invoice");
		btnEditInvoice.setBounds(270, 33, 120, 30);
		btnEditInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// this for confirmation
				int agree1 = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to Update the invoice " + txtInvoiceId.getText() + "  and all items",
						"Update Confirmation", JOptionPane.YES_NO_OPTION);
				if (agree1 == 0) {
					AdnanStoreInvoice invoice = null;
					int invoiceID = Integer.parseInt(txtInvoiceId.getText());
					invoice = db.getStoreInvoice(invoiceID);
					try {
						Date invoiceDate = new SimpleDateFormat("dd-MM-yyyy").parse(lblcurentDate.getText());
						invoice.setInvoiceDate(invoiceDate);
						double invoicePrice = Double.parseDouble(txtPrice.getText());
						invoice.setInvoicePrice(invoicePrice);
						double invoiceTax = Double.parseDouble(textInvoicTax.getText());
						invoice.setInvoiceTax(invoiceTax);
						double totalInvoicPrice = Double.parseDouble(txtInvoiceTotalPrice.getText());
						invoice.setTotalInvoicPrice(totalInvoicPrice);
						db.updatInvoice(invoice);
						db.updateItems(itemsDetailes, invoiceID);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnEditInvoice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		OperationPanel.add(btnEditInvoice);
		// This Method to Delete invoice and all its items
		JButton btnDeleteInvoice = new JButton("Delete Invoice");
		btnDeleteInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// this for confirmation
				int agree1 = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to Delete the invoice " + txtInvoiceId.getText() + "  and all items",
						"Delete Confirmation", JOptionPane.YES_NO_OPTION);
				if (agree1 == 0) {
					int invoiceID = Integer.parseInt(txtInvoiceId.getText());
					AdnanStoreInvoice deletedInvoice = null;
					deletedInvoice = db.getStoreInvoice(invoiceID);
					db.deleteInvoice(deletedInvoice);
					// This to clear all form data
					clearForm();

				}
			}
		});
		btnDeleteInvoice.setBounds(400, 32, 130, 30);
		btnDeleteInvoice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		OperationPanel.add(btnDeleteInvoice);
		// This Method to Display all invoices
		JButton btnDsiplayInvoices = new JButton("Dsiplay Invoices");
		btnDsiplayInvoices.setBounds(540, 32, 145, 30);
		btnDsiplayInvoices.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDsiplayInvoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Invoice Number",
						"Invoice Date", "Invoice Price", "Invoice Tax", "Total Invoic Price" }));
				// This ArrayList to save all invoices
				ArrayList<AdnanStoreInvoice> invoices = new ArrayList<>();
				invoices = db.getAllInvoices();
				// this variables for total invoices tax and prices
				double totalInvoicesPrice = 0;
				double totalInvoicesTax = 0;
				double totalInvoicesNetPrice = 0;
				for (AdnanStoreInvoice inv : invoices) {
					Object[] invoicData = new Object[] { inv.getInvoiceID(), inv.getInvoiceDate(),
							inv.getInvoicePrice(), inv.getInvoiceTax(), inv.getTotalInvoicPrice() };
					AddRowToTable(invoicData);
					totalInvoicesNetPrice += inv.getInvoicePrice();
					totalInvoicesTax += inv.getInvoiceTax();
					totalInvoicesPrice += inv.getTotalInvoicPrice();
				}
				txtPrice.setText(String.valueOf(Math.round(totalInvoicesNetPrice)));
				textInvoicTax.setText(String.valueOf(Math.round(totalInvoicesTax)));
				txtInvoiceTotalPrice.setText(String.valueOf(Math.round(totalInvoicesPrice)));
			}
		});
		OperationPanel.add(btnDsiplayInvoices);

		// This Method for Find
		JButton btnFindInvoice = new JButton("Find Invoice");
		btnFindInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
				// this command to initials the table
				itemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Item Name",
						"Unit Price", "Quantity", "Discount", "Net Price", "Tax", "Total" }));
				AdnanStoreInvoice foundInvoice = null;
				int input = Integer.parseInt((JOptionPane.showInputDialog(null, "Please,Enter invoice Id")));
				foundInvoice = db.getStoreInvoice(input);
				if (foundInvoice != null) {
					txtInvoiceId.setText(String.valueOf(foundInvoice.getInvoiceID()));
					lblcurentDate.setText(String.valueOf(foundInvoice.getInvoiceDate()));
					txtPrice.setText(String.valueOf(foundInvoice.getInvoicePrice()));
					textInvoicTax.setText(String.valueOf(foundInvoice.getInvoiceTax()));
					txtInvoiceTotalPrice.setText(String.valueOf(foundInvoice.getTotalInvoicPrice()));
					// These variable to save the tax an prices of invoice
					invoicePrice = foundInvoice.getInvoicePrice();
					invoiceTax = foundInvoice.getInvoiceTax();
					invoiceTotalPriace = foundInvoice.getTotalInvoicPrice();

				} else {
					clearForm();
				}
				// fill up the ArrayList
				itemsDetailes = db.getAllInvoiceItems(input);
				for (AdnanStoreInvoiceDetails item : itemsDetailes) {
					AdnanStoreInvoiceDetails item1 = db.getProduct(item.getSerialNumber());
					Object[] invoiceItems = new Object[] { item.getSerialNumber(), item1.getProductName(),
							item1.getUnitPrice(), item.getQuantity(), item.getDiscount(), item.getNetPrice(),
							item.getTotalItemTax(), item.getTotalItemPrice() };
					AddRowToTable(invoiceItems);
				}
			}
		});
		btnFindInvoice.setBounds(690, 32, 120, 30);
		btnFindInvoice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		OperationPanel.add(btnFindInvoice);
		// This Method for Print an invoice
		JButton btnprintInvoice = new JButton("print Invoice");
		btnprintInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdnanPrintInvoice form = new AdnanPrintInvoice();
				MainForm.desktop.add(form, Integer.valueOf(5));
				int invoiceCode = Integer.parseInt(txtInvoiceId.getText());
				form.printInvoice1(form.getTextArea(), invoiceCode);
				form.setSize(1000, 600);
				form.requestFocus(false);
				form.setIconifiable(true);
				form.setLocation(100, 10);
				form.setClosable(true);
				form.moveToFront();
			}
		});
		// This Method for reset
		btnprintInvoice.setBounds(820, 32, 130, 30);
		btnprintInvoice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		OperationPanel.add(btnprintInvoice);
		// This Method for Rest
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		// This Method for close the form
		btnReset.setBounds(970, 32, 100, 30);
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 16));
		OperationPanel.add(btnReset);
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		//
		btnClose.setBounds(1090, 32, 105, 30);
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 16));
		OperationPanel.add(btnClose);
		// This Method to save invoice and all items
		btnSaveInvoice = new JButton("Save Invoice");
		btnSaveInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int agree1 = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to Save the invoice " + txtInvoiceId.getText() + "  and all items",
						"Save Confirmation", JOptionPane.YES_NO_OPTION);
				if (agree1 == 0) {
					try {
						int invoiceID = Integer.parseInt(txtInvoiceId.getText());
						Date invoiceDate = new SimpleDateFormat("dd-MM-yyyy").parse(lblcurentDate.getText());
						double invoicePrice = Double.parseDouble(txtPrice.getText());
						double invoiceTax = Double.parseDouble(textInvoicTax.getText());
						double totalInvoicPrice = Double.parseDouble(txtInvoiceTotalPrice.getText());
						AdnanStoreInvoice invoice = new AdnanStoreInvoice(invoiceID, invoiceDate, invoicePrice,
								invoiceTax, totalInvoicPrice);
						db.addInvoice(invoice);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// These new items details
					if (itemsDetailes != null) {
						db.addItems(itemsDetailes);
					}
					itemsDetailes.removeAll(itemsDetailes);
					clearForm();
				}
			}
		});
		btnSaveInvoice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSaveInvoice.setBounds(140, 32, 120, 30);
		OperationPanel.add(btnSaveInvoice);
		JPanel InvoicePanel = new JPanel();
		InvoicePanel.setBounds(374, 31, 834, 104);
		getContentPane().add(InvoicePanel);
		InvoicePanel.setLayout(null);
		InvoicePanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Invoice Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		JLabel lblInvoiceId = new JLabel("Invoice Id");
		lblInvoiceId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblInvoiceId.setBounds(10, 40, 81, 25);
		InvoicePanel.add(lblInvoiceId);
		txtInvoiceId = new JTextField();
		txtInvoiceId.setEnabled(false);
		txtInvoiceId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtInvoiceId.setBounds(89, 40, 100, 25);
		InvoicePanel.add(txtInvoiceId);
		txtInvoiceId.setColumns(10);
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrice.setBounds(222, 40, 48, 25);
		InvoicePanel.add(lblPrice);
		txtPrice = new JTextField();
		txtPrice.setEnabled(false);
		txtPrice.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtPrice.setBounds(272, 40, 100, 25);
		InvoicePanel.add(txtPrice);
		txtPrice.setColumns(10);
		JLabel lblInvoiceTax = new JLabel("Tax");
		lblInvoiceTax.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblInvoiceTax.setBounds(408, 40, 48, 25);
		InvoicePanel.add(lblInvoiceTax);
		textInvoicTax = new JTextField();
		textInvoicTax.setEnabled(false);
		textInvoicTax.setFont(new Font("Times New Roman", Font.BOLD, 13));
		textInvoicTax.setColumns(10);
		textInvoicTax.setBounds(458, 40, 120, 25);
		InvoicePanel.add(textInvoicTax);
		JLabel lblInvoicePrice = new JLabel("Total Price");
		lblInvoicePrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblInvoicePrice.setBounds(600, 40, 90, 25);
		InvoicePanel.add(lblInvoicePrice);
		txtInvoiceTotalPrice = new JTextField();
		txtInvoiceTotalPrice.setEnabled(false);
		txtInvoiceTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtInvoiceTotalPrice.setColumns(10);
		txtInvoiceTotalPrice.setBounds(680, 40, 120, 25);
		InvoicePanel.add(txtInvoiceTotalPrice);
		lblcurentDate = new JLabel("");
		lblcurentDate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblcurentDate.setText(dateformat.format(current));
		lblcurentDate.setBounds(10, 15, 287, 14);
		InvoicePanel.add(lblcurentDate);
		JPanel navigation = new JPanel();
		navigation.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),

				"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		navigation.setBounds(374, 405, 834, 40);
		getContentPane().add(navigation);
		navigation.setLayout(null);
		// Invoices Navigation Previous Invoice
		JButton btnPreviousInvoice = new JButton("Previous Invoice");
		btnPreviousInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdnanStoreInvoice invoice = null;
					invoice = db.getPrevious();
					if (invoice != null) {
						displayInvoice(invoice);
						int invoiceID1 = Integer.parseInt(txtInvoiceId.getText());
						itemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Item Name",
								"Unit Price", "Quantity", "Discount", "Net Price", "Tax", "Total" }));
						ArrayList<AdnanStoreInvoiceDetails> products = new ArrayList<AdnanStoreInvoiceDetails>();
						AdnanInvoiceDetailesDBAccess db3 = new AdnanInvoiceDetailesDBAccess();
						products = db3.getAllInvoiceDetailes();
						for (AdnanStoreInvoiceDetails product : products) {
							if (product.getInvoiceId() == invoiceID1) {
								AdnanStoreInvoiceDetails itemName = db3.getProduct(product.getSerialNumber());
								Object[] invoiceItems = new Object[] { product.getSerialNumber(),
										itemName.getProductName(), product.getUnitPrice(), product.getQuantity(),
										product.getDiscount(), product.getNetPrice(), product.getTotalItemTax(),
										product.getTotalItemPrice() };
								AddRowToTable(invoiceItems);
							}
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPreviousInvoice.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnPreviousInvoice.setBounds(430, 11, 130, 25);
		navigation.add(btnPreviousInvoice);
		// Invoices Navigation Next Invoice
		JButton btnNextInvoice = new JButton("Next Invoice");
		btnNextInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					AdnanStoreInvoice invoice = null;
					invoice = db.getNext();
					if (invoice != null) {
						displayInvoice(invoice);
						int invoiceID1 = Integer.parseInt(txtInvoiceId.getText());
						itemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Item Name",
								"Unit Price", "Quantity", "Discount", "Net Price", "Tax", "Total" }));
						ArrayList<AdnanStoreInvoiceDetails> products = new ArrayList<AdnanStoreInvoiceDetails>();
						AdnanInvoiceDetailesDBAccess db2 = new AdnanInvoiceDetailesDBAccess();
						products = db2.getAllInvoiceDetailes();

						for (AdnanStoreInvoiceDetails product : products) {
							if (product.getInvoiceId() == invoiceID1) {
								AdnanStoreInvoiceDetails itemName = db2.getProduct(product.getSerialNumber());
								Object[] invoiceItems = new Object[] { product.getSerialNumber(),
										itemName.getProductName(), product.getUnitPrice(), product.getQuantity(),
										product.getDiscount(), product.getNetPrice(), product.getTotalItemTax(),
										product.getTotalItemPrice() };
								AddRowToTable(invoiceItems);

							}

						}

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNextInvoice.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNextInvoice.setBounds(280, 11, 130, 25);
		navigation.add(btnNextInvoice);
		// Invoices Navigation First Invoice
		JButton btnFirstInvoic = new JButton(" First Invoice");
		btnFirstInvoic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdnanStoreInvoice invoice = null;
					invoice = db.getFirst();
					if (invoice != null) {
						displayInvoice(invoice);
						int invoiceID1 = invoice.getInvoiceID();
						ArrayList<AdnanStoreInvoiceDetails> products = new ArrayList<AdnanStoreInvoiceDetails>();
						ArrayList<AdnanStoreInvoiceDetails> productName = new ArrayList<AdnanStoreInvoiceDetails>();
						AdnanInvoiceDetailesDBAccess db1 = new AdnanInvoiceDetailesDBAccess();
						products = db1.getAllInvoiceDetailes();
						for (AdnanStoreInvoiceDetails product : products) {
							if (product.getInvoiceId() == invoiceID1) {
								AdnanStoreInvoiceDetails itemName = db1.getProduct(product.getSerialNumber());
								Object[] invoiceItems = new Object[] { product.getSerialNumber(),
										itemName.getProductName(), product.getUnitPrice(), product.getQuantity(),
										product.getDiscount(), product.getNetPrice(), product.getTotalItemTax(),
										product.getTotalItemPrice() };
								AddRowToTable(invoiceItems);

							}

						}

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnFirstInvoic.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnFirstInvoic.setBounds(130, 11, 130, 25);
		navigation.add(btnFirstInvoic);
		// Invoices Navigation Last Invoice
		JButton btnLastInvoice = new JButton("Last Invoice");
		btnLastInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					AdnanStoreInvoice invoice = null;
					invoice = db.getLast();
					if (invoice != null) {
						displayInvoice(invoice);
						int invoiceID1 = Integer.parseInt(txtInvoiceId.getText());
						itemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Item Name",
								"Unit Price", "Quantity", "Discount", "Net Price", "Tax", "Total" }));

						ArrayList<AdnanStoreInvoiceDetails> products = new ArrayList<AdnanStoreInvoiceDetails>();
						AdnanInvoiceDetailesDBAccess db4 = new AdnanInvoiceDetailesDBAccess();
						products = db4.getAllInvoiceDetailes();

						for (AdnanStoreInvoiceDetails product : products) {
							if (product.getInvoiceId() == invoiceID1) {
								AdnanStoreInvoiceDetails itemName = db4.getProduct(product.getSerialNumber());
								Object[] invoiceItems = new Object[] { product.getSerialNumber(),
										itemName.getProductName(), product.getUnitPrice(), product.getQuantity(),
										product.getDiscount(), product.getNetPrice(), product.getTotalItemTax(),
										product.getTotalItemPrice() };
								AddRowToTable(invoiceItems);

							}

						}

					}

					// JOptionPane.showMessageDialog(null, invoice.getInvoiceID());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLastInvoice.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnLastInvoice.setBounds(580, 11, 130, 25);
		navigation.add(btnLastInvoice);

	}

	// This Method to show data in TextBoxes
	public void displayInvoice(AdnanStoreInvoice invoice) {
		txtInvoiceId.setText(String.valueOf(invoice.getInvoiceID()));
		lblcurentDate.setText(String.valueOf(invoice.getInvoiceDate()));
		txtPrice.setText(String.valueOf(invoice.getInvoicePrice()));
		textInvoicTax.setText(String.valueOf(invoice.getInvoiceTax()));
		txtInvoiceTotalPrice.setText(String.valueOf(invoice.getTotalInvoicPrice()));
	}

//This Method to add Data to Table
	public static void AddRowToTable(Object[] datarow) {
		DefaultTableModel model1 = (DefaultTableModel) itemTable.getModel();
		model1.addRow(datarow);
	}

//This method to select Data from Table
	public void SelectRowToTable() {
		DefaultTableModel model1 = (DefaultTableModel) itemTable.getModel();
		int i = itemTable.getSelectedRow();
		if (i >= 0) {
			String serial = model1.getValueAt(i, 0).toString();
			String itemName = model1.getValueAt(i, 1).toString();
			if (itemsDetailes != null) {
				for (AdnanStoreInvoiceDetails itemDetail : itemsDetailes) {
					if (serial.equals(itemDetail.getSerialNumber())) {
						comboItemCode.setEditable(true);
						comboItemCode.setSelectedItem(itemDetail.getSerialNumber());
						comboItemCode.setEditable(false);
						comItemName.setEditable(true);
						comItemName.setSelectedItem(itemName);
						comItemName.setEditable(false);
						txtItemPrice.setText(String.valueOf(itemDetail.getUnitPrice()));
						txtQuantity.setText(String.valueOf(itemDetail.getQuantity()));
						double discountPercent = (itemDetail.getDiscount())
								/ (itemDetail.getQuantity() * itemDetail.getUnitPrice());
						discountPercent = discountPercent * 100;
						discountPercent = (double) Math.round(discountPercent * 100.00) / 100.00;
						txtDiscount.setText(String.valueOf(discountPercent));
						txtNetPrice.setText(String.valueOf(itemDetail.getNetPrice()));
						txtTax.setText(String.valueOf(itemDetail.getTotalItemTax()));
						txtTotalPrice.setText(String.valueOf(itemDetail.getTotalItemPrice()));
					}
				}

			}

		}
	}

	// This Method to clear the form
	public void clearForm() {
		itemTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Item Name", "Unit Price",
				"Quantity", "Discount", "Net Price", "Tax", "Total" }));
		txtInvoiceId.setText("");
		// lblcurentDate.setText("");
		txtPrice.setText("");
		textInvoicTax.setText("");
		txtInvoiceTotalPrice.setText("");
		clearTextBoxex();
	}

//This Method to clear TextBoxes
	public void clearTextBoxex() {
		comboItemCode.setSelectedItem("");
		comItemName.setSelectedItem("");
		txtItemPrice.setText("");
		txtQuantity.setText("");
		txtDiscount.setText("");
		txtNetPrice.setText("");
		txtTax.setText("");
		txtTotalPrice.setText("");

	}

	// this method to print invoice
	public void printInvoice(JTextArea textArea, int invoiceNum) {
		AdnanStoreInvoice foundInvoice = null;
		foundInvoice = db.getStoreInvoice(invoiceNum);
		if (foundInvoice != null) {
			textArea.append(String.valueOf(foundInvoice.getInvoiceID()));
			textArea.append(String.valueOf(foundInvoice.getInvoiceDate()));
		}
	}

//This Method for validating 
	public boolean isValidData() {
		if (!ValidatorAdnan.isperesnt(txtItemPrice, "Item Price "))
			return false;
		if (!ValidatorAdnan.isperesnt(txtQuantity, "Quantity "))
			return false;
		if (!ValidatorAdnan.isInteger(txtQuantity, "Quantity "))
			return false;
		// this code to validate product availability
		AdnanStoreInvoiceDetails itemQuantity = null;
		String serialNumber = (String) comboItemCode.getSelectedItem();
		itemQuantity = db.getProduct(serialNumber);
		int Quantity = Integer.parseInt(txtQuantity.getText());
		if (Quantity > itemQuantity.gettotalQuantity()) {
			JOptionPane.showMessageDialog(txtQuantity,
					"Sorry, The available quantity is only:  " + itemQuantity.gettotalQuantity() + "  items",
					"Invalid entry", JOptionPane.ERROR_MESSAGE);
			txtQuantity.requestFocusInWindow();
			return false;
		}

		if (!ValidatorAdnan.isperesnt(txtDiscount, "Discount"))
			return false;
		if (!ValidatorAdnan.isDouble(txtDiscount, "Discount"))
			return false;

		return true;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdnanInvoice frame = new AdnanInvoice();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
