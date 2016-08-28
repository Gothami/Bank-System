import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.LeafElement;

public class Bank {
	private AccountFactory accFactory;
	private UserFactory usrFactory;
	private Map<Integer, User> userList = new HashMap<Integer, User>();
	
	int lastAccID;//taking from db
	int lastUserID;//taking from db
	
	Bank(){
		accFactory = new AccountFactory(lastAccID);
		usrFactory = new UserFactory();
	}
	
	void createUser(String username, String password, String fullName){
		userList.put(usrFactory.userID, usrFactory.createUser(username, password, fullName));
	}
	
	void createAccount(int ownerID, int accountAmount){
		Account createdAccount = accFactory.createAccount(ownerID, accountAmount);
		User user = userList.get(ownerID);//can trigger an aspect for this
		user.accounts[user.accounts.length - 1] = createdAccount;//each time account is created add account to users' Account list
		
	}
	
	User findUser(int userID){
		return userList.get(userID);
	}
	
	void userReg(){
		Bank bank = new Bank();
		Scanner s = new Scanner(System.in);
		System.out.print("Enter Full Name :");
		String fullNm = s.nextLine();
		System.out.print("Enter Username :");
		String userName = s.nextLine();
		System.out.print("Enter Password:");
		String pwd = s.nextLine();
		System.out.println("\n");
		bank.createUser(userName, pwd, fullNm);
		System.out.println("User "+fullNm+" Registered Successfully");		
	}
	
	boolean login(){
		Scanner s = new Scanner(System.in);
		System.out.print("Enter Username :");
		String username = s.nextLine();
		System.out.print("Enter Password :");
		String password = s.nextLine();		
		String dbPassword = null;
		boolean match = false;
		Statement statement = null;
		try{
		DBConnection dbConn = new DBConnection();
		Connection connection = dbConn.executeDBConnection();
		statement = connection.createStatement();
		//check whether the username exists in the db.If so get the password and match
		
		String sql = "SELECT password FROM user WHERE username=" + "\""+username+"\"";		
		ResultSet rs = statement.executeQuery(sql);	
		while(rs.next()){			
			dbPassword = rs.getString("password");
			}
		
		/*String sql = "IF EXISTS (SELECT * FROM user WHERE username=" + username 
							+ ") SELECT password FROM user WHERE username=" + username;
		if(sql!=null){
			ResultSet rs = statement.executeQuery(sql);			
			while(rs.next()){
				
			}			
		}*/
		
		}
		catch(Exception e){
			System.out.println(e);
		}
		if(dbPassword.equals(password)){
			match = true;System.out.println("aaa");
		}else{
			match = false;System.out.println("fff");
		}
		return match;
	}
	
	void personalProfile(int usrID){
		Statement statement;
		String name;
		String pwd;
		String fullName;
		try{
			DBConnection dbConn = new DBConnection();
			Connection connection = dbConn.executeDBConnection();
			statement = connection.createStatement();
			String sql = "SELECT * FROM user WHERE userID=" + "\"" + usrID + "\"";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				name = rs.getString("username");
				pwd = rs.getString("password");
				fullName = rs.getString("fullName");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
