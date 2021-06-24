/**
 * Class:	 CTrailer
 * 
 * @author 	 Neina Cichon
 * @version  1.0
 * 
 * Date: 	 12/4/2020
 */
public class CTrailer extends CVehicle
{
	// Properties		
		double dblCost = 0.0;
		
		/**
		 * Initialize
		 * This will initialize all our values
		 * @param intMPG miles per gallon for vehicle
		 * @param intWheels number of wheels on vehicle
		 * @param intDays number of rental days
		 * @param strType type of vehicle
		 * @param dblPrice price per day of vehicle
		 * @param strHowToDrive how to drive the vehicle
		 */
		public void Initialize(int intMPG, int intWheels, int intDays, String strType, double dblPrice, String strHowToDrive  )
		{
			// protected: can be accessed only by the class in which it was declared
			//			or by classes that inherit.
			SetType( "Trailer" );
			SetWheels(intWheels);
			SetMPG( intMPG );
			SetPrice( dblPrice );
			SetHowToDrive(strHowToDrive);
		}
		
		
		/**
		 * CTrailer 
		 * Default Constructor
		 */
		public CTrailer( )
		{
			Initialize( 0, 0, 0, "", 0, "" );
		}


		//Methods are defined here!
		/**
		 * Method: Cost
		 * @param intDays number of rental days
		 * @param dblPrice price of vehicle per day
		 * @return dblCost
		 */
		public double Cost( int intDays, double dblPrice)
		{
			dblCost = intDays * dblPrice;
			return dblCost;
		}


	/**
	 * Method: Print
	 * Print Results
	 */
	public void Print( )
	{
		System.out.println( "Vehicle Type:\t" + GetType( ) );
		System.out.println( "MPG:\t\t" + GetMPG( ) );
		System.out.println( "Wheels:\t\t" + GetWheels( ) );
		System.out.println( "How To Drive:\t" + GetHowToDrive( ) );
		System.out.printf( "Price:\t\t$%.2f\n", GetPrice( ) );
		System.out.printf( "Cost:\t\t$%.2f\n", dblCost);

	}
	}

