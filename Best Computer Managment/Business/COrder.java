package Business;

public class COrder {

	private String pSerialnumber, pProductName;
	private int pOrderid, pItemid, pQuantity;

	public String getpProductName() {
		return pProductName;
	}

	public void setpProductName(String pProductName) {
		this.pProductName = pProductName;
	}

	public String getpSerialnumber() {
		return pSerialnumber;
	}

	public void setpSerialnumber(String pSerialnumber) {
		this.pSerialnumber = pSerialnumber;
	}

	public int getpOrderid() {
		return pOrderid;
	}

	public void setpOrderid(int pOrderid) {
		this.pOrderid = pOrderid;
	}

	public int getpQuantity() {
		return pQuantity;
	}

	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}

	public int getpItemid() {
		return pItemid;
	}

	public void setpItemid(int pItemid) {
		this.pItemid = pItemid;
	}

	public COrder(String pSerialnumber, String pProductName, int pOrderid, int pItemid, int pQuantity) {
		this.pProductName = pProductName;
		this.pSerialnumber = pSerialnumber;
		this.pOrderid = pOrderid;
		this.pItemid = pItemid;
		this.pQuantity = pQuantity;

	}

	public COrder(int pOrderid, String pProductName, int pQuantity, String pSerialnumber, int pItemid) {

		this.pOrderid = pOrderid;
		this.pProductName = pProductName;
		this.pQuantity = pQuantity;
		this.pSerialnumber = pSerialnumber;
		this.pItemid = pItemid;

	}

	public COrder(String pSerialnumber, int pOrderid, int pItemid, int pQuantity) {
		this.pProductName = pProductName;
		this.pSerialnumber = pSerialnumber;
		this.pOrderid = pOrderid;
		this.pItemid = pItemid;
		this.pQuantity = pQuantity;

	}

	public COrder(int pOrderid, int pItemid, String pSerialnumber, int pQuantity) {
		// this.pProductName = pProductName;
		this.pSerialnumber = pSerialnumber;
		this.pOrderid = pOrderid;
		this.pItemid = pItemid;
		this.pQuantity = pQuantity;

	}

	public COrder(int pOrderid, int pItemid, int pQuantity) {

		// this.pSerialnumber = pSerialnumber;
		this.pOrderid = pOrderid;
		this.pItemid = pItemid;
		this.pQuantity = pQuantity;
	}

	public COrder() {

	}

	public String toString() {
		return pItemid + "[ " + pOrderid + " ] , " + pSerialnumber + " , " + pQuantity;
	}
}
