package Business;

import java.util.Date;

public class AdnanStoreInvoice {
	// Variable Declaration
	private int invoiceID;
	private Date invoiceDate;
	private double invoicePrice;
	private double invoiceTax;
	private double totalInvoicPrice;

	// Constructors
	public AdnanStoreInvoice() {

	}

	public AdnanStoreInvoice(int invoiceID, Date invoiceDate, double invoicePrice, double invoiceTax,
			double totalInvoicPrice) {
		this.invoiceID = invoiceID;
		this.invoiceDate = invoiceDate;
		this.invoicePrice = invoicePrice;
		this.invoiceTax = invoiceTax;
		this.totalInvoicPrice = totalInvoicPrice;
	}

	// Setters and Getter
	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public int getInvoiceID() {
		return this.invoiceID;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoicePrice(double invoicePrice) {
		this.invoicePrice = invoicePrice;
	}

	public double getInvoicePrice() {
		return this.invoicePrice;
	}

	public void setInvoiceTax(double invoiceTax) {
		this.invoiceTax = invoiceTax;
	}

	public double getInvoiceTax() {
		return this.invoiceTax;
	}

	public void setTotalInvoicPrice(double totalInvoicPrice) {
		this.totalInvoicPrice = totalInvoicPrice;
	}

	public double getTotalInvoicPrice() {
		return this.totalInvoicPrice;
	}

}
