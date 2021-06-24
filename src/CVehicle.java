/**
 * Class:	 CVehicle
 * 
 * @author 	 Neina Cichon
 * @version  1.0
 * 
 * Date: 	 12/4/2020
 */

public class CVehicle
{
	// Properties
	private String m_strType = "";
	private String m_strHowToDrive = "";
	private int m_intWheels = 0;
	private int m_intMPG = 0;
	private double m_dblPrice = 0;
	
	/**
	 * Method: SetType
	 * @param strNewType The type of the Vehicle
	 */
	public void SetType ( String strNewType )
	{
		//Negative?
		int intLength = 0;
		
		intLength = strNewType.length ( ) ;
		
		//Too Long?
		if ( intLength > 50 )
		{
			//if longer, clip to 5o characters
			intLength = 50;
		}
		m_strType = strNewType.substring ( 0, intLength );
	}
	
	/**
	 * Method: GetType
	 * @return m_strType the type of the Vehicle
	 */
	public String GetType( )
	{
		return m_strType;
	}
	
	/**
	 * Method: SetWheels
	 * @param intNewWheels The number of wheels
	 */
	public void SetWheels( int intNewWheels )
	{
		//Negative??
		if(intNewWheels < 0)
		{
			//Yes, clip
			intNewWheels = 0;
		}
		
		m_intWheels = intNewWheels;
	}
	
	/**
	 * Method: GetWheels
	 * @return m_intWheels The Name of the Vehicle
	 */
	public int GetWheels( )
	{
		return m_intWheels;
	}
	
	
	/**
	 * Method: SetMPG
	 * @param intNewMPG The MPG of Vehicle
	 */
	public void SetMPG( int intNewMPG )
	{
		//Negative??
		if(intNewMPG < 0)
		{
			//Yes, clip
			intNewMPG = 0;
		}
		
		m_intMPG = intNewMPG;
	}
	/**
	 * Method: GetMPG
	 * @return m_intMPG The MPG of the Vehicle
	 */
	public int GetMPG( )
	{
		return m_intMPG;
	}
	

	
	/**
	 * Method: SetPrice
	 * @param dblNewPrice The Price of Vehicle
	 */
	public void SetPrice( double dblNewPrice )
	{
		//Negative??
		if(dblNewPrice < 0)
		{
			//Yes, clip
			dblNewPrice = 0;
		}
		
		m_dblPrice = dblNewPrice;
	}
	
	/**
	 * Method: GetPrice
	 * @return m_dblPrice The Price of the Vehicle
	 */
	public double GetPrice( )
	{
		return m_dblPrice;
	}

	/**
	 * Method: SetPrice
	 * @param strHowToDrive How to drive the Vehicle
	 */
	public void SetHowToDrive( String strHowToDrive )
	{
		//Negative?
		int intLength = 0;
		
		intLength = strHowToDrive.length ( ) ;
		
		//Too Long?
		if ( intLength > 50 )
		{
			//if longer, clip to 5o characters
			intLength = 50;
		}
		m_strHowToDrive = strHowToDrive.substring ( 0, intLength );
	}
	
	/**
	 * Method: GetHowToDrive
	 * @return m_strHowToDrive How to drive the vehicle
	 */
	public String GetHowToDrive( )
	{
		return m_strHowToDrive;
	}
	
	
}