package Data;

import java.sql.Connection;
//import java.util.*;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Business.AdnanStoreInvoice;

public class AdnanInvoiceDBAccess {
	// Variable Declaration
	protected Connection conn = null;
	protected PreparedStatement pstm = null;
	protected Statement stm = null;
	protected ResultSet rs = null;

	// this method to start connection with database
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@CALVIN.HUMBER.CA:1521:GROK";
		String username = "N01335459";
		String password = "oracle";
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	// This method to get ResultSeT
	public ResultSet getResultSet() throws SQLException {
		String str = "select invoiceID,invoiceDate,invoicePrice,"
				+ "invoiceTax,totalInvoicPrice from storeinvoice order by invoiceID";
		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = stm.executeQuery(str);
		return rs;
	}

	// This method to get all invoices and return ArrayList
	public ArrayList<AdnanStoreInvoice> getAllInvoices() {
		ArrayList<AdnanStoreInvoice> invoices = new ArrayList<AdnanStoreInvoice>();
		String str = "select invoiceID,invoiceDate,invoicePrice,"
				+ "invoiceTax,totalInvoicPrice from storeinvoice order by invoiceID";
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(str);
			rs = pstm.executeQuery();
			while (rs.next()) {
				int invoiceID = rs.getInt("invoiceID");
				Date invoiceDate = rs.getDate("invoiceDate");
				double invoicePrice = rs.getDouble("invoicePrice");
				double invoiceTax = rs.getDouble("invoiceTax");
				double totalInvoicPrice = rs.getDouble("invoiceTax");
				AdnanStoreInvoice invoice = new AdnanStoreInvoice(invoiceID, invoiceDate, invoicePrice, invoiceTax,
						totalInvoicPrice);
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

	// This method to get one invoice object using invoice Id
	public AdnanStoreInvoice getStoreInvoice(int num) {
		String str = "select invoiceID,invoiceDate,invoicePrice,invoiceTax,"
				+ "totalInvoicPrice from storeinvoice where invoiceid=?";
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(str);
			pstm.setInt(1, num);
			rs = pstm.executeQuery();
			if (rs.next()) {
				int invoiceID = rs.getInt("invoiceID");
				Date invoiceDate = rs.getDate("invoiceDate");
				double invoicePrice = rs.getDouble("invoicePrice");
				double invoiceTax = rs.getDouble("invoiceTax");
				double totalInvoicPrice = rs.getDouble("totalInvoicPrice");
				AdnanStoreInvoice invoice = new AdnanStoreInvoice(invoiceID, invoiceDate, invoicePrice, invoiceTax,
						totalInvoicPrice);
				conn.close();
				return invoice;
			} else {
				JOptionPane.showMessageDialog(null, "The invoice is not exist");
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The invoice is not exist");
			return null;
		}
	}

	// This method used in invoice navigation(first, next, previous, and last);
	public AdnanStoreInvoice getStoreInvoice() {
		AdnanStoreInvoice invoice = null;
		try {
			if (rs != null) {
				int invoiceID = rs.getInt("invoiceID");
				Date invoiceDate = rs.getDate("invoiceDate");
				double invoicePrice = rs.getDouble("invoicePrice");
				double invoiceTax = rs.getDouble("invoiceTax");
				double totalInvoicPrice = rs.getDouble("totalInvoicPrice");
				invoice = new AdnanStoreInvoice(invoiceID, invoiceDate, invoicePrice, invoiceTax, totalInvoicPrice);
				return invoice;
			} else {
				JOptionPane.showMessageDialog(null, "The invoice is not exist");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The invoice is not exist");
			return null;
		}
	}

	// This Method to add invoice
	public boolean addInvoice(AdnanStoreInvoice invoice) {
		try {
			conn = this.getConnection();
			String str = "Insert into StoreInvoice(invoiceID,invoiceDate,invoicePrice,"
					+ "invoiceTax,totalInvoicPrice) values(?,?,?,?,?)";
			pstm = conn.prepareStatement(str);
			pstm.setInt(1, invoice.getInvoiceID());
			pstm.setDate(2, new java.sql.Date(invoice.getInvoiceDate().getTime()));
			pstm.setDouble(3, invoice.getInvoicePrice());
			pstm.setDouble(4, invoice.getInvoiceTax());
			pstm.setDouble(5, invoice.getTotalInvoicPrice());
			pstm.executeUpdate();
			JOptionPane.showMessageDialog(null, "The Invoice was added successfully");
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The Invoice adding was unsuccessfully");
			return false;
		}

	}

//This method to update Invoice
	public boolean updatInvoice(AdnanStoreInvoice invoice) {
		try {
			String str = "update storeInvoice set invoiceDate=?, invoicePrice=?, "
					+ "invoiceTax=?, totalInvoicPrice=? where invoiceid=?";
			conn = this.getConnection();
			pstm = conn.prepareStatement(str);
			pstm.setDate(1, new java.sql.Date(invoice.getInvoiceDate().getTime()));
			pstm.setDouble(2, invoice.getInvoicePrice());
			pstm.setDouble(3, invoice.getInvoiceTax());
			pstm.setDouble(4, invoice.getTotalInvoicPrice());
			pstm.setInt(5, invoice.getInvoiceID());
			pstm.executeUpdate();
			JOptionPane.showMessageDialog(null, "The Invoice was updated successfully");
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The Invoice was not updated successfully");
			return false;
		}
	}

	// This method to delete
	public void deleteInvoice(AdnanStoreInvoice invoice) {
		String str = "delete from storeInvoice where invoiceid=?";
		try {
			conn = this.getConnection();
			pstm = conn.prepareStatement(str);
			pstm = conn.prepareStatement(str);
			pstm.setInt(1, invoice.getInvoiceID());
			pstm.executeUpdate();
			JOptionPane.showMessageDialog(null, "The Invoice was deleted successfully");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The Invoice was not deleted successfully");
		}

	}

	// This method to get the next record
	public AdnanStoreInvoice getNext() throws SQLException, ClassNotFoundException {
		AdnanStoreInvoice invoice = null;

		if (rs != null) {
			if (!(rs.isLast())) {
				rs.next();
				invoice = this.getStoreInvoice();
			}
		}
		return invoice;
	}

	// This method to get the previous record
	public AdnanStoreInvoice getPrevious() throws SQLException {
		AdnanStoreInvoice invoice = null;
		if (rs != null) {
			if (!(rs.isFirst())) {
				rs.previous();
				invoice = this.getStoreInvoice();
			}
		}
		return invoice;
	}

	// This method to get Last Record
	public AdnanStoreInvoice getLast() throws SQLException {
		AdnanStoreInvoice invoice = null;
		if (rs != null) {
			rs.last();
			invoice = this.getStoreInvoice();
		}

		return invoice;
	}

	// This method to get First record
	public AdnanStoreInvoice getFirst() throws SQLException, ClassNotFoundException {
		AdnanStoreInvoice invoice = null;
		conn = this.getConnection();
		rs = this.getResultSet();
		if (rs != null) {
			rs.first();
			invoice = this.getStoreInvoice();
		}
		return invoice;
	}

}
