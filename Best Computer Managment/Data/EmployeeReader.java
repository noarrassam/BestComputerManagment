package Data;


import java.util.ArrayList;

import Business.Employee;;

public interface EmployeeReader {
	Business.Employee getEmployee(String firstName);
	ArrayList<Business.Employee> getEmployees();
}
