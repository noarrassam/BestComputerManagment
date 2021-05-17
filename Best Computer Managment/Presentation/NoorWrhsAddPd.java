package Presentation;

import javax.swing.JPanel;
import java.sql.*;
import java.awt.Color;
import java.awt.Component;
import net.proteanit.sql.DbUtils;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Business.NoorProgram;
import Data.NoorDBAccess;

import Presentation.ValidateData;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JSpinner;

public class NoorWrhsAddPd extends JPanel {
	private JTextField txtSerial;
	private JTextField txtCost;
	private JTextField txtRetail;
	private JTextArea txtComments;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnNext;
	private JButton btnUpdate;
	private JButton btnPrevious;
	private JComboBox jcCategory, jcQtny, jcPdName, jcAisle;
	private JDateChooser txtPrDate, txtPrchDt;
	private NoorDBAccess db=null;
	protected Connection conn=null;
	protected ResultSet rs=null;
	protected Statement stm=null;
	protected PreparedStatement pst=null;
	private JTable table;
	
	public void showProgram(NoorProgram program) {
		txtSerial.setText(program.getSerialNum());
		jcPdName.setSelectedItem(program.getItem());
		txtPrDate.setDate(program.getPdDate());
		txtPrchDt.setDate(program.getPrchdate());	
		txtCost.setText(""+program.getCost());
		txtRetail.setText(""+program.getRetail());
		jcQtny.setSelectedItem(program.getQnty());
		jcAisle.setSelectedItem(program.getAisle());
		jcCategory.setSelectedItem(program.getCategory());
		txtComments.setText(""+program.getComnt());
	}
		
