package Data;

public interface Constants {
	 String FILENAME_TEXT = "Employees.txt";
	 String FILENAME_BIN = "Employees1.dat";
	 String FIELD_SEP = "\t";
		String FILENAME1_TEXT = "repairs.txt";
		String FILENAME1_BIN = "repairs.dat";
		String FILENAME2_TEXT = "Register.txt";
		//String FIELD_SEP = "\t";
		String FILENAME_BIN_FIXED = "repairsFixed.dat";
		int CUSTOMER_NAME_SIZE = 25;
		int PRODUCT_NAME_SIZE = 25;
		int SERVICE_TASK_SIZE = 25;
		int TICKET_NUMBER_SIZE = 8;
		int SERVICE_COST_SIZE = 8;
		// int AGE_SIZE = 4;
		// int GENDER_SIZE = 6; female is 6 character
		int RECORD_SIZE = CUSTOMER_NAME_SIZE * 2 + PRODUCT_NAME_SIZE * 2 + SERVICE_TASK_SIZE * 2 + TICKET_NUMBER_SIZE
				+ SERVICE_COST_SIZE * 2;
		 
        final double TAX=0.13;

}
