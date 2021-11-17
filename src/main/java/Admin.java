import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

import java.sql.*;

public class Admin {

	public static void InsertIntoDB(User user)
	{
		try{

			//  System.out.println(getJDBCVersion());
			//  System.out.println("SHFBSD");
			//step1 load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded Successfully!");
			//step2 create the connection object
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@//DESKTOP-QB4O65F:1521/xe",
					"system","tiger");
			System.out.println("Connection Established!");


			String sql = "INSERT INTO SYSTEM.ACCOUNT_USER (NAME,ADDRESS,PHONE) VALUES (?,?,?)";
			//System.out.println("SHFBSD");
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, user.getName());
			statement.setString(2, user.getAddress());
			statement.setString(3, user.getPhoneNum());
			//statement.setDate(4, (java.sql.Date) user.getDateCreated());

			//statement.setString(3, user.getAddress());
			//statement.setInt(4, ba.getAccNo());
			//statement.setInt(5, ba.getBalance());
			//statement.setString(6, ba.getPhoneNo());
			//statement.setInt(7, ba.getType());
			//  System.out.println("sadsadD");
			int rowInserted = statement.executeUpdate();

			if(rowInserted> 0){
				System.out.println("Inserted into Oracle");
			}
		}catch(Exception e){
			System.out.println("Exception Thrown");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**
	 * Main function to hold all menus and cater execution
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//-------Testing oracle connection-------
		try
		{
			//step1 load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println("Driver Loaded Successfully!");

			//step2 create the connection object
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@//DESKTOP-QB4O65F:1521/xe",
					"system","tiger");
			System.out.println("Connection Established!");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Driver Not Loaded");
		}
		catch(SQLException e)
		{
			System.out.println("Connection Not Established!");
		}
		//---------------------------------------

		 Scanner input = new Scanner(System.in);
		 

		//Making array list of accounts & users
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		ArrayList<User> users = new ArrayList<User>();
		
		Account tempAcc;
		
		User tempUser;
		
		
		System.out.println("=========================\n"
						 + "ACCOUNT MANAGEMENT SYSTEM\n"
						 + "=========================\n\n");
		
		String choice = "1"; 
		
		while( !choice.equals("4") ) 
		{
			
			User currUser = null;
			Account currAcc = null;
			
			//-----------------------FIRST MENU-----------------
			System.out.println("---------------------------\n"
							 + "        MAIN MENU\n"
							 + "---------------------------\n"
							 + "1. Use an Existing Account\n"
							 + "2. Open a new Account\n"
							 + "3. Close an account\n"
							 + "4. Exit\n"
							 + "---------------------------\n"
							 + "\nEnter choice : ");
			
			choice = input.nextLine();
			
			//Input validation loop
			while( !choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") )
			{
				System.out.println("Invalid Choice !\n"
						+ "Please enter again : ");
				
				choice = input.nextLine(); 
							
			}	
			
			//if-else for correct option
			if ( choice.equals("1") ) //Use an existing account
			{
				System.out.println("Enter Account Number : ");
				int accNum = input.nextInt();  //Taking account number as input from user
				
				for(int i = 0 ; i < accounts.size() ; i++) 
				{
					//users.get(i).displayDetails();
					if( accounts.get(i).getAccountNum() == accNum ) 
					{
						currAcc = accounts.get(i);
						
						System.out.println("\nACCOUNT FOUND\n");
						
						break;
					}
				}	
				
				if ( currAcc != null ) 
				{
					
					//BOTH CHECKING AND SAVING ACCOUNT WILL HAVE DIFFERENT MENUS
					System.out.println("---------------------------\n"
									 + "       ACCOUNT MENU\n"
									 + "---------------------------\n"
									 + "1. Make a Deposit\n"
									 + "2. Make a Withdrawal\n"
									 + "3. Check Balance\n"
									 + "4. Display all Deductions\n"
									 + "5. Transfer Amount\n"
									 + "---------------------------\n"
									 + "\nEnter choice : ");
					choice = input.nextLine();
			
					//Input validation loop
					while( !choice.equals("1") && !choice.equals("2") && 
						!choice.equals("3") && !choice.equals("4") && !choice.equals("5") )
					{
						System.out.println("Invalid Choice !\n"
								+ "Please enter again : ");
						
						choice = input.nextLine(); 
									
					}
					
					if ( choice.equals("1") ) //deposit function called
					{
						
						System.out.println("Enter Depositing Amount : ");
						
						float depositAmount = input.nextFloat();
						
						currAcc.makeDeposit(depositAmount);
					} 
					else if ( choice.equals("2") ) //withdrawal function called
					{
						System.out.println("Enter Withdrawing Amount : ");
						
						float withdrawAmount = input.nextFloat();
						
						currAcc.makeWithdrawal(withdrawAmount);
					}
					else if ( choice.equals("3") ) //check balance function called
					{
						currAcc.checkBalance();
					}
					else if ( choice.equals("4") ) //Display all deductions
					{
						currAcc.displayAllDeductions();
					}
					else if ( choice.equals("5") ) //Transfer amount
					{
						System.out.println("Enter Recievers Account Number : ");
						
						int accNum2 = input.nextInt();
						
						Account acc2 = null;
						
						for(int i = 0 ; i < accounts.size() ; i++) 
						{
							//users.get(i).displayDetails();
							if( accounts.get(i).getAccountNum() == accNum2 ) 
							{
								acc2 = accounts.get(i);
								
								System.out.println("\nRECIEVERS ACCOUNT FOUND\n");
								
								break;
							}
						}	
						
						if( acc2 != null ) 
						{
							System.out.println("Enter Amount to Transfer : ");
							
							float transferAmount = input.nextFloat(); 
							
							currAcc.makeWithdrawal(transferAmount); //withdrawing from currAcc
							acc2.makeDeposit(transferAmount);      //Depositing into recievers account
							
						}
						else 
						{
							System.out.println("\nRECIEVERS ACCOUNT DOES'NT EXIST\n");
						}
						
						
						
					}
					
					
				} 
				else if ( currAcc == null )
				{
					System.out.println("\nACCOUNT DOES'NT EXIST\n");
				}
				
				
			}
			else if ( choice.equals("2") ) //Open a new Account
			{
				System.out.println("Are you an old customer ? (Enter y/n)");
				choice = input.nextLine();
				
				//Input validation
				while ( !choice.equals("y") && !choice.equals("n") ) 
				{
					System.out.println("Invalid Selection, Please enter again (Enter Y/N)");
					choice = input.nextLine();
				}
				
				//If new user was already registered
				if( choice.equals("y") ) 
				{
					
					
					System.out.println("Enter Username : ");
					choice = input.nextLine();
					
					for(int i = 0 ; i < users.size() ; i++) 
					{
						//users.get(i).displayDetails();
						if( (users.get(i).getUserName()).equals(choice) ) 
						{
							currUser = users.get(i);
							
							System.out.println("\nUSER FOUND\n");
							break;
	
						}
						
					}
					
				}
				//registering new user 
				else if ( choice.equals("n") ) 
				{
					System.out.println("Enter Full Name     :");
					String name = input.nextLine();
					System.out.println("Enter Date of birth :");
					String dob = input.nextLine();
					System.out.println("Enter Phone Number  :");
					String phNum = input.nextLine();
					System.out.println("Enter Address       :");
					String addr = input.nextLine();
					
					//New User created
					tempUser = new User(name,dob,phNum,addr);
					
					System.out.println("\nUSER CREATED SUCCESFULLY !");
					InsertIntoDB(tempUser);
					System.out.println("Username    : " + tempUser.getUserName() + '\n' );
					
					currUser = tempUser;
					
					//New User added to the ArrayList of users
					users.add(tempUser);
				}
				
				//Now creating new account for the current user
				if ( currUser != null) 
				{
					System.out.println("---------------------------\n"
									+  "  SELECT TYPE OF ACCOUNT\n"
									+  "---------------------------\n"
									+  "1. Open a Checking Acoount\n"
							 		+  "2. Open a Savings Account\n"
							 		+  "---------------------------\n"
							 		+  "\nEnter choice : ");
					choice = input.nextLine();
					
					while ( !choice.equals("1") && !choice.equals("2") ) 
					{
						System.out.println("Invalid Selection, Please enter again :");
						choice = input.nextLine();
					}
				
				}
				//Creating Checking Account here
				if ( currUser == null) 
				{
					System.out.println("\nUSER NOT FOUND !\n");
				}
				else if( choice.equals("1") ) 
				{
					if(currUser.getCheckAcc() == null) 
					{
						//Taking deposit Amount from user
						System.out.println("Enter Amount to deposit into Account : ");
						float depositAmount = input.nextFloat();
						
						tempAcc = new CheckingAccount(currUser,depositAmount);
						
						System.out.println("\nChecking Account Succesfully Created !\n"
										 + "\n Account Number   : 	" + Integer.toString(tempAcc.getAccountNum()) ) ;
						
						accounts.add(tempAcc);//Adding new account in account array list
					}
					else 
					{
						System.out.println("\n CHECKING ACCOUNT ALREADY EXISTS ! \n");
					}
				}
				//Creating Savings Account here
				else if ( choice.equals("2") ) 
				{
					if ( currUser.getSavAcc() == null) 
					{
						//Taking deposit Amount from user
						System.out.println("Enter Amount to deposit into Account : ");
						float depositAmount = input.nextFloat();
						
						tempAcc = new SavingsAccount(currUser,depositAmount);
						
						System.out.println("\nSavings Account Succesfully Created !\n"
										 + "\n Account Number   : 	" + tempAcc.getAccountNum() ) ;
						
						accounts.add(tempAcc);//Adding new account in account array list
					}
					else 
					{
						System.out.println("\n SAVINGS ACCOUNT ALREADY EXISTS ! \n");
					}
				}
				
				
				
			}
			else if( choice.equals("3") ) //Close an account
			{
				System.out.println("Enter Username : ");
				choice = input.nextLine();
				
				for(int i = 0 ; i < users.size() ; i++) 
				{
					//users.get(i).displayDetails();
					if( (users.get(i).getUserName()).equals(choice) ) 
					{
						currUser = users.get(i);
						
						System.out.println("\nUSER FOUND\n");

					}
					
				}
				
				//If user is found then he/she is given the option of which account to close
				if(currUser != null) 
				{
					System.out.println("------------------------------------\n"
									+  "  SELECT TYPE OF ACCOUNT TO CLOSE\n"
									+  "------------------------------------\n"
									+  "1. Checking Acoount\n"
							 		+  "2. Savings Account\n"
							 		+  "------------------------------------\n"
							 		+  "\nEnter choice : ");
					
					choice = input.nextLine();
					
					if( choice.equals("1") ) //closing checking account if it exists
					{
						if( currUser.getCheckAcc() != null ) 
						{
							currUser.setCheckAcc(null);
							System.out.println("\nACCOUNT CLOSED !\n");
						}
						else 
						{
							System.out.println("\nACCOUNT DOES'NT EXIST !\n");
						}
					}
					else if ( choice.equals("2") )//closing savings account if it exists
					{
						if( currUser.getSavAcc() != null ) 
						{
							currUser.setSavAcc(null);
							System.out.println("\nACCOUNT CLOSED !\n");
						}
						else 
						{
							System.out.println("\nACCOUNT DOES'NT EXIST !\n");
						}
					}
				}
				else
				{
					System.out.println("\nUSER NOT FOUND\n");
				}
			}
			
			currUser = null;//setting currUser back to NULL at end of session
			currAcc = null;
			
			input.reset();
			
			System.out.println("\nEnter 4 to Exit (Enter any other key to go back )");
			choice = input.nextLine();
			
		}
		
		//--------------------------------------------------
		
		
		input.close();//closing input scanner
	}
	
	
	



}
