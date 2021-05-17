package Data;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Business.AdnanStoreInvoice;
import Business.AdnanStoreInvoiceDetails;

//This class extends AdnanInvoiceDBAccess, so I can use all its methods 
public class AdnanInvoiceDetailesDBAccess extends AdnanInvoiceDBAccess {

	// This method to get all sold items for all invoices and return them in
	// ArrayList
	public ArrayList<AdnanStoreInvoiceDetails> getAllInvoiceDetailes() {
		ArrayList<AdnanStoreInvoiceDetails> invoices = new ArrayList<AdnanStoreInvoiceDetails>();
		String str = " select invoiceid,serialnumber,unitprice,quantity,discount,"
				+ "totalitemtax,totalitemprice,netprice from STOREINVOICEDETAILS";
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(str);
			rs = pstm.executeQuery();
			while (rs.next()) {
				int invoiceId = rs.getInt("invoiceId");
				String serialNumber = rs.getString("serialNumber");
				double unitPrice = rs.getDouble("unitPrice");
				int quantity = rs.getInt("quantity");
				double discount = rs.getDouble("discount");
				double totalItemTax = rs.getDouble("totalItemTax");
				double totalItemPrice = rs.getDouble("totalItemPrice");
				double netprice = rs.getDouble("netprice");
				AdnanStoreInvoiceDetails invoice = new AdnanStoreInvoiceDetails(invoiceId, serialNumber, unitPrice,
						quantity, discount, totalItemTax, totalItemPrice, netprice);
				invoices.add(invoice);
			}
			conn.close();
			return invoices;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The table or DataBase is not exist");
			return null;
		}

	}

	// This method to get all items for only one invoice using invoice id (one
	// invoice has manay items)
	// and return them in Array List
	public ArrayList<AdnanStoreInvoiceDetails> getAllInvoiceItems(int serial) {
		ArrayList<AdnanStoreInvoiceDetails> invoices = new ArrayList<AdnanStoreInvoiceDetails>();
		String str = " select invoiceid,serialnumber,unitprice,quantity,discount,"
				+ "totalitemtax,totalitemprice,netprice from STOREINVOICEDETAILS where invoiceid=?";
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(str);
			pstm.setInt(1, serial);
			rs = pstm.executeQuery();
			while (rs.next()) {
				int invoiceId = rs.getInt("invoiceId");
				String serialNumber = rs.getString("serialNumber");
				double unitPrice = rs.getDouble("unitPrice");
				int quantity = rs.getInt("quantity");
				double discount = rs.getDouble("discount");
				double totalItemTax = rs.getDouble("totalItemTax");
				double totalItemPrice = rs.getDouble("totalItemPrice");
				double netprice = rs.getDouble("netprice");
				AdnanStoreInvoiceDetails invoice = new AdnanStoreInvoiceDetails(invoiceId, serialNumber, unitPrice,
						quantity, discount, totalItemTax, totalItemPrice, netprice);
				invoices.add(invoice);
			}
			conn.close();
			return invoices;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The table or DataBase is not exist");
			return null;
		}

	}

	// This method to get only one product from product table.
	public AdnanStoreInvoiceDetails getProduct(String serial) {
		String str = "select serialnumber,productname,retail,quantity " + "from storeproduct where serialnumber=?";
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(str);
			pstm.setString(1, serial);
			rs = pstm.executeQuery();
			if (rs.next()) {
				String serialNumber = rs.getString("serialNumber");
				String productname = rs.getString("productname");
				double unitPrice = rs.getDouble("retail");
				String quantity1 = rs.getString("QUANTITY");
				int quantity = Integer.parseInt(quantity1);
				AdnanStoreInvoiceDetails product = new AdnanStoreInvoiceDetails(serialNumber, productname, unitPrice,
						quantity);
				conn.close();
				return product;
			} else {
				JOptionPane.showMessageDialog(null, "The item is not exist");
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The item is not exist");
			return null;
		}

	}

	// this method get all products from product table so I can fill combobox
	public ArrayList<AdnanStoreInvoiceDetails> getAllProduct() {
		ArrayList<AdnanStoreInvoiceDetails> products = new ArrayList<AdnanStoreInvoiceDetails>();
		String str = "select serialnumber,productname,retail from storeproduct";

		try {
			conn = getConnection();
			pstm = conn.prepareStatement(str);
			rs = pstm.executeQuery();
			while (rs.next()) {
				String serialNumber = rs.getString("serialNumber");
				String productname = rs.getString("productname");
				double unitPrice = rs.getDouble("retail");
				AdnanStoreInvoiceDetails product = new AdnanStoreInvoiceDetails(serialNumber, productname, unitPrice);
				products.add(product);
			}
			conn.close();
			return products;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The products are not exist");
			return null;
		}

	}

	// this method to add invoice's items(detailes)
	public boolean addItem(AdnanStoreInvoiceDetails item) {
		try {
			conn = this.getConnection();
			String str = "insert into STOREINVOICEDETAILS(invoiceid,serialnumber,"
					+ "unitprice,quantity,discount,totalitemtax,totalitemprice,netPrice)" + "VALUES(?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(str);
			pstm.setInt(1, item.getInvoiceId());
			pstm.setString(2, item.getSerialNumber());
			pstm.setDouble(3, item.getUnitPrice());
			pstm.setInt(4, item.getQuantity());
			pstm.setDouble(5, item.getDiscount());
			pstm.setDouble(6, item.getTotalItemTax());
			pstm.setDouble(7, item.getTotalItemPrice());
			pstm.setDouble(8, item.getNetPrice());
			pstm.executeUpdate();
			JOptionPane.showMessageDialog(null, "The Item was added successfully");
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The Item adding was unsuccessfully");
			return false;
		}

	}

	// This method to add all items in one invoice, it accept ArrayList and add all
	// the items
	public boolean addItems(ArrayList<AdnanStoreInvoiceDetails> items) {
		try {
			conn = this.getConnection();
			String str = "insert into STOREINVOICEDETAILS(invoiceid,serialnumber,"
					+ "unitprice,quantity,discount,totalitemtax,totalitemprice,netPrice)" + "VALUES(?,?,?,?,?,?,?,?)";
			for (AdnanStoreInvoiceDetails item : items) {
				pstm = conn.prepareStatement(str);
				pstm.setInt(1, item.getInvoiceId());
				pstm.setString(2, item.getSerialNumber());
				pstm.setDouble(3, item.getUnitPrice());
				pstm.setInt(4, item.getQuantity());
				pstm.setDouble(5, item.getDiscount());
				pstm.setDouble(6, item.getTotalItemTax());
				pstm.setDouble(7, item.getTotalItemPrice());
				pstm.setDouble(8, item.getNetPrice());
				pstm.executeUpdate();
			}
			// JOptionPane.showMessageDialog(null, "The Items was added successfully");
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The Items adding was unsuccessfully");
			return false;
		}

	}

