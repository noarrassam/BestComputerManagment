package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Business.RgstrEmp;

public class RgstrDAOText implements RgstrDAO {
	private File RgstrFile = null;

	public RgstrDAOText() {
		RgstrFile = new File(Constants.FILENAME2_TEXT);
	}

	private void checkFile() throws IOException {
		if (!RgstrFile.exists()) {
			RgstrFile.createNewFile();
		}
	}

	private boolean saveRegister(ArrayList<RgstrEmp> rgstrsEmp) {
		PrintWriter out = null;
		try {
			this.checkFile();
			out = new PrintWriter(new BufferedWriter(new FileWriter(RgstrFile)));

			for (RgstrEmp r : rgstrsEmp) {
				out.print(r.getFirstName() + FIELD_SEP);
				out.print(r.getLastName() + FIELD_SEP);
				out.print(r.getUserName() + FIELD_SEP);
				out.print(r.getPass() + FIELD_SEP);
				out.print(r.getPhoneNum() + FIELD_SEP);
				out.println(r.getGender() + FIELD_SEP);
//				out.close();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}

		finally {
			this.close(out);
		}
		return true;
	}

	private void close(Closeable stream) {
		try {
			if (stream != null)
				stream.close();
		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public RgstrEmp getRgstr(String firstName) {
		// TODO Auto-generated method stub
		ArrayList<RgstrEmp> rgstrEmp = this.getRgstrs();
		for (RgstrEmp r : rgstrEmp) {
			if (r.getFirstName().equalsIgnoreCase(firstName)) {
				return r;
			}

		}
		return null;
	}

	@Override
	public ArrayList<RgstrEmp> getRgstrs() {
		// TODO Auto-generated method stub
		BufferedReader in = null;
		try {
			this.checkFile();
			in = new BufferedReader(new FileReader(RgstrFile));
			ArrayList<RgstrEmp> rgstr = new ArrayList<RgstrEmp>();
			String line = in.readLine();
			while (line != null) {
				String[] columns = line.split(FIELD_SEP);
				String firstName = columns[0];
				String lastName = columns[1];
				String userName = columns[2];
				String password = columns[3];
				// char[] password = toCharArray(columns[3]);
				int phoneNum = Integer.parseInt(columns[4]);
				String gender = columns[5];

				RgstrEmp r = new RgstrEmp(firstName, lastName, userName);
				r.setPass(password);
				r.setPhoneNum(phoneNum);
				r.setGender(gender);
				rgstr.add(r);
				line = in.readLine();
			}
			return rgstr;
		} catch (IOException e) {
			e.printStackTrace();
			// System.err.println("There is an error during reading data");
			return null;
		} finally {
			this.close(in);
		}
	}

	@Override
	public boolean addRgstr(RgstrEmp rgstr) {
		// TODO Auto-generated method stub
		ArrayList<RgstrEmp> rg = this.getRgstrs();
		rg.add(rgstr);
		return this.saveRegister(rg);
	}
}