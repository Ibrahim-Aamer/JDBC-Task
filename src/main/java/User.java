import java.util.Date;

/**
	A class for Users
 */
public class User 
{
	/**
	 * Construct a user object
	 * @param uName
	 * @param date of birth
	 */
	public User(String uName, String dob, String phNum, String addr ) 
	{
		userCnt ++;//incrementing user count in each constructor call
		
		Name = uName;
		address = addr;
		phoneNum = phNum;
		dateCreated = new Date();
		this.dob = dob;
		
		//Creating a unique username (with no spaces) for each user based on user count
		userName = Name.replaceAll("\\s", "") + Integer.toString(userCnt);	}
	
	/**
	 * Display details User
	 * @return Details of User in a string
	 */
	public void displayDetails() 
	{
		System.out.println( "Username           : " + getUserName() + '\n'
			+  "Full Name          : " + getName() + '\n' 
			+  "Joining Date       : " + dateCreated + '\n'
			+  "Date of Birth      : " + dob + '\n'
			+  "Address            : " + address + '\n'
			+  "Phone Number       : " + phoneNum );
		
		this.checkAccountsStatus();
		

		
	}
	
	/**
	 * Displaying Accounts status of user
	 */
	public void checkAccountsStatus() 
	{
		
		if( checkAcc == null ) 
		{					  
			System.out.println("Checking Account   : INACTIVE ");
		}
		else 
		{
			System.out.println("Checking Account   : ACTIVE ");
		}
		
		if( savAcc == null ) 
		{					  
			System.out.println("Savings Account    : INACTIVE ");
		}
		else 
		{
			System.out.println("Savings Account    : ACTIVE ");
		}
	}
	
	/**
	 * 
	 * @return Name of the user
	 */
	public String getName() {
		return Name;
	}

	public String getUserName() {
		return userName;
	}
	
	

	public SavingsAccount getSavAcc() {
		return savAcc;
	}

	public void setSavAcc(SavingsAccount savAcc) {
		this.savAcc = savAcc;
	}

	public CheckingAccount getCheckAcc() {
		return checkAcc;
	}

	public void setCheckAcc(CheckingAccount checkAcc) {
		this.checkAcc = checkAcc;
	}

	public String getAddress()
	{
		return this.address;
	}

	public String getPhoneNum()
	{
		return this.phoneNum;
	}

	public Date getDateCreated()
	{
		return this.dateCreated;
	}

	static private int userCnt = 0;
	private String userName;
	private String Name;
	private String address;
	private String phoneNum;
	private Date dateCreated;
	private String dob; 
	private CheckingAccount checkAcc;
	private SavingsAccount savAcc;
}
