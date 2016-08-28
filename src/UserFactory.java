import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserFactory {
	int userID;
	Account[] userAccounts;	
	
	User createUser(String userName, String password, String fullName){
		User user = null;
		try{
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.executeDBConnection();
		Statement statement = conn.createStatement();
		String sql1 = "SELECT lastUserID FROM lastid";
		statement.executeQuery(sql1);
		ResultSet rs = statement.executeQuery(sql1);
		while(rs.next()){
		userID = rs.getInt("lastUserID");		
		}
		int newUserID = userID+1;
		user = new User(newUserID, userName, password, fullName, userAccounts);		
		String sql2 = "INSERT INTO user VALUES (" + newUserID+","+"'"+userName+"'"+","+"'"+ password+"'"+","+"'"+fullName+"'"+")";		
		statement.executeUpdate(sql2);
		String sql3 = "UPDATE lastid SET lastUserID="+newUserID;
		statement.executeUpdate(sql3);
		
		}
		catch(Exception e){
			System.out.println(e);
		}
		return user;
	}

}
