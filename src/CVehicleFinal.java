//import for sql Connection, DriverManager, ResultSet, Statement
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.microsoft.sqlserver.jdbc.*;	// For connecting to SQL Server

/**
* Abstract: Vehicle Rental Program
* @author NC
* @since  12/4/2019
* @version 1.0
*/
public class CVehicleFinal
{
		//define the Connection
		private static Connection m_conAdministrator;
		
		//define the table, primary key, and column
		/**
		 * main method: Vehicle Rental
		 * Connect to the db and display records on the console
		 * Calculate Vehicle Rental
		 * @param args main args
		 */
		public static void main(String[] args) 
		{
		     // Declare variables
				String strResponse = "";
				String strPhoneNumber = "";
				String strEmail = "";
				String strFirstName = "";
				String strLastName = "";
				String strPickupDate = "";
				String strVehicleCount = "";
				int intVehicleCount = 0;
				int intIndex = 0;
				int intVehicle[];
				int intDays = 0;
				
				
	        try {
	    			// Can we connect to the database?
	        	
	    			//if ( OpenDatabaseConnectionSQLServer( ) == true )
	    			if ( OpenDatabaseConnectionMSAccess( ) == true )
	    			{	
	    				System.out.println("Here are the pickup locations - We will contact you with a location confirmation");
						// Yes, load the teams list
	    				LoadListFromDatabase( "TLocations", "intLocationID" , "strLocationName", "strAddress", "strCity", "strState", "strZip" );
	    			}
	    			else
	    			{
	    				// No, warn the user ...
	    				System.out.println("Error loading the table");
	    			}		
	        }
	            catch 	(Exception e) {
	            	System.out.println("An I/O error occurred: " + e.getMessage());
	        	}
	        
	        	// Print header
				System.out.println( "---------------------------------------------------------------------------------------------------" );
				System.out.println( "Enter QUIT at anytime to exit." );
				System.out.println( );
	
				// Ask user for first name
				System.out.print( "First Name: " );
				strResponse = ReadStringFromUser();
				// Check for quit
				if( strResponse.toUpperCase().matches( "QUIT" ) )
				{
					ProgramEnded();
					return;
				}
				// Quit not entered
				strFirstName = strResponse;
				
				// Ask user for last name
				System.out.print( "Last Name: " );
				strResponse = ReadStringFromUser();
				// Check for quit
				if( strResponse.toUpperCase().matches( "QUIT" ) )
				{
					ProgramEnded();
					return;
				}
				// Quit not entered
				strLastName = strResponse;
				
				// Ask user for phone number and loop until correct format is entered
				do
				{
					System.out.print( "Phone number in the format '###-###-####' or '##########': " );
					strResponse = ReadStringFromUser();
					// Check for quit
					if( strResponse.toUpperCase().matches("QUIT") )
					{
						ProgramEnded();
						return;
					}
				}while( IsValidPhoneNumber( strResponse ) == false );
				
				// Phone number is in correct format
				strPhoneNumber = strResponse;
				
				// Ask user for email and loop until correct format is entered
				do
				{
					System.out.print( "Email: " );
					strResponse = ReadStringFromUser();
					// Check for quit
					if( strResponse.toUpperCase().matches("QUIT") )
					{
						ProgramEnded();
						return;
					}
				}while( IsValidEmailAddress( strResponse ) == false );
				
				// Email is in correct format
				strEmail = strResponse;
	
				// Ask user for pick-up date and loop until correct format is entered
				do
				{
					System.out.print( "Pick-up date in the format MM-DD-YYYY or MM/DD/YYYY: " );
					strResponse = ReadStringFromUser();
					// Check for quit
					if( strResponse.toUpperCase().matches("QUIT") )
					{
						ProgramEnded();
						return;
					}
				}while( IsValidDate( strResponse ) == false );
				
				// Date is in correct format
				strPickupDate = strResponse;
				
				// Ask user for number of days to rent
				do
				{
					System.out.print( "Number of days you are renting: " );
					strResponse = ReadStringFromUser();
					// Check for quit
					if( strResponse.toUpperCase().matches("QUIT") )
					{
						ProgramEnded();
						return;
					}
				}while( IsInteger( strResponse ) == false || IsPositiveInteger(strResponse) == false);
				
				// Number of Days is valid
				//strDays = strResponse;
				intDays = Integer.parseInt(strResponse);
	
				// Ask user for number of Vehicles to rent
				do
				{
					System.out.print( "Number of vehicles you are renting (1-3): " );
					strResponse = ReadStringFromUser();
					// Check for quit
					if( strResponse.toUpperCase().matches("QUIT") )
					{
						ProgramEnded();
						return;
					}
				}while( IsInteger( strResponse ) == false || IsPositiveInteger(strResponse) == false || IsValidVehicleNumber(strResponse) == false);
				
				// Number of vehicles is valid
				strVehicleCount = strResponse;
				
				intVehicleCount = Integer.parseInt(strResponse);
				
				intVehicle = new int[intVehicleCount];
				
				System.out.println( "-----------------------------------------------" );
				System.out.print( "Please select the type of vehicle you wish to rent: \n" );
				System.out.print( " Enter 1 for Car\n Enter 2 for Motorcycle\n Enter 3 for Trailer\n" );
				
				// Ask user for Vehicle Type
				for(intIndex = 0; intIndex < intVehicleCount; intIndex += 1)
				{
					do
					{
						System.out.printf( "Vehicle #%d: ", intIndex + 1 );
						strResponse = ReadStringFromUser();
						// Check for quit
							if( strResponse.toUpperCase().matches("QUIT") )
							{
								ProgramEnded();
								return;
							}
					}while( IsInteger( strResponse ) == false || IsPositiveInteger(strResponse) == false || IsValidVehicleNumber(strResponse) == false);
					
					intVehicle[intIndex] = Integer.parseInt(strResponse);
				}
				
				//Print Results
				System.out.println( "" );
				System.out.println( "-----------------------------------------------" );
				System.out.println( "Rental Information" );
				System.out.println( "-----------------------------------------------" );
				System.out.printf( "Name:\t\t%s %s\n", strFirstName, strLastName);
				System.out.printf( "Phone Number:\t%s\n", strPhoneNumber );
				System.out.printf( "Email:\t\t%s\n", strEmail );
				System.out.printf( "Rental Days:\t%d\n", intDays );
				Vehicles(intVehicle, intDays);
							
				System.out.println( "" );	
				System.out.println( "" );
			
				ProgramEnded();

		}
		

