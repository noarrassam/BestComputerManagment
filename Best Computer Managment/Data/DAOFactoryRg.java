package Data;

public class DAOFactoryRg 
{
	public static RgstrDAO getRgstDAO()
	{
		RgstrDAO rDAO = new RgstrDAOText();
		return rDAO;
	}
}
