package Data;

public class RepirDAOFactory {
	
	public static RepairDAO getRepairDAO() {
		
	//	RepairDAO pDAO = new RepairDAOText();
		RepairDAO pDAO = new RepairDAOBinary();
		
		return pDAO;
	}
}