		/** 
		 * 	method name: This will load the list from the table.
		 * @param strTable name of table
		 * @param strPrimaryKeyColumn name of PK column
		 * @param strNameColumn name of name column
		 * @param strAddressColumn name of address column
		 * @param strCityColumn name of city column
		 * @param strStateColumn name of State column
		 * @param strZipColumn name of zip column
		 * @return blnResult
		 */
		public static boolean LoadListFromDatabase( String strTable, String strPrimaryKeyColumn, String strNameColumn, String strAddressColumn, String strCityColumn, String strStateColumn, String strZipColumn ) {
			
			//set flag to false
			boolean blnResult = false;
			
			try
			{
				String strSelect = "";
				Statement sqlCommand = null;
				ResultSet rstTSource = null;
				int intID = 0;
				String strName = "";
				String strAddress = "";
				String strCity = "";
				String strState = "";
				String strZip = "";
			
				// Build the SQL string
				strSelect = "SELECT " + strPrimaryKeyColumn + ", " + strNameColumn + ", " + strAddressColumn
						+ ", " + strCityColumn + ", " + strStateColumn + ", " + strZipColumn
							+ " FROM " + strTable
							+ " ORDER BY " + strNameColumn; 

						
				// Retrieve the all the records	
				sqlCommand = m_conAdministrator.createStatement( );
				rstTSource = sqlCommand.executeQuery( strSelect );
				// Loop through all the records
				while( rstTSource.next( ) == true )
				{
					// Get ID and Name from current row
					intID = rstTSource.getInt( 1 );
					strName = rstTSource.getString( 2 );
					strAddress = rstTSource.getString( 3 );
					strCity = rstTSource.getString( 4 );
					strState = rstTSource.getString( 5 );
					strZip = rstTSource.getString( 6 );
					
					
					// Print the list
					System.out.println(" ID: " + intID + "\t Name: " + strName + "\t Address: " + strAddress + "\t City: " + strCity + "\t State: " + strState + "\t Zip: " + strZip);
				}
				// Clean up
				rstTSource.close( );
				sqlCommand.close( );
				// Success
				blnResult = true;
			}
			catch 	(Exception e) {
				System.out.println( "Error loading table" );
				System.out.println( "Error is " + e );
			}
			
			return blnResult;
			}

