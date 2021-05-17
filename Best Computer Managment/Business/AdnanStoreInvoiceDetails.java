package Business;

public class AdnanStoreInvoiceDetails {
//Variable Declaration
	private int invoiceId;
	private String serialNumber;
	private String productName;
	private double unitPrice;
	private int quantity;
	private double discount;
	private double totalItemTax;
	private double totalItemPrice;
	private double netPrice;
	private int totalQuantity;

	// Constructors
	public AdnanStoreInvoiceDetails() {
	}

	public AdnanStoreInvoiceDetails(String serialNumber, String productName, double unitPrice) {
		this.serialNumber = serialNumber;
		this.productName = productName;
		this.unitPrice = unitPrice;
	}

	public AdnanStoreInvoiceDetails(String serialNumber, String productName, double unitPrice, int totalQuantity) {
		this.serialNumber = serialNumber;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.totalQuantity = totalQuantity;
	}

	public AdnanStoreInvoiceDetails(int invoiceId, String serialNumber, double unitPrice, int quantity, double discount,
			double totalItemTax, double totalItemPrice, double netPrice) {
		this.invoiceId = invoiceId;
		this.serialNumber = serialNumber;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.discount = discount;
		this.netPrice = netPrice;
		this.totalItemTax = totalItemTax;
		this.totalItemPrice = totalItemPrice;
	}

	// setters and getters
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}

	public double getNetPrice() {
		return this.netPrice;
	}

	public void setTotalItemTax(double totalItemTax) {
		this.totalItemTax = totalItemTax;
	}

	public double getTotalItemTax() {
		return this.totalItemTax;
	}

	public void setTotalItemPrice(double totalItemPrice) {
		this.totalItemPrice = totalItemPrice;
	}

	public double getTotalItemPrice() {
		return this.totalItemPrice;
	}

	public void settotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int gettotalQuantity() {
		return this.totalQuantity;
	}

}