	public NoorWrhsAddPd() {		
				
		setBackground(Color.ORANGE);
		
		txtRetail = new JTextField();
		txtRetail.setColumns(10);
		
		txtSerial = new JTextField();
		txtSerial.setColumns(10);
		
		JLabel lblSerial = new JLabel("Serial Number:");
		lblSerial.setDisplayedMnemonic(KeyEvent.VK_S);
		lblSerial.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblDate = new JLabel("Production Date:");
		lblDate.setDisplayedMnemonic(KeyEvent.VK_P);
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		txtCost = new JTextField();
		txtCost.setColumns(10);
		
		JLabel lblCost = new JLabel("Cost:");
		lblCost.setDisplayedMnemonic(KeyEvent.VK_C);
		lblCost.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setDisplayedMnemonic(KeyEvent.VK_M);
		lblComments.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblItem = new JLabel("Select Item:");
		lblItem.setDisplayedMnemonic(KeyEvent.VK_M);
		lblItem.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblQuantity = new JLabel("Select Quantity:");
		lblQuantity.setDisplayedMnemonic(KeyEvent.VK_Q);
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblAisle = new JLabel("Select Aisle:");
		lblAisle.setDisplayedMnemonic(KeyEvent.VK_A);
		lblAisle.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblPurchDt = new JLabel("Purchasing Date:");
		lblPurchDt.setDisplayedMnemonic(KeyEvent.VK_D);
		lblPurchDt.setFont(new Font("Times New Roman", Font.BOLD, 15));
				
		JLabel lblRetail = new JLabel("Retail:");
		lblRetail.setDisplayedMnemonic(KeyEvent.VK_R);
		lblRetail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		 
		JLabel lblCateg = new JLabel("Select Category:");
		lblCateg.setDisplayedMnemonic(KeyEvent.VK_C);
		lblCateg.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		jcCategory = new JComboBox();
		jcCategory.setModel(new DefaultComboBoxModel(new String[] {"Input devices", "Processing devices", "Output devices"}));
		jcCategory.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		jcQtny = new JComboBox();
		jcQtny.setFont(new Font("Times New Roman", Font.BOLD, 12));
		jcQtny.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}));

		jcPdName = new JComboBox();
		jcPdName.setModel(new DefaultComboBoxModel(new String[] {"Keyboard", "Mouse", "Light pen", "Track Ball", "Scanner", "Microphone", "Optical Character Reader(OCR)", "Magnetic Ink Card Reader(MICR)", "Monitors", "Speakers", "Headphones", "Printers", "Motherboard", "Network card", "Sound card", "Video card"}));
		jcPdName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		jcAisle = new JComboBox();
		jcAisle.setFont(new Font("Times New Roman", Font.BOLD, 12));
		jcAisle.setModel(new DefaultComboBoxModel(new String[] {"Shelve A", "Shelve B", "Shelve C", "Shelve D", "Shelve E", "Shelve F", "Shelve G", "Shelve H", "Shelve I", "Shelve J", "Shelve K"}));
		
		txtPrDate = new JDateChooser();
		txtPrDate.setDateFormatString("MM/dd/yyyy");
		txtPrDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		txtPrchDt = new JDateChooser();
		txtPrchDt.setDateFormatString("MM/dd/yyyy");
		txtPrchDt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setMnemonic(KeyEvent.VK_A);
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnAdd.setForeground(Color.BLACK);
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(ValidateData.isPresent(txtSerial, "Serial Number"))
					//if(ValidateData.isPresent(txtItem, "Product Name"))
						if(ValidateData.isDate(txtPrDate, "Producing Date"))
							if(ValidateData.isDate(txtPrchDt, "Purchasing Date"))
								if(ValidateData.isInt(txtCost, "Cost"))
									if(ValidateData.isInt(txtCost, "Cost"))
										//if(ValidateData.isSpin(spinQuantity, "Quantity"))
									//if(ValidateData.isPresent(txtAisle, "Aisle"))
										//if(ValidateData.isPresent(txtCateg, "Category"))
											if(ValidateData.isPresent(txtComments, "Comments")){
												String Combo = jcPdName.getSelectedItem().toString();
												String Combo1 = jcQtny.getSelectedItem().toString();
												String Combo2 = jcCategory.getSelectedItem().toString();
												String Combo3 = jcAisle.getSelectedItem().toString();
												Date date = new Date(txtPrDate.getDate().getTime());
												Date date1 = new Date(txtPrchDt.getDate().getTime());												
												NoorProgram program = new NoorProgram(txtSerial.getText(), Combo , date, date1,Integer.parseInt(txtCost.getText()), Integer.parseInt(txtRetail.getText()), Combo1, Combo3, Combo2, txtComments.getText());
												
								try
								{
									db.addProgram(program);
									JOptionPane.showMessageDialog(null, "Program is added successfully");
								} catch(SQLException e1) {
									showException(e1);
								}
							}
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setMnemonic(KeyEvent.VK_D);
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnDelete.setForeground(Color.BLACK);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try{
						db.deleteProgram(txtSerial.getText());
						JOptionPane.showMessageDialog(null,"Deleted successfully");
					}
					catch(SQLException e1) {
						JOptionPane.showMessageDialog(null,"Deleted Unsuccessfully");	
					}
				}});
		
		JButton btnNext = new JButton("Next");
		btnNext.setMnemonic(KeyEvent.VK_N);
		btnNext.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NoorProgram program=db.getNext();
					if(program!=null)
						showProgram(program);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					showException(e1);
				}
			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ValidateData.isPresent(txtSerial, "Serial Number"))
						if(ValidateData.isDate(txtPrDate, "Producing Date"))
							if(ValidateData.isDate(txtPrchDt, "Purchasing Date"))
								if(ValidateData.isInt(txtCost, "Cost"))
									if(ValidateData.isInt(txtRetail, "Retail"))
											if(ValidateData.isPresent(txtComments, "Comments")){
												String Combo = jcPdName.getSelectedItem().toString();
												String Combo1 = jcQtny.getSelectedItem().toString();
												String Combo2 = jcCategory.getSelectedItem().toString();
												String Combo3 = jcAisle.getSelectedItem().toString();
												Date date = new Date(txtPrDate.getDate().getTime());
												Date date1 = new Date(txtPrchDt.getDate().getTime());												
												NoorProgram program = new NoorProgram(txtSerial.getText(), Combo , date, date1,Integer.parseInt(txtCost.getText()), Integer.parseInt(txtRetail.getText()),Combo1, Combo3, Combo2, txtComments.getText());				
				try{
						db.updateProgram(program);					
						JOptionPane.showMessageDialog(null,"Updated successfully");
					}
				 catch(Exception e1) {
					 showException(e1);	
					}
				}
			}});
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setMnemonic(KeyEvent.VK_P);
		btnPrevious.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NoorProgram program=db.getPrevious();
					if(program!=null)
						showProgram(program);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					showException(e1);
				}
			}
		});
		
		JButton btnFirst = new JButton("First");
		btnFirst.setMnemonic(KeyEvent.VK_F);
		btnFirst.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NoorProgram program=db.getFirst();
					if(program!=null)
						showProgram(program);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					showException(e1);
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				}			
		});
		
		JButton btnLoad = new JButton("Load Table");
		btnLoad.setMnemonic(KeyEvent.VK_L);
		btnLoad.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q = "Select * From StoreProduct where ProductName = ?";

				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					String url = "jdbc:oracle:thin:@CALVIN.HUMBER.CA:1521:GROK";
					String username = "N01335459";
					String password = "oracle";
					conn = DriverManager.getConnection(url, username,password);
					PreparedStatement rs1 = conn.prepareStatement(q);
					rs1.setString(1, jcPdName.getSelectedItem().toString());
					ResultSet set =rs1.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(set));
					}
					catch(Exception e1){
					showException(e1);				
					}
			}
		});		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JList list = new JList();
		
		
			
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnFirst, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
										.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
										.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
										.addComponent(btnPrevious, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblQuantity)
										.addComponent(lblAisle, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSerial, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblItem, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCateg)
										.addComponent(lblPurchDt, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCost, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblComments, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRetail, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(list, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
										.addComponent(jcCategory, 0, 238, Short.MAX_VALUE)
										.addComponent(jcAisle, 0, 238, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(2)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtPrDate, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
												.addComponent(txtCost, Alignment.TRAILING, 236, 236, Short.MAX_VALUE)
												.addComponent(txtRetail, Alignment.TRAILING, 236, 236, Short.MAX_VALUE)
												.addComponent(jcQtny, 0, 236, Short.MAX_VALUE)
												.addComponent(jcPdName, 0, 236, Short.MAX_VALUE)
												.addComponent(txtPrchDt, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
												.addComponent(txtSerial, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))))
									.addGap(16)))
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 853, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnLoad)
							.addGap(389))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSerial)
								.addComponent(txtSerial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jcPdName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblItem))
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDate)
								.addComponent(txtPrDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPurchDt, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtPrchDt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCost))))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtRetail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRetail, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblQuantity)
								.addComponent(jcQtny, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAisle)
								.addComponent(jcAisle, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCateg)
								.addComponent(jcCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblComments)
								.addComponent(list))
							.addGap(18)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd)
								.addComponent(btnUpdate)
								.addComponent(btnDelete))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnFirst)
								.addComponent(btnPrevious)
								.addComponent(btnNext)))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
					.addContainerGap())
		);
		txtComments = new JTextArea();
		scrollPane_1.setViewportView(txtComments);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int rows = table.getRowCount();
				
				if(rows !=0)
				{
					int row_selected =table.getSelectedRow();
					String id = table.getValueAt(row_selected, 0).toString();
					String pNme = table.getValueAt(row_selected, 1).toString();
					Object date = table.getValueAt(row_selected, 2);
					Object date1 = table.getValueAt(row_selected, 3);
					String cost = table.getValueAt(row_selected, 4).toString();
					String retail = table.getValueAt(row_selected, 5).toString();
					String qnty = table.getValueAt(row_selected, 6).toString();
					String aisle = table.getValueAt(row_selected, 7).toString();
					String Category = table.getValueAt(row_selected, 8).toString();
					String cmnts = table.getValueAt(row_selected, 9).toString();
		
					txtSerial.setText(id);
					jcPdName.setSelectedItem(pNme);
					txtPrDate.setDate((java.util.Date)date);
					txtPrchDt.setDate((java.util.Date)date);
					txtCost.setText(cost);
					txtRetail.setText(retail);
					jcQtny.setSelectedItem(qnty);
					jcAisle.setSelectedItem(aisle);
					jcCategory.setSelectedItem(Category);
					txtComments.setText(cmnts);					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Select a Row");
				}
			}
		});
			
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Comments", "Category", "Aisle", "Quantity", "Retail", "Cost", "PurchaseDate", "ProducingDate", "ProductName"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

		try {
			db=new NoorDBAccess();
			NoorProgram program=db.getFirst();
			if(program!=null)
				showProgram(program);
		}
		catch(ClassNotFoundException | SQLException sqle) {
			showException(sqle);
			System.exit(1);
		}
		
		//ShowTableData();
	}
	private void showException(Exception e) {
		JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	}
}