import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.LeafElement;

import com.mysql.fabric.xmlrpc.base.Data;

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
		Statement statement = null;
		String name = null;
		String pwd = null;
		String fullName = null;
		Scanner s = new Scanner(System.in);
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
		}catch(Exception e){
			System.out.println(e);
		}
			System.out.println("Your Profile Info");
			System.out.println("Full Name : " + fullName);
			System.out.println("Username : " + name);
			System.out.println("Password : " + pwd);
			System.out.println("\t\tEnter new Full Name : ");
			String newFullName = s.nextLine();
			System.out.println("Enter New Username : ");
			String newUsername = s.nextLine();
			System.out.println("Enter New Password : ");
			String newPassword = s.nextLine();
			System.out.println("\t\tYour Profile has successfully updated");
			System.out.println("\t\tNew Profile Info");
			System.out.println("Full Name : " + newFullName);
			System.out.println("Username : " + newUsername);
			System.out.println("Password : " + newPassword);
			
			String sql1 = "UPDATE user SET username=" + newUsername + ",password="+ newPassword +
							",fullName=" + newFullName + " WHERE userID=" + usrID;
			try {
				statement.executeUpdate(sql1);
			} catch (SQLException e) {				
				e.printStackTrace();
			}		
	}
	
	void sendMessage(int usrID){
		System.out.println("Enter your message");
		Scanner s = new Scanner(System.in);
		String message = s.nextLine();
		Statement statement;
		Date date = new Date();
		try{
			DBConnection dbConn = new DBConnection();
			Connection connection = dbConn.executeDBConnection();
			statement = connection.createStatement();
			String sql = "INSERT INTO messages (userID, message, DateAndTime) VALUES ("+usrID
							+ ",\"" +message + "\""+",\""+date +"\""+ ")";
			System.out.println(sql);
			statement.executeUpdate(sql);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
