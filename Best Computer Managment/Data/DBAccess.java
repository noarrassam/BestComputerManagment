package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Business.COrder;
import net.proteanit.sql.DbUtils;

public class DBAccess {

	protected Connection conn = null;
	protected ResultSet rs = null;
	protected Statement stm = null;
	protected String sql = null;
	protected PreparedStatement pstm = null;

	public Connection bdConnector() {
		try {

			// load the jdbc driver, name of the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loaded");

			// establish a connection
			String url = "jdbc:oracle:thin:@calvin.humber.ca:1521:GROK";
			String username = "N01335459";
			String password = "oracle";

			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected");
			return conn;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;

		}
	}

	public void disconnect() throws SQLException {
		if (!rs.isClosed()) {
			rs.close();
			conn.close();
		}
	}

	public void addOrderItem(COrder order) throws SQLException {

		// conn=this.bdConnector();
		String query = "insert into storeorderitem (orderid,itemid,serialnumber,quantity) values (?,?,?,?)";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, order.getpOrderid());
		pst.setInt(2, order.getpItemid());
		pst.setString(3, order.getpSerialnumber());
		pst.setInt(4, order.getpQuantity());

		pst.execute();
		JOptionPane.showMessageDialog(null, "Data is Saved");

		pst.close();
	}

	public void UpdateOrderItem(COrder order) throws SQLException {
		String query = "update storeorderitem set itemid='" + order.getpItemid() + "',serialnumber='"
				+ order.getpSerialnumber() + "',quantity='" + order.getpQuantity() + "' where orderid='"
				+ order.getpOrderid() + "' ";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.execute();
		JOptionPane.showMessageDialog(null, "Data is Updated");

		pst.close();
	}

	public void DeleteOrderItem(COrder order) throws SQLException {
		String query = "delete from storeorderitem where orderid='" + order.getpOrderid() + "'  ";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.execute();
		JOptionPane.showMessageDialog(null, "Data is Deleted");

		pst.close();
	}

	public void SearOrderItem(JComboBox combosearchtype, JTextField txtsearch, JTable table) throws SQLException {
		// convert the object that we get from combo to string -casting
		String selection = (String) combosearchtype.getSelectedItem();

		String query = "select oi.orderid,p.productname,oi.quantity,oi.serialnumber from storeorderitem oi inner join storeproduct p on oi.serialnumber=p.serialnumber where "
				+ selection + "=? ";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, txtsearch.getText());

		ResultSet rs = pst.executeQuery();
		// read from db and display in text boxes
		table.setModel(DbUtils.resultSetToTableModel(rs));

		pst.close();

	}

	public void ProductnameSearch(JComboBox comboitemname, JTextField txtserialnum) throws SQLException {

		String query = "select * from storeproduct where productname=? ";
		PreparedStatement pst = conn.prepareStatement(query);

		// convert the object that we get from combo to string -casting
		pst.setString(1, (String) comboitemname.getSelectedItem());

		ResultSet rs = pst.executeQuery();
		
		// read from db and display in text boxes
		while (rs.next()) {

			txtserialnum.setText(rs.getString("serialnumber"));	
		}
		pst.close();
	}

	public COrder ProductnameSearch(int a) throws SQLException {

		String query = "select oi.orderid,p.productname,oi.quantity,oi.serialnumber,oi.itemid from storeorderitem oi inner join storeproduct p on oi.serialnumber=p.serialnumber where orderid=? ";
		PreparedStatement pst = conn.prepareStatement(query);

		try {
		
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, a);
			rs = pstm.executeQuery();

			COrder order = null;
			if (rs.next()) {
				int orderid = rs.getInt("orderid");
				String productname = rs.getString("productname");
				int quantity = rs.getInt("quantity");
				String serialnumber = rs.getString("serialnumber");
				int item = rs.getInt("itemid");
				
				order = new COrder(orderid, productname, quantity, serialnumber, item);

			}
			return order;

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The table or DataBase is not exist");
			return null;
		}
		
	}

	public void Loadtable(JTable table) throws SQLException {

		String query = "select oi.orderid,oi.itemid,p.productname,oi.quantity,oi.serialnumber from storeorderitem oi inner join storeproduct p on oi.serialnumber=p.serialnumber";
		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		// convert rs to model which tb can take
		table.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
	}

	public void Refreshtabledb(JTable table) throws SQLException {
		String query = "select oi.orderid,oi.itemid,p.productname,oi.quantity,oi.serialnumber from storeorderitem oi inner join storeproduct p on oi.serialnumber=p.serialnumber";
		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		// convert rs to model which tb can take
		table.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
	}

	public void loadcomboNamedb(JComboBox comboitemname) throws SQLException {
		String query = "select * from storeproduct";
		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			comboitemname.addItem(rs.getString("PRODUCTNAME"));
		}
		pst.close();
		rs.close();
	}

	public void loadListdb(JList list) throws SQLException {
		String query = "select * from storeorder";
		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		// create model to load data
		DefaultListModel<String> DLM = new DefaultListModel<>();

		while (rs.next()) {
			DLM.addElement(rs.getString("ORDERID"));
		}
		// transfer model to the list
		list.setModel(DLM);

		pst.close();
		rs.close();
	}
}
