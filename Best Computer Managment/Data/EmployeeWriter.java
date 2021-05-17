package Data;

import java.util.ArrayList;

import Business.Employee;

public interface EmployeeWriter {
	boolean addEmployee(Employee employee);
	public boolean updateEmployee(ArrayList<Employee> employees);
	public boolean removeEmployee(Employee oldEmployee);
	public boolean removeEmployee(ArrayList<Employee> employees,Employee oldEmployee);
	
}
