package Business;

public class RgstrEmp {
	private String firstName, lastName, userName, password, gender;
	// private char[] password;
	private int phoneNum;

	public void setFirstName(String fName) {
		this.firstName = fName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setUserName(String uName) {
		this.userName = uName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPass(String pass) {
		this.password = pass;
	}

	public String getPass() {
		return password;
	}

	public void setPhoneNum(int num) {
		this.phoneNum = num;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public RgstrEmp(String userNm, String pass) {
		this.userName = userNm;
		this.password = pass;
	}

	public RgstrEmp(String fName, String lName, String userNm) {
		this.firstName = fName;
		this.lastName = lName;
		this.userName = userNm;
		// this.password = pass;
	}

	public RgstrEmp() {
		this.firstName = "Noor";
		this.lastName = "Rassam";
		this.userName = "noorrassam";
		// this.password = "nggg";
	}

	public String toString() {
		return firstName + " " + lastName + " " + userName + " " + password + " " + phoneNum + " " + gender;
	}
}