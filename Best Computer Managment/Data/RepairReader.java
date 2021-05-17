package Data;

import java.util.ArrayList;

import Business.Repair;

public interface RepairReader {

	Business.Repair getRepair(String customerName);

	ArrayList<Repair> getRepairs();
}
