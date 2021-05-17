package Data;

import java.io.*;
import java.util.ArrayList;

import Business.Repair;

public class RepairDAOText implements RepairDAO {

	// get the file
	private File repairFile = null;

	public RepairDAOText() {
		repairFile = new File(Constants.FILENAME1_TEXT);
	}

	private void checkFile() throws IOException {
		if (!repairFile.exists())
			repairFile.createNewFile();
	}

	// write to a file -- save one ticket
	private boolean saveRepairs(ArrayList<Business.Repair> repairs) {
		PrintWriter out = null;
		try {
			this.checkFile();
			out = new PrintWriter(new BufferedWriter(new FileWriter(repairFile)));
			for (Business.Repair p : repairs) {
				out.print(p.getCustomername() + FIELD_SEP);
				out.print(p.getRepairNumber() + FIELD_SEP);
				out.print(p.getProductname() + FIELD_SEP);
				out.print(p.getRepairTask() + FIELD_SEP);
				// go next line
				out.println(p.getRepairCost() + FIELD_SEP);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		} finally {
			this.close(out);
		}
		return true;
	}

	// close the file
	private void close(Closeable stream) {
		try {
			if (stream != null)
				stream.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// get one ticket when find button clicks -- the value we want to search with
	@Override
	public Business.Repair getRepair(String customerName) {
		ArrayList<Business.Repair> repairs = this.getRepairs();
		for (Business.Repair p : repairs) {
			if (p.getCustomername().equalsIgnoreCase(customerName)) {
				return p;
			}
		}
		return null;
	}

	// read file/ get information from file-- we assign all data to arraylist
	@Override
	public ArrayList<Business.Repair> getRepairs() {

		BufferedReader in = null;
		try {
			this.checkFile();
			in = new BufferedReader(new FileReader(repairFile));
			ArrayList<Business.Repair> repairs = new ArrayList<Business.Repair>();
			String line = in.readLine();
			while (line != null) {
				String[] columns = line.split(FIELD_SEP);
				String customername = columns[0];
				int ticketnum = Integer.parseInt(columns[1]);
				String productname = columns[2];
				String servicetask = columns[3];
				double servicecost = Double.parseDouble(columns[4]);

				// create obj to call constructor
				Business.Repair p = new Business.Repair(customername, ticketnum);
				p.setProductname(productname);
				p.setRepairTask(servicetask);
				p.setRepairCost(servicecost);
				repairs.add(p);

				// go to next line each time
				line = in.readLine();
			}
			return repairs;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} finally {
			this.close(in);
		}
	}

	public boolean addRepair(Business.Repair repair) {
		ArrayList<Business.Repair> repairs = this.getRepairs();
		repairs.add(repair);
		return this.saveRepairs(repairs);
	}

	// to remove
	public boolean removeCustomer(Business.Repair oldcustomer) {
		ArrayList<Business.Repair> repairs = this.getRepairs();
		Business.Repair emp = this.getRepair(oldcustomer.getCustomername());

		repairs.remove(emp);

		return this.saveRepairs(repairs);
	}

	public boolean removeCustomer(ArrayList<Business.Repair> repairs, Repair oldCustomer) {

		repairs.remove(oldCustomer);

		return this.saveRepairs(repairs);
	}

	// to edit
	@Override
	public boolean updateRepair(ArrayList<Business.Repair> repairs) {

		return this.saveRepairs(repairs);
	}
}