//This method to update invoice items, it accept ArrayList and update them 
	public boolean updateItems(ArrayList<AdnanStoreInvoiceDetails> items, int invoiceid1) {
		try {
			conn = this.getConnection();
			String str = "update STOREINVOICEDETAILS set invoiceid=?,serialnumber=?, unitprice=?, quantity=?, discount=?, totalitemtax=?, totalitemprice=?, netPrice=? where invoiceid=? and serialnumber=?";
			for (AdnanStoreInvoiceDetails item : items) {
				pstm = conn.prepareStatement(str);
				pstm.setInt(1, item.getInvoiceId());
				pstm.setString(2, item.getSerialNumber());
				pstm.setDouble(3, item.getUnitPrice());
				pstm.setInt(4, item.getQuantity());
				pstm.setDouble(5, item.getDiscount());
				pstm.setDouble(6, item.getTotalItemTax());
				pstm.setDouble(7, item.getTotalItemPrice());
				pstm.setDouble(8, item.getNetPrice());
				pstm.setInt(9, invoiceid1);
				pstm.setString(10, item.getSerialNumber());
				pstm.executeUpdate();
			}
			// JOptionPane.showMessageDialog(null, "The Items was update successfully");
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The Items updating was unsuccessfully");
			return false;
		}

	}

}
