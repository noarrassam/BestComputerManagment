package Data;

import java.util.ArrayList;

import Business.RgstrEmp;

public interface RgstrReader {
	RgstrEmp getRgstr(String firstName);

	ArrayList<RgstrEmp> getRgstrs();
}
