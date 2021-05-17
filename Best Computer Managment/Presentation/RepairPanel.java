package Presentation;

import Business.Repair;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Data.DAOFactory;
import Data.RepirDAOFactory;
public class RepairPanel extends JPanel {

	protected HelperMessagePanel messagePanel = new HelperMessagePanel("Welcomte to Java");

	private Data.RepairDAO pDAO = RepirDAOFactory.getRepairDAO();
	// panel container's components
	private JLabel lblDate,lblrepairmodify,lblrepairnumber, lblcustomername, lblproductname, lblrepairtask, lblrepaircost, lblreceivedate, lblpickdate;
	private JTextField txtrepairnumber, txtcustomername, txtproductname, txtrepaircost ,txtcustomers;
	private JButton btnrepairsave, btnrepairfind, btnrepairdelete,btnrepairedit,btndisplay,btncancle;

	// Declare an array of Strings for tasks
	private String[] tasks = { "Recovery data", "Computer Diagnostics", "Windows Installation", "Upgrade System" };
	//private String[] names = {"Reyhaneh HG","Adnan","Noor"};
	
	private String[] columnName = {"Customer Name","Ticket Number"}; 
	
	private String[] years = {"2018", "2019", "2020","2021"};
	private String[] months = {"January", "February","March", "April","May","June","July","August","September","October","November","December"};
	private String[] days = {"1", "2", "3","4", "5", "6", "7","8", "9", "10","11", "12", "13", "147","15", "16", "17","18", "19", "20", "21","22", "23", "24","25", "26", "27", "28","29", "30"};
	
	// Declare ComoBox
	private JComboBox combotask,comboname ,jcboYear, jcboMonth, jcboDay;
	
	
	private JTextArea displayarea;
	private JTable jTable;
	private DefaultTableModel dtm;

