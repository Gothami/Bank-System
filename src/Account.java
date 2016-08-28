import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Account {
	int ownerID;
	int AccNum;
	int AccAmount;
	Date date = new Date();	
	
	Account(int usrID, int AccountNum, int AccountAmount){
		ownerID = usrID;
		AccNum = AccountNum;
		AccAmount = AccountAmount;
	}
	
	void debit(int amount){
		if(amount<AccAmount){
		AccAmount = AccAmount - amount;
		Statement statement = null;
		int minusAmount = -amount;		
		try{
		DBConnection dbConn = new DBConnection();
		Connection connection = dbConn.executeDBConnection();
		statement = connection.createStatement();
		String sql = "UPDATE account SET AccAmount=" + AccAmount + " WHERE ownerID=" + this.ownerID;
		String sql2 = "INSERT INTO transactions (AccNum, ownerID, DateAndTime, AmountTransfered) VALUES ("+this.AccNum+","
                +this.ownerID+","+"'"+ date + "'" + "," + minusAmount+")";		
		statement.executeUpdate(sql);
		statement.executeUpdate(sql2);
		}
		catch(Exception e){
			System.out.println(e);
		}
		}
		
	}	
	void credit(int amount){
		AccAmount = AccAmount + amount;
		Statement statement = null;
		try{
		DBConnection dbConn = new DBConnection();
		Connection connection = dbConn.executeDBConnection();
		statement = connection.createStatement();
		String sql = "UPDATE account SET AccAmount=" + AccAmount + " WHERE ownerID=" + this.ownerID;
		String sql2 = "INSERT INTO transactions (AccNum, ownerID, DateAndTime, AmountTransfered) VALUES ("+this.AccNum+","
				                +this.ownerID+","+"'"+ date + "'" + "," + amount+")";
		statement.executeUpdate(sql);
		statement.executeUpdate(sql2);
		}
		catch(Exception e){
			System.out.println(e);
		}		
	}
	int getBalance(){
		return this.AccAmount;
	}
}
