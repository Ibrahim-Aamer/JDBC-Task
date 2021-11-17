import java.util.Date;

public class SavingsAccount extends Account
{
	public SavingsAccount(User user, float initDeposit) 
	{
		NumCnt = NumCnt + 1; //incrementing account no# at each new creation
		
		accountNum = NumCnt; //Assigning NumCnt to accountNum
		
		dateCreated = new Date();//Getting system date and time
		
		Name = user.getName();  //Getting user name from user object
		
		this.user = user;
		
		this.user.setSavAcc(this);
		
		balance = initDeposit;//setting initial deposit	
		
	}
	
	/**
	 * 
	 * @param float deposit amount 
	 */
	public void makeDeposit(float deposit) 
	{
		if( deposit > 0 ) 
		{
			balance += deposit;
			
			this.printStatement(deposit);

		}
		else 
		{
			System.out.println("INVALID DEPOSIT AMOUNT !");
		}
		
	}
	
	/**
	 * 
	 * @param float withrawing amount
	 */
	public void makeWithdrawal(float amount) 
	{
		//Withdrawal wont proceed if amount exceeds balance
		if( amount <= this.balance ) 
		{
			balance -= amount; //amount deducted
			
			this.printStatement(amount);
		}
		else 
		{
			System.out.println("TRANSACTION FAILED ! \n (Amount exceeds current Balance)\n");
		}
	}
	
	public float calculateInterest() 
	{
		return interestRate * balance;
	}
	
	public float calculateZakat() 
	{
		if( balance >= 20000 ) 
		{
			return (balance*(float)2.5) /100;
		}
		else 
		{
			return 0;
		}
	}
	
	public void displayAllDeductions() 
	{
		if( balance >= 20000 ) 
		{
			//float zakat = ( (balance*(float)2.5)/100 );
			
			
			System.out.println("Total Zakat deducted : "  + Float.toString(this.calculateZakat()) + " PKR" + '\n');
		}
		else 
		{
			System.out.println("NO DEDUCTIONS HAVE BEEN MADE ! \n");
		}
	}
	
	static private float interestRate = 5;//5% interest rate
}




