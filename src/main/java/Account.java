import java.util.Date;

public class Account 
{
	public Account() 
	{
		
	}
	
	/**
	 * Constructs a new account object (Parameterized constructor)
	 * @param username,
	 */
	public Account(User user, int initDeposit) 
	{
		NumCnt = NumCnt + 1; //incrementing account no# at each new creation
		
		accountNum = NumCnt; //Assigning NumCnt to accountNum
		
		dateCreated = new Date();//Getting system date and time
		
		Name = user.getName();  //Getting user name from user object
		
		balance = initDeposit;//setting initial deposit	
		
	}
	
	/**
	 * 
	 * @return String Name & balance
	 */
	public void checkBalance() 
	{
		System.out.println(  "Account no. : " + this.getAccountNum() + "\n"
						   + "User Name   : " + user.getName() + '\n' 
						  +  "Balance     : " + Float.toString(balance) + '\n');
	}
	

	
	
	public void transferAmount(float amount, int accountNum) 
	{
		
	}
	
	/**
	 * 
	 * @param transactionAmount
	 * @return returns string which holds transaction details
	 */
	public void printStatement(float transactionAmount) 
	{
		Date transactionTime = new Date();
		
		System.out.println( "--------------------------------------------------\n"
						  + "              TRANSACTION STATEMENT\n"
				           +"-------------------------------------------------\n"
				+ "Account Number     : " + Integer.toString(accountNum) + '\n'
			+  "Transaction Amount : " + Float.toString(transactionAmount) + '\n'
			+  "Remaining Balance  : " + Float.toString(balance) + '\n'
			+  "Transaction Time   : " + transactionTime + '\n');
			user.displayDetails() ;
		System.out.println( "--------------------------------------------------\n"	);
			
	}
	
	public void makeDeposit(float deposit) 
	{
	}

	public void makeWithdrawal(float amount) 
	{
	}
	
	/**
	 * Function to be overridden
	 */
	public void displayAllDeductions() 
	{
	}
	
	
	/**
	 * Returns account number
	 * @return Account number 
	 */
	public int getAccountNum() 
	{
		return accountNum;
	}

	static protected int NumCnt = 0;
	protected int accountNum;
	protected String Name;
	protected float balance;
	protected Date dateCreated;
	protected User user; //user object to hold user details
	protected float totalDeductions = 0;
	
}





