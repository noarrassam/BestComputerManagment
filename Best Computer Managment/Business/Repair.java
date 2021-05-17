package Business;

public class Repair {

	private String repairTask;  // task name
	private int repairNumber;
	private double repairCost;
	
	private String productName;
	private String customerName;
	
	private String repairday;
	private String repairmonth;
	private String repairyear;
	
	//customer
	
	public void setCustomername(String customerName2)
	{
		this.customerName = customerName2;
	}
	public String getCustomername()
	{
		return customerName;
	}
	//product
	
	public void setProductname(String productName2)
	{
		this.productName = productName2;
	}
	public String getProductname()
	{
		return productName;
	}
	
     //repair
	public Repair()
	{
	}
	public Repair(int repairNumber2)
	{
		this.repairNumber = repairNumber2;
	}
	public Repair(String customerName2,int repairNumber2)
	{
		this.customerName= customerName2;
		this.repairNumber = repairNumber2;
	}
	
	public void setRepairTask(String repairTask2)
	{
		this.repairTask = repairTask2;
	}
	public String getRepairTask()
	{
		return repairTask;
	}
	public int getRepairNumber()
	{
		return repairNumber;
	}
	
	public void setRepairNumber(int repainNumber2)
	{
		this.repairNumber = repainNumber2;
	}
	public double getRepairCost()
	{
		return repairCost;
	}
	
	public void setRepairCost(double repairCost2)
	{
		this.repairCost = repairCost2;
	}
	public void setRepairyear(String theyears)
	{
		this.repairyear = theyears;
	}
	public String getRepairyear()
	{
		return repairyear;
	}
	public void setRepairmonth(String repairmonth2)
	{
		this.repairmonth = repairmonth2;
	}
	public String getRepairmonth()
	{
		return repairmonth;
	}
	public void setRepairday(String repairday2)
	{
		this.repairday = repairday2;
	}
	public String getRepairday()
	{
		return repairday;
	}
}

