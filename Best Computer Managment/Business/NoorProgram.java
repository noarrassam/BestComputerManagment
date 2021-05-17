package Business;

import java.sql.Date;

public class NoorProgram {

	private String serialNm, pItem, pCategory, pAisle, pComment, pQtny;
	private int pSemesters, pCost, pRetail;
	private Date pdate, prchdate;

	public String getSerialNum() {
		return serialNm;
	}

	public void setSerialNum(String serialNm) {
		this.serialNm = serialNm;
	}

	public String getItem() {
		return pItem;
	}

	public void setItem(String pName) {
		this.pItem = pName;
	}

	public Date getPdDate() {
		return pdate;
	}

	public void setPdDate(Date date) {
		this.pdate = date;
	}

	public int getCost() {
		return pCost;
	}

	public void setCost(int pCost) {
		this.pCost = pCost;
	}

	public String getQnty() {
		return pQtny;
	}

	public void setQnty(String pQtny) {
		this.pQtny = pQtny;
	}

	public String getAisle() {
		return pAisle;
	}

	public void setAisle(String pAisle) {
		this.pAisle = pAisle;
	}

	public String getCategory() {
		return pCategory;
	}

	public void setCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	public String getComnt() {
		return pComment;
	}

	public void setComnt(String pComment) {
		this.pComment = pComment;
	}

	public Date getPrchdate() {
		return prchdate;
	}

	public void setPrchdate(Date prchdate) {
		this.prchdate = prchdate;
	}

	public int getRetail() {
		return pRetail;
	}

	public void setRetail(int pRetail) {
		this.pRetail = pRetail;
	}

	public NoorProgram() {
	}

	public NoorProgram(String serialNm, String pItem, Date pdate, Date prchdate, int pCost, int pRetail, String pQtny,
			String pAisle, String pCategory, String pComment) {
		this.serialNm = serialNm;
		this.pItem = pItem;
		this.pdate = pdate;
		this.prchdate = prchdate;
		this.pCost = pCost;
		this.pRetail = pRetail;
		this.pQtny = pQtny;
		this.pAisle = pAisle;
		this.pCategory = pCategory;
		this.pComment = pComment;
	}
}