	// constructor to make panel work
	public RepairPanel() {

		this.initializealterpanel();
		// buttons work
		btnrepairsave.addActionListener(new SaveButtonHandler());
		btnrepairfind.addActionListener(new FindButtonHandler());
		btnrepairdelete.addActionListener(new DeleteButtonHandler());
		btnrepairedit.addActionListener(new EditButtonHandler());
		btndisplay.addActionListener(new DisplayButtonHandler());
		btncancle.addActionListener(new CancleButtonHandler());
		
		
		// Register listeners for combo box1
		combotask.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				messagePanel.setMessage(combotask.getSelectedItem().toString());
			}
		});
		// show first item by default
		combotask.setSelectedIndex(0);
		messagePanel.setMessage(combotask.getSelectedItem().toString());
		//////////////////////////////////////////////////////
		
		jcboYear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				messagePanel.setMessage(jcboYear.getSelectedItem().toString());
			}
		});
		// show first item by default
		jcboYear.setSelectedIndex(0);
		messagePanel.setMessage(jcboYear.getSelectedItem().toString());
		//////////////////////////////////////////////////////////
		
		jcboMonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				messagePanel.setMessage(jcboMonth.getSelectedItem().toString());
			}
		});
		// show first item by default
		jcboMonth.setSelectedIndex(0);
		messagePanel.setMessage(jcboMonth.getSelectedItem().toString());
		//////////////////////////////////////////////////////////
		
		jcboDay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				messagePanel.setMessage(jcboDay.getSelectedItem().toString());
			}
		});
		// show first item by default
		jcboDay.setSelectedIndex(0);
		messagePanel.setMessage(jcboDay.getSelectedItem().toString());
	}

	public void initializealterpanel() {

		// create 2 panels
		JPanel panelfirst = new JPanel();
		JPanel panelsecond = new JPanel();
		JPanel panelthird = new JPanel();
		JPanel panelforth = new JPanel();
		
		messagePanel.setBackground(Color.WHITE);

		//lblrepairmodify = new JLabel("To modify the ticket");
		lblrepairnumber = new JLabel("Ticket Number");
		lblcustomername = new JLabel("Customer Name");
		lblproductname = new JLabel("Product Name");
		lblrepairtask = new JLabel("Service Task");
		lblrepaircost = new JLabel("Service Cost");
		lblDate = new JLabel("Receive Date");

		txtrepairnumber = new JTextField();
		txtcustomername = new JTextField();
		txtproductname = new JTextField();
		combotask = new JComboBox(tasks);
		jcboYear = new JComboBox(years);
		jcboMonth = new JComboBox(months);
		jcboDay = new JComboBox(days);
		//comboname = new JComboBox(names);
		txtrepaircost = new JTextField();
		txtcustomers = new JTextField();

		btnrepairsave = new JButton("Save");
		btnrepairfind = new JButton("Find");
		btnrepairdelete = new JButton("Delete");
		btnrepairedit = new JButton("Edit");
		btncancle = new JButton("Cancle");
		btndisplay = new JButton("Display All");
		
		//displayarea = new JTextArea();
		
		dtm = new DefaultTableModel(columnName,0) {
			 public boolean isCellEditable(int i, int i1) {
			        return false; //To change body of generated methods, choose Tools | Templates.
			    }  			 
		};
		
		jTable = new JTable(dtm);
		//jTable.setBounds(30, 40, 200, 200); 
		JScrollPane sp = new JScrollPane(jTable); 
		//jTable.setEnabled(false);
		
		jTable.addMouseListener (new java.awt.event.MouseAdapter() {
			
			public void mouseClicked(java.awt.event.MouseEvent e)
			{
			int row =jTable.rowAtPoint(e.getPoint());
			
			//int col= jTable.columnAtPoint(e.getPoint());
			
			txtcustomername.setText(jTable.getValueAt(row,0).toString());
			txtrepairnumber.setText(jTable.getValueAt(row,1).toString());
			
			}});
		
		// panel 1
		panelfirst.setLayout(new GridLayout(6, 2));
		//panelfirst.add(lblrepairmodify);
		//panelfirst.add(comboname);
		
		panelfirst.add(lblcustomername);
		panelfirst.add(txtcustomername);
		
		panelfirst.add(lblrepairnumber);
		panelfirst.add(txtrepairnumber);

		panelfirst.add(lblproductname);
		panelfirst.add(txtproductname);

		panelfirst.add(lblrepairtask);
		panelfirst.add(combotask);

		panelfirst.add(lblrepaircost);
		panelfirst.add(txtrepaircost);
		

		// panel 2
		panelsecond.setLayout(new GridLayout(1, 6));
		panelsecond.add(btnrepairsave);
		panelsecond.add(btnrepairfind);
		panelsecond.add(btnrepairdelete);
		panelsecond.add(btnrepairedit);
		panelsecond.add(btndisplay);
		panelsecond.add(btncancle);
		
		//panel3
		//panelthird.add(txtcustomers);
		//panelthird.add(messagePanel);
		panelthird.add(sp);
		// adding it to JScrollPane 
        
		panelfirst.add(lblDate);
		//adding date to one panel
		panelforth.setLayout(new FlowLayout());	
		panelforth.add(jcboYear);
		panelforth.add(jcboMonth);
		panelforth.add(jcboDay);
		panelfirst.add(panelforth);
		
		// add panels to one panel
		setLayout(new BorderLayout());
		add(panelfirst, BorderLayout.NORTH);
		add(panelsecond, BorderLayout.SOUTH);
		add(panelthird,BorderLayout.CENTER);
	}
	
	private class SaveButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// check validation
			if(isValidData()) {
			String customername = txtcustomername.getText();
			int ticketnum = Integer.parseInt(txtrepairnumber.getText());
			String productname = txtproductname.getText();
			String servicetask = combotask.getSelectedItem().toString();
			double servicecost = Double.parseDouble(txtrepaircost.getText());
			String theyears = jcboYear.getSelectedItem().toString();
			//int theyears = Integer.parseInt(jcboYear.getSelectedItem().toString());
			String themonths = jcboMonth.getSelectedItem().toString();
			String thedays = jcboDay.getSelectedItem().toString();
			//int thedays = Integer.parseInt(jcboDay.getSelectedItem().toString());
		
			Business.Repair repair = new Repair();
			repair.setCustomername(customername);
			repair.setRepairNumber(ticketnum);
			repair.setProductname(productname);
			repair.setRepairTask(servicetask);
			repair.setRepairCost(servicecost);
			repair.setRepairyear(theyears);
			repair.setRepairmonth(themonths);
			repair.setRepairday(thedays);
			
			if(pDAO.addRepair(repair)) {
				
			String result = "New Ticket information is: \n" + "Ticket Number: " + ticketnum + "\n Customer : "
					+ customername + "\n Product : " + productname + "\n Service Task is: " + servicetask
					+ "\n Service cost is " + servicecost;
			JOptionPane.showMessageDialog(null, result + "\n Your ticket is saved", "\n New Ticket",
					JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	}
	//call isvaliddata func
	public boolean isValidData() {
		if(!Validator.isperesnt(txtcustomername, "Customer name")) return false;
		if(!Validator.isInteger(txtrepairnumber, "Ticket number")) return false;
		if(!Validator.isperesnt(txtproductname, "Product name")) return false;
		if(!Validator.isDouble(txtrepaircost, "Service Cost")) return false;
		return true;
	}

	private class FindButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// display data after click
			Business.Repair repair = pDAO.getRepair(txtcustomername.getText());
			if(repair !=null)
			{
				//for number
				txtrepairnumber.setText(String.valueOf(repair.getRepairNumber()));
				txtproductname.setText(repair.getProductname());

				// to see combo accordingly
			    combotask.setSelectedItem(repair.getRepairTask());
				txtrepaircost.setText(String.valueOf(repair.getRepairCost()));
				
				// to see combo accordingly
				jcboYear.setSelectedItem(repair.getRepairyear());
				jcboMonth.setSelectedItem(repair.getRepairmonth());
				jcboDay.setSelectedItem(repair.getRepairday());
			}
			else
				JOptionPane.showMessageDialog(null,txtcustomername.getText() + " not find.");
		}
			//JOptionPane.showMessageDialog(null, "Ticket is not found", "Find Ticket", JOptionPane.ERROR_MESSAGE);
		}

	private class DeleteButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// first we assign to array ,then search, then remove
			ArrayList<Business.Repair> repairs = pDAO.getRepairs();

			for (Business.Repair e2 : repairs) {
				if ((txtcustomername.getText().equalsIgnoreCase(e2.getCustomername()))) {
					//employees.iterator().next();
					pDAO.removeCustomer(repairs, e2);
                     
					JOptionPane.showMessageDialog(null, "  Customer " + e2.getCustomername() + " is Deleted",
							"Delete Customer", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
			//JOptionPane.showMessageDialog(null, "Ticket is deleted", "Delete Ticket", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private class EditButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(isValidData()) {
				
			//}else {
			// get all objects from list
			ArrayList<Repair> repairs = pDAO.getRepairs();
			
			if(repairs != null) {
					//&& repair.getCustomername() != null || repair.getCustomername() != "") {
				//pDAO.removeCustomer(repair);
				//check if not empty
				String customername = txtcustomername.getText();
				int ticketnum = Integer.parseInt(txtrepairnumber.getText());
				String productname = txtproductname.getText();
				String servicetask = combotask.getSelectedItem().toString();
				double servicecost = Double.parseDouble(txtrepaircost.getText());
				//int year = Integer.parseInt(jcboYear.getSelectedItem().toString());
				String year = jcboYear.getSelectedItem().toString();
				String month = jcboMonth.getSelectedItem().toString();
				String day = jcboDay.getSelectedItem().toString();
				//int day = Integer.parseInt(jcboDay.getSelectedItem().toString());
				for(Repair r: repairs) {
					if(r.getCustomername().equalsIgnoreCase(customername)){

				//Business.Repair newRepair = new Repair();
				r.setCustomername(customername);
				r.setRepairNumber(ticketnum);
				r.setProductname(productname);
				r.setRepairTask(servicetask);
				r.setRepairCost(servicecost);
				r.setRepairyear(year);
				r.setRepairmonth(month);
				r.setRepairday(day);
				
				//pDAO.addRepair(r);
				pDAO.updateRepair(repairs);

				JOptionPane.showMessageDialog(null, "Ticket is edited", "Edit Ticket", JOptionPane.INFORMATION_MESSAGE);
			}
		}}}
	}
	}
	private class CancleButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			 System.exit(0);
		   }
	}
	// to display in text field
	private class DisplayButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			ArrayList<Repair> repairs = pDAO.getRepairs();
               //messagePanel.setMessage(txtcustomername.getText());
				//txtcustomers.setText(getAllRepairs());
		
			dtm.setRowCount(0);
			for (int j = 0; j < repairs.size(); j++) {
               // displayarea.append(repairs.get(j).getCustomername() + "\n");
				
				
				String[] item = new String[2];
				item[0] = repairs.get(j).getCustomername();
				//item[1] = repairs.get(j).getProductname();
				item[1] = Integer.toString(repairs.get(j).getRepairNumber());
				dtm.addRow(item);
            }
			}
		}

//	private String getAllRepairs() {
//		String result = "\n";
//		ArrayList<Repair> repairs = pDAO.getRepairs();
//		for (Repair repair : repairs) {
//			
//			result += repair.getCustomername() + "      " + repair.getRepairNumber() + " \n " ;
//			//result += " \n ";
//		}
//		return result;
//	}
}
