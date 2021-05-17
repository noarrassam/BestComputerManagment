package Data;

public class DAOFactory {
	public static EmployeeDAO getEmployeeDAO() {
		EmployeeDAO empDAO = new EmployeeDAOText();
//EmployeeDAO empDAO = new EmployeeDAOBinary1();
//EmployeeDAO empDAO = new EmployeeDAOBinary();
		// EmployeeDAORandom eDAO = new EmployeeDAORandom();
		return empDAO;
	}
}
