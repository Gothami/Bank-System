import java.sql.Connection;
import java.sql.Statement;

public class User {
	int usrID;
	String usrName;
	Account[] accounts;
	String pwd;
	String fullName;
	
	User(int userID, String userName, String password, String name, Account[] userAccounts){
		usrID = userID;
		usrName = userName;
		accounts = userAccounts;
		pwd = password;
		fullName = name;
	}
	
	void addAccount(int usrID, Account acc){
		if(accounts.length == 0){
			accounts[0] = acc;
		}
		else{
			accounts[accounts.length - 1] = acc;
		}
		
	}
	void profileManagement(int userID){
		
		Statement statement = null;
		try{
		DBConnection dbConn = new DBConnection();
		Connection connection = dbConn.executeDBConnection();
		statement = connection.createStatement();
		//String sql = "SELECT username,"
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