		/** 
		 * method name: OpenDatabaseConnectionMSAccess
		 * Opens the database connection	
		 * @return blnResult
		 */
		public static boolean OpenDatabaseConnectionMSAccess( )
		{
			boolean blnResult = false;
			
			try {
				String strConnectionString = "";
				
				// Server name/port, IP address/port or path for file based DB like MS Access
				// System.getProperty( "user.dir" ) => Current working directory from where
				// application was started
				strConnectionString = "jdbc:ucanaccess://" + System.getProperty( "user.dir" )
									+ "\\Database\\dbHCM.accdb";
				// Open a connection to the database
				m_conAdministrator = DriverManager.getConnection( strConnectionString );
				// Success
				blnResult = true;
			}
			catch 	(Exception e) {
				System.out.println( "Try again - error in OpenDB ");
				System.out.println( "Error is " + e );
			}
			return blnResult;
		}
		
//		/**
//		 * OpenDatabaseConnectionSQLServer - get SQL db connection
//		 * @return blnResult
//		 */
//		public static boolean OpenDatabaseConnectionSQLServer( )
//		{
//			boolean blnResult = false;
//			
//			try
//			{
//				SQLServerDataSource sdsLocations = new SQLServerDataSource( );
//				//tg-comment out --sdsTeamsAndPlayers.setServerName( "localhost" ); // localhost or IP or server name
//				sdsLocations.setServerName( "localhost\\SQLExpress" ); // SQL Express version
//				sdsLocations.setPortNumber( 1433 );
//				sdsLocations.setDatabaseName( "dbHCM" );
//				
//				// Login Type:
//								
//					// SQL Server
//					sdsLocations.setUser( "sa" );
//					sdsLocations.setPassword( "" );	// Empty string "" for blank password
//				
//				// Open a connection to the database
//				m_conAdministrator = sdsLocations.getConnection(  );
//				
//				// Success
//				blnResult = true;
//			}
//			catch( Exception excError )
//			{
//				// Display Error Message
//				System.out.println( "Cannot connect - error: " + excError );
//
//				// Warn about SQL Server JDBC Drivers
//				System.out.println( "Make sure download MS SQL Server JDBC Drivers");
//			}
//			
//			return blnResult;
//		}
		
		
		/**
		* Name: CloseDatabaseConnection
		* Abstract: Close the connection to the database
		* @return blnResult
		*/ 
		public static boolean CloseDatabaseConnection( )
		{
			boolean blnResult = false;
			
			try
			{
				// Is there a connection object?
				if( m_conAdministrator != null )
				{
					// Yes, close the connection if not closed already
					if( m_conAdministrator.isClosed( ) == false ) 
					{
						m_conAdministrator.close( );
						
						// Prevent JVM from crashing
						m_conAdministrator = null;
					}
				}
				// Success
				blnResult = true;
			}
			catch( Exception excError )
			{
				// Display Error Message
				System.out.println( excError );
			}
			
			return blnResult;
		}
		/**
		 * Method: ReadStringFromUser - Get input from user
		 * @return strBuffer
		 */
		public static String ReadStringFromUser( )
		{

			String strBuffer = "";

			try
			{
				// Input stream
				BufferedReader burInput = new BufferedReader( new InputStreamReader( System.in ) ) ;

				// Read a line from the user
				strBuffer = burInput.readLine( );
			}
			catch( Exception excError )
			{
				System.out.println( excError.toString( ) );
			}

			// Return string
			return strBuffer;
		}
		
		
		/**
		 * Method: ProgramEnded - When user enters "QUIT"
		 */
		private static void ProgramEnded() {
			System.out.println( "-----------------------------------------------" );
			System.out.println( "Program ended." );
			System.out.println( "Thank you for using Neina's Vehicle Rental!" );
			System.out.println( "Have a great winter break!" );
		}
		
		
		/**
		 * Method: IsValidPhoneNumber - Check if phone number entered is in correct format
		 * @param strPhoneNumber
		 * Phone number entered by user
		 * @return blnIsValidPhoneNumber
		 */
		public static boolean IsValidPhoneNumber(String strPhoneNumber) {
			boolean blnIsValidPhoneNumber = false;
			
			try
			{
				// Declare variables
				String strStart = "^";
				String strStop = "$";
				String strDash = "\\-";
				String strPattern1 = "";
				String strPattern2 = "";
				
				// String patterns
				// ###-###-####
				strPattern1 = strStart + "\\d{3}" + strDash + "\\d{3}" + strDash + "\\d{4}" + strStop;
				// ##########
				strPattern2 = strStart + "\\d{10}" + strStop;
				
				// Does it match any of the formats?
				if( strPhoneNumber.matches( strPattern1 ) == true || 
					strPhoneNumber.matches( strPattern2 ) == true )
				{
					// Yes
					blnIsValidPhoneNumber = true;
				}
			}
			catch( Exception excError )
			{
				// Display Error Message
				System.out.println( excError );
			}
			// Return result
			return blnIsValidPhoneNumber;
		}
		
		
		/**
		 * Method: IsValidEmailAddress - Check if email entered is valid
		 * @param strResponse
		 * Email entered by user
		 * @return blnIsValidEmailAddress
		 */
		private static boolean IsValidEmailAddress(String strEmailAddress) {
			boolean blnIsValidEmailAddress = false;
			
			try
			{
				// Declare variables
				String strStart = "^";
				String strStop = "$";
				String strPattern = "";
				
				// Set string pattern
				strPattern = strStart + "[a-zA-Z][a-zA-Z0-9\\.\\-]*" + "@" + "[a-zA-Z][a-zA-Z0-9\\.\\-]*\\.[a-zA-Z]{2,6}" + strStop;
				
				// Does it match?
				if( strEmailAddress.matches( strPattern ) == true )
				{
					// Yes
					blnIsValidEmailAddress = true;
				}
				
			}
			catch( Exception excError )
			{
				// Display Error Message
				System.out.println( excError );
			}
			
			return blnIsValidEmailAddress;
		}
		
		
		/**
		 * Method: IsValidDate - Check if date entered is valid
		 * @param strResponse
		 * Date entered by user
		 * @return blnIsValidDate
		 */
		private static boolean IsValidDate(String strDate) {
			boolean blnIsValidDate = false;
			
			try
			{
				// Declare variables
				String strStart = "^";
				String strStop = "$";
				String strDash = "\\-";
				String strSlash = "\\/";
				String strPattern1 = "";
				String strPattern2 = "";
				
				// Set string pattern
				// MM-DD-YYYY
				strPattern1 = strStart + "\\d{2}" + strDash + "\\d{2}" + strDash + "\\d{4}" + strStop;
				// MM/DD/YYYY
				strPattern2 = strStart + "\\d{2}" + strSlash + "\\d{2}" + strSlash + "\\d{4}" + strStop;
				
				// Does it match?
				if( strDate.matches( strPattern1 ) == true ||
					strDate.matches( strPattern2 ) == true)
				{
					// Yes
					blnIsValidDate = true;
				}
				
			}
			catch( Exception excError )
			{
				// Display Error Message
				System.out.println( excError );
			}
			
			return blnIsValidDate;
		}
		
		
		/**
		 * Method ReadIntegerFromUser - Get user input
		 * @return intValue
		 */
		public static int ReadIntegerFromUser( )
		{

			int intValue = 0;

			try
			{
				String strBuffer = "";	

				// Input stream
				BufferedReader burInput = new BufferedReader( new InputStreamReader( System.in ) ) ;

				// Read a line from the user
				strBuffer = burInput.readLine( );
				
				// Convert from string to integer
				intValue = Integer.parseInt( strBuffer );
			}
			catch( Exception excError )
			{
				System.out.println( excError.toString( ) );
			}
			

			// Return integer value
			return intValue;
		}
		
		
		/**
		 * Method: IsInteger- checks if string is an integer
		 * @param strResponse
		 * String to check
		 * @return blnNumeric
		 */
		public static boolean IsInteger(String strResponse) {
			boolean blnNumeric = true;
			
			try
			{
				Integer.parseInt(strResponse);
			}
			catch( NumberFormatException e )
			{
				blnNumeric = false;
			}
			
			return blnNumeric;
		}
		
