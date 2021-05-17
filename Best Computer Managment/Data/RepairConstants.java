package Data;

public interface RepairConstants {

	// they are final variable
	String FILENAME_TEXT = "repairs.txt";
	String FILENAME_BIN = "repairs.dat";
	String FIELD_SEP = "\t";

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

}
