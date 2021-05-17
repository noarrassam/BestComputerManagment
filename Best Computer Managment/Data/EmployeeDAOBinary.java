package Data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Business.Employee;

public class EmployeeDAOBinary implements EmployeeDAO {
	private File employeeFile = null;

	public EmployeeDAOBinary() {
		employeeFile = new File(Constants.FILENAME_BIN);
	}

	private void checkFile() throws IOException {
		if (!employeeFile.exists()) {
			employeeFile.createNewFile();
		}
	}

//	@SuppressWarnings("resource")
	private boolean saveEmployees(ArrayList<Employee> employees) {
		DataOutputStream out = null;
		try {
			this.checkFile();
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(employeeFile, true)));
			for (Employee emp : employees) {
				out.writeInt(emp.getemployeeNumber());
				out.writeUTF(emp.getfirstName());
				out.writeUTF(emp.getlastName());
				out.writeUTF(emp.getjobTitle());
				out.writeUTF(emp.getaddress());
				out.writeUTF(emp.getpostalCode());
				out.writeUTF(emp.getphone());
				out.writeUTF(emp.getemail());
				out.writeUTF(emp.getgender());
				out.writeUTF(emp.getuserName());
				out.writeUTF(emp.getpasword());
			}

			// out.flush();
			// out.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		} // finally {
			// this.close(out);
			// }

	}

	private void close(Closeable stream) {
		try {
			if (stream != null) {
				stream.close();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public Employee getEmployee(String firtName) {
		// TODO Auto-generated method stub
		ArrayList<Employee> employees = this.getEmployees();
		for (Employee emp : employees) {
			if (emp.getfirstName().equalsIgnoreCase(firtName)) {
				return emp;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Employee> getEmployees() {
		// TODO Auto-generated method stub

		DataInputStream in = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			this.checkFile();
			in = new DataInputStream(new BufferedInputStream(new FileInputStream(employeeFile)));
			while (in.available() > 0) {
				int employeeNumber = in.readInt();
				String firstName = in.readUTF();
				String lastName = in.readUTF();
				String jobTitle = in.readUTF();
				String dateOfBirth = in.readUTF();
				String address = in.readUTF();
				String postalCode = in.readUTF();
				String phone = in.readUTF();
				String email = in.readUTF();
				String gender = in.readUTF();
				String userName = in.readUTF();
				String password = in.readUTF();

				Employee e = new Employee(employeeNumber, firstName, lastName, jobTitle, dateOfBirth, address,
						postalCode, phone, email, gender, userName, password);
				e.setEmployeeNumber(employeeNumber);
				employees.add(e);
			}

			in.close();
		} catch (EOFException eofe) {
			this.close(in);

		} catch (IOException ioe) {
			System.err.println("There is an error during reading data of Employees");
			JOptionPane.showMessageDialog(null, " New Employee is saved", "New Employee",
					JOptionPane.INFORMATION_MESSAGE);
			ioe.printStackTrace();

		}
		return employees;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		// TODO Auto-generated method stub

		ArrayList<Employee> employees = this.getEmployees();

		// int num = 1;

		// for (Employee e2:employees)
		// {
		// e2.setEmployeeNumber(num++);
		// }
		// employee.setEmployeeNumber(num);
		employees.add(employee);

		return this.saveEmployees(employees);
	}

	public boolean removeEmployee(Employee oldEmployee) {
		ArrayList<Employee> employees = this.getEmployees();
		Employee emp = this.getEmployee(oldEmployee.getfirstName());
		// int i= employees.indexOf(oldEmployee);
//	Employee emp=this.getEmployee(oldEmployee.getfirstName());
		employees.remove(emp);

		return this.saveEmployees(employees);
	}

	@Override
	public boolean updateEmployee(ArrayList<Employee> newEmployee) {
		ArrayList<Employee> employees = this.getEmployees();
		// Employee oldEmployee = this .getEmployee(newEmployee.getfirstName());
		// int i= employees.indexOf(oldEmployee);
		// employees.remove(oldEmployee);

		// employees.add(newEmployee);
		return this.saveEmployees(employees);
	}

	@Override
	public boolean removeEmployee(ArrayList<Employee> employees, Employee oldEmployee) {
		// TODO Auto-generated method stub
		// ArrayList<Employee> employees = this.getEmployees();
//	Employee emp = this .getEmployee(oldEmployee.getfirstName());
		// int i= employees.indexOf(oldEmployee);
//	Employee emp=this.getEmployee(oldEmployee.getfirstName());
		employees.remove(oldEmployee);

		return this.saveEmployees(employees);
	}

}