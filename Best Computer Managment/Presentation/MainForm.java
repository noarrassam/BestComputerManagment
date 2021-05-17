package Presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class MainForm extends JFrame {
	/*
	 * Instance Variable contains one Menu Bar and main Menus such as file, record
	 * and many Menu Items which will be as sub Menu like logout, exit, products and
	 * so on
	 */
	private JMenuBar mainMenueBar;
	static JDesktopPane desktop;
	private JMenu fileMenu, recordMenu, processMenu, viewMenu, helpMenu;
	private JMenu WareHouseMenu, itemsMenu, trackMenu;
	private JMenuItem file_exitItem, file_logouttItem, file_loginttItem;
	private JMenuItem record_customerItem, record_productItem, record_WareHouseItem, record_employeeItem,
			record_ServicesItem;
	private JMenuItem costItem, budgetItem, expensesItem, profitItem, mSearchItem;
	private JMenuItem addItem, returnItem, searchItem, trackReturnDateItem, trackArivalDateItem;
	private JMenuItem InvoiceItem, ordersItem;

	// The Main Constructor
	public MainForm() {
		this.initialize();
		this.setLocationRelativeTo(null);
	}

	/*
	 * Initialize the form, we add the Menu_items to the Menu then all add to the
	 * menu bar
	 */
	public void initialize() {
		this.setSize(1400, 750);
		// Initialize the DeskTop Pane
		desktop = new JDesktopPane();
		this.setContentPane(desktop);
		// Initialize the Main Menu Bar
		mainMenueBar = new JMenuBar();
		this.setJMenuBar(mainMenueBar);
		this.setTitle("Best Computer Store");
		/* Beginning File Menu */
		/* Initialize File Main Menu and Menu_Items(LogOut,Exit) */
		fileMenu = new JMenu("File");
		mainMenueBar.add(fileMenu);
		fileMenu.setMnemonic(KeyEvent.VK_F);// Press ALT+F
		fileMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));
		// Log Out
		file_logouttItem = new JMenuItem("Logout");
		fileMenu.add(file_logouttItem);
		file_logouttItem.setMnemonic(KeyEvent.VK_L);
		file_logouttItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		file_logouttItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		file_logouttItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// JInternalFrame inFm = new InternalMainForm(new WrHsLognPanel());
				dispose();
				WrHsLognPanel inFm = new WrHsLognPanel();
				inFm.setSize(720, 300);
				inFm.setVisible(true);
				// MainForm.this.desktop.add(inFm);
				inFm.setLocation(300, 100);
				inFm.setTitle("Best Computer LogIn Form");
				inFm.toFront();
			}
		});

		// Exit
		file_exitItem = new JMenuItem("Exit");
		fileMenu.add(file_exitItem);
		file_exitItem.setMnemonic(KeyEvent.VK_X);
		file_exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		file_exitItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		file_exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		/* Ending File Menu */
		/* Beginning Record Menu */
		/*
		 * Initialize Record Main Menu and
		 * Menu_Items(Employees,Customers,Products,Alteration)
		 */
		// Main Records Menu
		recordMenu = new JMenu("Records");
		mainMenueBar.add(recordMenu);
		recordMenu.setMnemonic(KeyEvent.VK_R);// Press ALT+R
		recordMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));

		// Employees
		record_employeeItem = new JMenuItem("Employees");
		recordMenu.add(record_employeeItem);
		record_employeeItem.setMnemonic(KeyEvent.VK_M);
		record_employeeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		record_employeeItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		record_employeeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeDisplay internalProduct = new EmployeeDisplay();
				MainForm.this.desktop.add(internalProduct);
				internalProduct.setResizable(false);
				internalProduct.setSize(1200, 600);
				internalProduct.show();
				internalProduct.setLocation(50, 10);
			}
		});

		// Services
		record_ServicesItem = new JMenuItem("Services");
		recordMenu.add(record_ServicesItem);
		record_ServicesItem.setMnemonic(KeyEvent.VK_A);
		record_ServicesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		record_ServicesItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		record_ServicesItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame inFm = new InternalMainForm(new RepairPanel());
				inFm.setSize(700, 500);
				inFm.setVisible(true);
				MainForm.this.desktop.add(inFm);
				inFm.setLocation(300, 50);
				inFm.setTitle("Best Computer Sevices Form");
				inFm.moveToFront();
			}
		});
		/* Ending Record Menu */

		/* Beginning WareHouse Menu */
		// Main WareHouse main Menu
		WareHouseMenu = new JMenu("WareHouse");
		mainMenueBar.add(WareHouseMenu);
		WareHouseMenu.setMnemonic(KeyEvent.VK_W);// Press ALT+W
		WareHouseMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));
		addItem = new JMenuItem("Add Items");
		// itemsMenu.add(addItem);
		addItem.setMnemonic(KeyEvent.VK_D);
		addItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		addItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		WareHouseMenu.add(addItem);
		addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame inFm = new InternalMainForm(new NoorWrhsAddPd());
				inFm.setSize(1300, 710);
				inFm.setVisible(true);
				MainForm.this.desktop.add(inFm);
				inFm.setLocation(5, 5);
				inFm.setTitle("Best Computer Add Items Form");
				inFm.moveToFront();
			}
		});

		/* Ending WareHouse Menu */
		/* Beginning process Menu */

		/* Beginning process Menu */
		/*
		 * Initialize process Main Menu and Menu Items(Business Activities,Orders)
		 */
		// Main process Menu
		processMenu = new JMenu("Process");
		mainMenueBar.add(processMenu);
		processMenu.setMnemonic(KeyEvent.VK_P);// Press ALT+P
		processMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));
		// Business Activities
		InvoiceItem = new JMenuItem("Invoices");
		processMenu.add(InvoiceItem);
		InvoiceItem.setMnemonic(KeyEvent.VK_C);
		InvoiceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		InvoiceItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		InvoiceItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdnanInvoice internalProduct = new AdnanInvoice();
				MainForm.this.desktop.add(internalProduct);
				internalProduct.setResizable(false);
				internalProduct.setSize(1250, 600);
				internalProduct.show();
				internalProduct.setLocation(40, 10);
			}
		});
		// Orders
		ordersItem = new JMenuItem("Orders");
		processMenu.add(ordersItem);
		ordersItem.setMnemonic(KeyEvent.VK_O);
		ordersItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		ordersItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		ordersItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ReyhanehGUI orderForm = new ReyhanehGUI();
				MainForm.this.desktop.add(orderForm);
				orderForm.setResizable(false);
				orderForm.setSize(1000, 650);
				orderForm.show();
				orderForm.setLocation(40, 10);
				orderForm.setTitle("Best Computer Orders Form");
				orderForm.setClosable(true);
				orderForm.setIconifiable(true);
			}
		});
		/* Ending process Menu */
	}


	// This Method To add form
	public static void formModifyEmployee() {
		ModifyEmployee form = new ModifyEmployee();
		MainForm.desktop.add(form, Integer.valueOf(5));
		form.setSize(400, 600);
		form.requestFocus(false);
		form.setIconifiable(true);
		form.setLocation(50, 10);
		form.setClosable(true);
		form.moveToFront();
		// this method to make the form fixed
		BasicInternalFrameUI ui = (BasicInternalFrameUI) form.getUI();
		for (MouseListener listner : ui.getNorthPane().getMouseListeners()) {
			ui.getNorthPane().removeMouseListener(listner);
		}

		// This Method To add form
	}

	public static void formInvoice() {
		ModifyEmployee form = new ModifyEmployee();
		MainForm.desktop.add(form, Integer.valueOf(5));
		form.setSize(400, 600);
		form.requestFocus(false);
		form.setIconifiable(true);
		form.setLocation(50, 10);
		form.setClosable(true);
		form.moveToFront();
		BasicInternalFrameUI ui = (BasicInternalFrameUI) form.getUI();
		for (MouseListener listner : ui.getNorthPane().getMouseListeners()) {
			ui.getNorthPane().removeMouseListener(listner);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainForm().setVisible(true);
	}

}