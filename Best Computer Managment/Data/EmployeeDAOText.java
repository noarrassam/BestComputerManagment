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

import Business.Employee;

public class EmployeeDAOText implements EmployeeDAO {
	private File employeeFile = null;
	//Constructor 
		public EmployeeDAOText() {
			employeeFile = new File(Constants.FILENAME_TEXT);
		}
		// check if the file is exist
		private void checkFile() throws IOException{
			if (!employeeFile.exists()) {
				employeeFile.createNewFile();
			}
		}
		//Save Employees
		private boolean saveTests(ArrayList<Employee> employees) {
			PrintWriter out = null;
			try {
				this.checkFile();
				// Print Employee
				out = new PrintWriter(new BufferedWriter(new FileWriter(employeeFile)));
				for (Employee emp : employees) {
					out.print(emp.getemployeeNumber()+ FIELD_SEP);
					out.print(emp.getfirstName() + FIELD_SEP);
					out.print(emp.getlastName() + FIELD_SEP);
					out.print(emp.getjobTitle()+ FIELD_SEP);
					out.print(emp.getdateOfBirth()+ FIELD_SEP);
					out.print(emp.getaddress()+ FIELD_SEP);
					out.print(emp.getpostalCode()+ FIELD_SEP);
					out.print(emp.getphone()+ FIELD_SEP);
					out.print(emp.getemail()+ FIELD_SEP);
			     	out.print(emp.getgender()+ FIELD_SEP);
					out.print(emp.getuserName()+ FIELD_SEP);
					out.println(emp.getpasword()+ FIELD_SEP);
				}
			} catch (IOException ioe) { 
				ioe.printStackTrace();
				return false;
			} finally {
				this.close(out);
			}
			return true;
		}
		// method to close the file
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
	public Employee getEmployee(String firstName) {
		// TODO Auto-generated method stub
		ArrayList<Employee> employees = this.getEmployees();
		for (Employee emp : employees) {
			if (emp.getfirstName().equalsIgnoreCase(firstName)) {
				return emp;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Employee> getEmployees() {
		// TODO Auto-generated method stub
BufferedReader in = null;
		
		try {
			this.checkFile();
			in = new BufferedReader(new FileReader(employeeFile));
			ArrayList<Employee> employees = new ArrayList<Employee>();
			String line = in.readLine();
			while (line != null) {
				String[] columns = line.split(FIELD_SEP);
				int employeeNumber=Integer.parseInt(columns[0]);
				String firstName = columns[1];
				String lastName = columns[2];
				String jobTitle = columns[3];
				String dateOfBirth = columns[4]; 
				String address = columns[5];
				String postalCode = columns[6];
				String phone = columns[7];
				String email = columns[8];
		    	String gender = columns[9];
				String userName = columns[10];
				String password = columns[11];
				//create Test Object
				Employee e = new Employee(employeeNumber,firstName, lastName,jobTitle,
				dateOfBirth,address,postalCode,phone,email,gender,userName,password);
			//	e.setEmployeeNumber(employeeNumber);
				employees.add(e);
				line = in.readLine();
			}
			return employees;
		} catch (Exception e) {
			System.err.println("There is an error during reading data of Employees");
			return null;
		} finally {
			this.close(in);
		}
	}
	@Override
	public boolean addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		ArrayList<Employee> employees = this.getEmployees();
		int num=1;
	
		//if (employees != null && !employees.isEmpty()) {
		///	  Employee emp1 = employees.get(employees.size()-1);
			//int num=emp1.getemployeeNumber();
			//employee.setEmployeeNumber(num++);
		//	}
		//;
	//	employees.add(employee);
		for (Employee e2:employees)
		{
			e2.setEmployeeNumber(num++);
		}
		employee.setEmployeeNumber(num);
		employees.add(employee);
		
		return this.saveTests(employees);
		
	}
	
	public boolean removeEmployee(Employee oldEmployee) {
	ArrayList<Employee> employees = this.getEmployees();
		Employee emp = this .getEmployee(oldEmployee.getfirstName());
		//int i= employees.indexOf(oldEmployee);
	//	Employee emp=this.getEmployee(oldEmployee.getfirstName());
		employees.remove(emp);
	
		return this .saveTests(employees);
}
	
	@Override
	public boolean updateEmployee(ArrayList<Employee> employees) {
			//ArrayList<Employee> employees = this.getEmployees();
		//	Employee oldEmployee = this .getEmployee(newEmployee.getfirstName());
			//int i= employees.indexOf(oldEmployee);
		//	employees.remove(oldEmployee);
		
			//employees.add(newEmployee);
			return this .saveTests(employees);
	}
	@Override
	public boolean removeEmployee(ArrayList<Employee> employees, Employee oldEmployee) {
		// TODO Auto-generated method stub
		//ArrayList<Employee> employees = this.getEmployees();
	//	Employee emp = this .getEmployee(oldEmployee.getfirstName());
		//int i= employees.indexOf(oldEmployee);
	//	Employee emp=this.getEmployee(oldEmployee.getfirstName());
		employees.remove(oldEmployee);
	
		return this .saveTests(employees);
	}
}
