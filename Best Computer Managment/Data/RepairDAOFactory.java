package Data;

public class RepairDAOFactory {
	
	public static RepairDAO getRepairDAO() {
		
	//	RepairDAO pDAO = new RepairDAOText();
		RepairDAO pDAO = new RepairDAOBinary();
		
		return pDAO;
	}
}

