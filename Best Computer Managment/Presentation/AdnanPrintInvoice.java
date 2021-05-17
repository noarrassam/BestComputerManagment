package Presentation;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

import Business.AdnanStoreInvoice;
import Business.AdnanStoreInvoiceDetails;
import Data.AdnanInvoiceDetailesDBAccess;

import java.awt.Dimension;
import javax.swing.JTextArea;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//This form for print
public class AdnanPrintInvoice extends JInternalFrame {

	private JTextArea txtArea;
	AdnanInvoiceDetailesDBAccess db=new AdnanInvoiceDetailesDBAccess();
	public AdnanPrintInvoice() {
	//	setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		setVisible(true);
		setTitle("Best Computer Invoice print Form ");
		JScrollPane textAreaScrollpane = new JScrollPane((Component) null);
		textAreaScrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textAreaScrollpane.setPreferredSize(new Dimension(800, 350));
		textAreaScrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		textAreaScrollpane.setBounds(25, 35, 950, 450);
		getContentPane().add(textAreaScrollpane);
		txtArea = new JTextArea(20, 200);
		txtArea.setWrapStyleWord(true);
		txtArea.setText("\n"+"    "+"===================================================================================\n"
		+"\t\t"+"     "+"Welcome to Best Computer\n"
		+"\t\t"+"    "+"Contact Number 67400000 \n"
		+"\t\t"+"    "+"20 Humber St-Toronto-Ontario\n"
		+"    "+"===================================================================================\n");
		txtArea.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textAreaScrollpane.setViewportView(txtArea);
		
		JPanel bottomPane = new JPanel();
		bottomPane.setPreferredSize(new Dimension(195, 33));
		bottomPane.setBounds(20, 486, 757, 40);
		getContentPane().add(bottomPane);
		bottomPane.setLayout(null);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtArea.print();
				} catch (Exception eX) {

				}
			}
		});
		btnPrint.setBounds(400, 11, 130, 29);
		btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bottomPane.add(btnPrint);
		
		JButton btnclose = new JButton("Close");
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnclose.setBounds(550, 11, 130, 29);
		btnclose.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bottomPane.add(btnclose);

	}
	public JTextArea getTextArea() {
		return this.txtArea;
	}

	//this method to print invoice
		public void printInvoice1(JTextArea textArea,int invoiceNum) {
		AdnanStoreInvoice foundInvoice=null;
		foundInvoice=db.getStoreInvoice(invoiceNum);
		if (foundInvoice!=null) {
			textArea.append("    "+"Invoice ID"+"\t");
			textArea.append("    "+"Invoice Date"+"\t");
			textArea.append("    "+"Net Price"+"\t");
			textArea.append("    "+"Tax"+"\t\t"+"               ");
			textArea.append("    "+"Total Price"+"\n");
			 textArea.append("    "+"==================================================================================="+"\n");
		textArea.append(String.valueOf("    "+foundInvoice.getInvoiceID())+"\t");
		textArea.append(String.valueOf("  "+foundInvoice.getInvoiceDate())+"\t");
		textArea.append(String.valueOf("   "+foundInvoice.getInvoicePrice())+"\t");
		textArea.append(String.valueOf("   "+foundInvoice.getInvoiceTax())+"\t");
		textArea.append(String.valueOf("\t\t"+foundInvoice.getTotalInvoicPrice())+"\n");
		 textArea.append("    "+"==================================================================================="+"\n");
		
		textArea.append("    "+"Code"+"\t");
		textArea.append("Item Name"+"\t");
		textArea.append("Unit Price"+"    ");
		textArea.append("Count"+"    ");
		textArea.append("Discount"+"    ");
		textArea.append("Net Price"+"\t");
		textArea.append("Tax"+"\t");
		textArea.append("Total Item Price"+"\n");
		 textArea.append("    "+"==================================================================================="+"\n");

	ArrayList<AdnanStoreInvoiceDetails> items=new ArrayList<AdnanStoreInvoiceDetails>();
	//AdnanStoreInvoiceDetails item1=db.getAllInvoiceItems(item.getSerialNumber());
	items=db.getAllInvoiceItems(invoiceNum);
	 for(AdnanStoreInvoiceDetails item : items) {
	AdnanStoreInvoiceDetails item1=db.getProduct(item.getSerialNumber());
	textArea.append(String.valueOf("    "+item.getSerialNumber())+"\t");
	textArea.append(String.valueOf(item1.getProductName())+"\t");
	textArea.append(String.valueOf("  "+item.getUnitPrice())+"\t");
	textArea.append(String.valueOf(item.getQuantity())+"           ");
	textArea.append(String.valueOf(item.getDiscount())+"            ");
	textArea.append(String.valueOf(item.getNetPrice())+"\t");
	textArea.append(String.valueOf(item.getTotalItemTax())+"\t"+"    ");
	textArea.append(String.valueOf(item.getTotalItemPrice())+"\n");
	 
	 }	
	 textArea.append("    "+"==================================================================================="+"\n");
	 textArea.append("    "+"\t\t\t\t\t       Total Price:   "+String.valueOf(foundInvoice.getTotalInvoicPrice()));	

		}
	}
}
