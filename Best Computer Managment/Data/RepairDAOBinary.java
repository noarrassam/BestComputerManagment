package Data;

import java.io.*;
import java.util.ArrayList;

import Business.Repair;

//whenever we call PersonDAO to implement, the other 3 interfaces should be implement at same time
public class RepairDAOBinary implements RepairDAO {
	
	private File repairFile = null;
	
	// constructor 
	public RepairDAOBinary() {
		
		// File is class 
		repairFile = new File(Constants.FILENAME1_BIN);
		//personFile = new File("c://student.txt");
	}
	
	private void checkFile() throws IOException {
		
		if(!repairFile.exists())
			repairFile.createNewFile();		
	}
	
	//if file is open, close it
	private void close(Closeable stream)
	{
		try {
			// if file is open
			if(stream !=null) 
			stream.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	// write to db
	private boolean saveRepairs(ArrayList<Business.Repair> allrepairs)
	{
		// write to db , data move out from app to db
		DataOutputStream out = null;
		try {
			this.checkFile();
			out = new DataOutputStream(new FileOutputStream(repairFile));
			out.flush();
			for (Business.Repair p: allrepairs) {
				out.writeUTF(p.getCustomername());
				out.writeInt(p.getRepairNumber());
				out.writeUTF(p.getProductname());
				out.writeUTF(p.getRepairTask());
				out.writeDouble(p.getRepairCost());
				//out.writeInt(p.getRepairyear());
				out.writeUTF(p.getRepairyear());
				out.writeUTF(p.getRepairmonth());
				out.writeUTF(p.getRepairday());
				//out.writeInt(p.getRepairday());
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		finally {
			this.close(out);
		}
		return true;
	}
	@Override
	public Business.Repair getRepair(String customerName)
	{
		ArrayList<Business.Repair> allrepairs = this.getRepairs();
		for(Business.Repair p : allrepairs)
		{
			if(p.getCustomername().equalsIgnoreCase(customerName))
				return p;
		}
		return null;
	}
	// read from db
	@Override
	public ArrayList<Business.Repair> getRepairs() {
		DataInputStream in=null;
		ArrayList<Business.Repair> allrepairs = new ArrayList<Business.Repair>();
		try {
			this.checkFile();			
			in = new DataInputStream(new FileInputStream(repairFile));
			while (in.available() > 0 ) {
				
				String customername = in.readUTF();
				int ticketnum = in.readInt();
				String productname = in.readUTF();
				String servicetask = in.readUTF();
				double servicecost = in.readDouble();
				//int year = in.readInt();
				String year = in.readUTF();
				String month = in.readUTF();
				String day = in.readUTF();
				//int day = in.readInt();
				
				// call constructor by creating obj
				Business.Repair p = new Business.Repair(customername,ticketnum);

				p.setProductname(productname);
				p.setRepairTask(servicetask);
				p.setRepairCost(servicecost);
				p.setRepairyear(year);
				p.setRepairmonth(month);
				p.setRepairday(day);
				allrepairs.add(p);
			}
		}
		catch (EOFException eofe) {
			this.close(in);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return allrepairs;	
	}
	// when user click on save
	@Override
	public boolean addRepair(Business.Repair repair)
	{
		ArrayList<Business.Repair> allrepairs = this.getRepairs();
		allrepairs.add(repair);
		return this.saveRepairs(allrepairs);
	}

	@Override
	public boolean removeCustomer(Repair oldRepair) {
		ArrayList<Business.Repair> allrepairs = this.getRepairs();
		Business.Repair emp = this.getRepair(oldRepair.getCustomername());

		allrepairs.remove(emp);

		return this.saveRepairs(allrepairs);
	}

	@Override
	public boolean removeCustomer(ArrayList<Repair> repairs, Repair oldCustomer) {
		repairs.remove(oldCustomer);

		return this.saveRepairs(repairs);
	}

	@Override
	public boolean updateRepair(ArrayList<Repair> repairs) {
		//ArrayList<Business.Repair> allrepairs = this.getRepairs();

		return this.saveRepairs(repairs);
	}
}

