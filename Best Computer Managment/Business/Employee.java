package Business;

public class Employee {
	// Instance Variables
	private int employeeNumber;
//private static int employeesNumbers;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String dateOfBirth;
	private String address;
	private String postalCode;
	private String phone;
	private String email;
	private String gender;
	private String userName;
	private String password;

	// constructors
	public Employee(int employeeNumber, String firstName, String lastName, String jobTitle, String dateOfBirth,
			String address, String postalCode, String phone, String email, String gender, String userName,
			String password) {
		this.employeeNumber = employeeNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.postalCode = postalCode;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.userName = userName;
		this.password = password;
	}

	// Setters
	public void setEmployeeNumber(int employeeNumber) {

		this.employeeNumber = employeeNumber;
	}

	public void setFirstName(String firstName) {

		this.firstName = firstName;
	}

	public void setLastName(String lastName) {

		this.lastName = lastName;
	}

	public void setJobTitle(String jobTitle) {

		this.jobTitle = jobTitle;
	}

	public void setDateOfBirth(String dateOfBirth) {

		this.dateOfBirth = dateOfBirth;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public void setPostalCode(String postalCode) {

		this.postalCode = postalCode;
	}

	public void setPhone(String phone) {

		this.phone = phone;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public void setGender(String gender) {

		this.gender = gender;
	}

	public void setuserName(String userName) {

		this.userName = userName;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	// getters
	public int getemployeeNumber() {
		return employeeNumber;
	}

	public String getfirstName() {
		return firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public String getjobTitle() {
		return jobTitle;
	}

	public String getdateOfBirth() {
		return dateOfBirth;
	}

	public String getaddress() {
		return address;
	}

	public String getpostalCode() {
		return postalCode;
	}

	public String getphone() {
		return phone;
	}

	public String getemail() {
		return email;
	}

	public String getgender() {
		return gender;
	}

	public String getuserName() {
		return userName;
	}

	public String getpasword() {
		return password;
	}
}