		/**
		 * Method: IsPositiveInteger- checks if string is a Positive integer
		 * @param strResponse
		 * String to check
		 * @return blnNumeric
		 */
		public static boolean IsPositiveInteger(String strResponse) {
			boolean blnNumeric = true;
			
			if(Integer.parseInt(strResponse) > 0)
			{
				blnNumeric = true;
			}
			else 
			{
				blnNumeric = false;
			}
				
			
			return blnNumeric;
		}
		/**
		 * Method: IsValidVehicleNumber- checks if string is a Positive integer
		 * @param strResponse
		 * String to check
		 * @return blnNumeric
		 */
		public static boolean IsValidVehicleNumber(String strResponse) {
			boolean blnNumeric = false;
			
			if(Integer.parseInt(strResponse) > 0 && Integer.parseInt(strResponse) <= 3)
			{
				blnNumeric = true;
			}
			else 
			{
				blnNumeric = false;
				
			}
				
			
			return blnNumeric;
		}
		
		/** 
		 * Name: Vehicles
		 * Abstract: Get Vehicle information and print results
		 * @param intVehicle[] array of vehicle selection
		 * @param intDays number of rental days
		 */
		public static void Vehicles(int intVehicle[], int intDays )
		{
			double dblTotal = 0.0;
			int intIndex = 0;
		
			CVehicle aclsVehicles[] = new CVehicle[intVehicle.length];
			
			// Get Vehicle Details
			for(intIndex = 0; intIndex < intVehicle.length; intIndex += 1)
			{
				System.out.println( "" );
				System.out.println( "Vehicle #" + (intIndex + 1));
				System.out.println( "-----------------------------------------------" );
				switch(intVehicle[intIndex])
				{
					case 1: 
						CCar clsCar = new CCar( );
						clsCar.SetType ( "Car" );
						clsCar.SetMPG( 28 );
						clsCar.SetWheels( 4 );
						clsCar.SetPrice( 75.00 );
						clsCar.SetHowToDrive( "Steering Wheel" );
						dblTotal += clsCar.Cost(intDays, clsCar.GetPrice( ));
						aclsVehicles[intIndex] = (CVehicle) clsCar;
						clsCar.Print();
						break;
					case 2: 
						CMotorbike clsMotorbike = new CMotorbike( );
						clsMotorbike.SetType ( "Motorbike" );
						clsMotorbike.SetMPG( 35 );
						clsMotorbike.SetWheels( 2 );
						clsMotorbike.SetPrice( 98.00 );
						clsMotorbike.SetHowToDrive( "Handle bars" );
						dblTotal += clsMotorbike.Cost(intDays, clsMotorbike.GetPrice( ));
						aclsVehicles[intIndex] = (CVehicle) clsMotorbike;
						clsMotorbike.Print();
						break;
					case 3: 
						CTrailer clsTrailer = new CTrailer( );
						clsTrailer.SetType ( "Trailer" );
						clsTrailer.SetWheels( 6 );
						clsTrailer.SetPrice( 35.00 );
						clsTrailer.SetHowToDrive( "Hitch to another vehicle" );
						dblTotal += clsTrailer.Cost(intDays, clsTrailer.GetPrice( ));
						aclsVehicles[intIndex] = (CVehicle) clsTrailer;
						clsTrailer.Print();
						break;	
				}
				
			}
			
			System.out.println( "\n-----------------------------------------------" );
			System.out.printf("Total: $%.2f", dblTotal);
			System.out.println( "\n-----------------------------------------------" );
			System.out.println("");
			
		}
		
		
	}


