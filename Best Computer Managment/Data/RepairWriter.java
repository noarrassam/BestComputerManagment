package Data;

import java.util.ArrayList;

public interface RepairWriter {
	
	// indicate methods in interface, in classes we give body to them
	boolean addRepair(Business.Repair repair);
	
	public boolean removeCustomer(Business.Repair oldCustomer);
	public boolean removeCustomer(ArrayList<Business.Repair> repairs,Business.Repair oldCustomer);
	
	public boolean updateRepair(ArrayList<Business.Repair> repairs);
}
