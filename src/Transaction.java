import java.security.acl.Owner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Transaction {
	int userID;
	int accNoFrom;
	int accNoTo;	
	
	public Transaction(int accountNoFrom, int accountNoTo) {
		this.accNoFrom  = accountNoFrom;
		this.accNoTo = accountNoTo;
	}
	
	public Transaction(){		
	}
	
	public void transferMoney(int amount){//working
		int ownerIDFrom = 0;
		int accAmountFrom = 0;
		int ownerIDTo = 0;
		int accAmountTo = 0;
		Statement statement = null;
		try{
		DBConnection dbConn = new DBConnection();
		Connection connection = dbConn.executeDBConnection();
		statement = connection.createStatement();
		String sql1 = "SELECT ownerID,AccAmount FROM account WHERE AccNum = " + accNoFrom;
		String sql2 = "SELECT ownerID,AccAmount FROM account WHERE AccNum = " + accNoTo;
		ResultSet rs1 = statement.executeQuery(sql1);		
		while(rs1.next()){
			ownerIDFrom = rs1.getInt("ownerID");
			accAmountFrom = rs1.getInt("AccAmount");
		}
		ResultSet rs2 = statement.executeQuery(sql2);
		while(rs2.next()){
			ownerIDTo = rs2.getInt("ownerID");
			accAmountTo = rs2.getInt("AccAmount");
		}		
		
		Account accFrom = new Account(ownerIDFrom, accNoFrom, accAmountFrom);
		Account accTo = new Account(ownerIDTo, accNoTo, accAmountTo);
		
		accFrom.debit(amount);
		accTo.credit(amount);
		this.accountInquiries(accNoFrom);
		this.accountInquiries(accNoTo);
		}
		catch(Exception e){
			System.out.println(e);
		}		
	}
	
	void accountInquiries(int accNum){//working
		int accAmount = 0;
		Statement statement = null;
		try{
		DBConnection dbConn = new DBConnection();
		Connection connection = dbConn.executeDBConnection();
		statement = connection.createStatement();
		String sql = "SELECT AccAmount FROM account WHERE AccNum=" + accNum;
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			accAmount = rs.getInt("AccAmount");
		}		
		System.out.println(accAmount);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	void accHistoryInquiries(int accNum){//working
		int ownerID = 0;
		String dateAndTime;
		int amountTranfered;
		String userName = null;
		Statement statement = null;
		
		try{
		DBConnection dbConn = new DBConnection();
		Connection connection = dbConn.executeDBConnection();
		statement = connection.createStatement();
		String sql3 = "SELECT ownerID FROM account WHERE AccNum=" + accNum;
		ResultSet rs3 = statement.executeQuery(sql3);
		while(rs3.next()){
			ownerID = rs3.getInt("ownerID");
		}
		String sql2 = "SELECT fullName FROM user WHERE userID = "+ ownerID;
		ResultSet rs2 = statement.executeQuery(sql2);
		while(rs2.next()){
			userName = rs2.getString("fullName");
		}
		String sql = "SELECT DateAndTime, AmountTransfered FROM transactions WHERE AccNum=" + accNum;		
		ResultSet rs = statement.executeQuery(sql);
		
		System.out.println("Account Number : " + accNum);
		System.out.println("Account Holder's Name : " + userName);
		System.out.println("Account Holder's ID : " + ownerID);
		System.out.println("\n");
		
		while(rs.next()){			
			dateAndTime = rs.getString("DateAndTime");
			amountTranfered = rs.getInt("AmountTransfered");
			System.out.println("Transaction Date and TIme : " + dateAndTime);
			System.out.println("Amount Transfered : " + amountTranfered);
			System.out.println("\n");
		}		
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	
	
	

}
