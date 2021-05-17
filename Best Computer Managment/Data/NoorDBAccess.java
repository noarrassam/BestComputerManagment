package Data;

import java.sql.*;

import javax.swing.JOptionPane;

import Business.NoorProgram;
public class NoorDBAccess {
	protected Connection conn=null;
	protected ResultSet rs=null;
	protected Statement stm=null;
	
	public NoorDBAccess() throws SQLException, ClassNotFoundException 
	{
		this.connect();
	}
	
	protected void connect() throws SQLException, ClassNotFoundException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@CALVIN.HUMBER.CA:1521:GROK";
		String username = "N01335459";
		String password = "oracle";
		conn = DriverManager.getConnection(url, username,password);
		String sql = "Select * From StoreProduct";
		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs = stm.executeQuery(sql);
	}
	
	public void disconnect() throws SQLException 
	{
		if(!rs.isClosed())
		{
			rs.close();
			conn.close();
		}
	}
	
	public void refresh() throws SQLException
	{
		String sql = "Select * From StoreProduct";
		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = stm.executeQuery(sql);
		rs.first();
	}
	
	public int addProgram(NoorProgram program) throws SQLException
	{
		int count = 0;
		String query = "Insert into StoreProduct values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, program.getSerialNum());
	    preparedStmt.setString(2, program.getItem());
	    preparedStmt.setDate(3, program.getPdDate());
	    preparedStmt.setDate(4, program.getPrchdate());
	    preparedStmt.setInt(5, program.getCost());
	    preparedStmt.setInt(6, program.getRetail());
	    preparedStmt.setString(7, program.getQnty());
	    preparedStmt.setString(8, program.getAisle());
	    preparedStmt.setString(9, program.getCategory());
	    preparedStmt.setString(10, program.getComnt());
	    preparedStmt.execute();
	    this.refresh();
		return count;		
	}
	
	public int deleteProgram(String Serial) throws SQLException
	{
		int count=0;
		String sql = "delete from StoreProduct where SerialNumber = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
	    pst.setString(1, Serial);
		pst.execute();
		this.refresh();
		return count;		
	}
	
	public int updateProgram(NoorProgram program) throws Exception{
		int count=0;
		String query = "update StoreProduct set ProductName=?, ProducingDate=?, PurchaseDate=?, Cost=?, Retail=?, Quantity=?, Aisle=?, Category=?, Comments=? where SerialNumber=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
	    preparedStmt.setString(1, program.getItem());
	    preparedStmt.setDate(2, program.getPdDate());
	    preparedStmt.setDate(3, program.getPrchdate());
	    preparedStmt.setInt(4, program.getCost());
	    preparedStmt.setInt(5, program.getRetail());
	    preparedStmt.setString(6, program.getQnty());
	    preparedStmt.setString(7, program.getAisle());
	    preparedStmt.setString(8, program.getCategory());
	    preparedStmt.setString(9, program.getComnt());
	    preparedStmt.setString(10, program.getSerialNum());
		preparedStmt.execute();
		this.refresh();
		return count;			
		}
	
	public NoorProgram getNext() throws SQLException{
		NoorProgram program=null;
		if(rs!=null) {
			if (!rs.isLast()) {
				rs.next();
				program=this.getProgram();
			}
		}
		return program;
	}
	
	public NoorProgram getPrevious() throws SQLException{
		NoorProgram program=null;
		if(rs!=null) {
			if (!rs.isFirst()) {
				rs.previous();
				program=this.getProgram();
			}
		}
		return program;
	}
	
	public NoorProgram getFirst() throws SQLException{
		NoorProgram program=null;
		if(rs!=null) {
				rs.first();
				program=this.getProgram();
		}
		return program;
	}
	
	protected NoorProgram getProgram() throws SQLException{
		NoorProgram program=null;
		if(rs!=null) {
			program=new NoorProgram();
			program.setSerialNum(rs.getString("SerialNumber"));
			program.setItem(rs.getString("ProductName"));
			program.setPdDate(rs.getDate("ProducingDate"));
			program.setPrchdate(rs.getDate("PurchaseDate"));
			program.setCost(rs.getInt("Cost"));
			program.setRetail(rs.getInt("Retail"));
			program.setQnty(rs.getString("Quantity"));
			program.setAisle(rs.getString("Aisle"));
			program.setCategory(rs.getString("Category"));
			program.setComnt(rs.getString("Comments"));			
		}
		return program;			
	}
}