import java.sql.*;
public class DBConnection {
	final static String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	final static String DB_URL = "jdbc:mysql://localhost/bank_system";
	final static String USER = "root";
	final static String PASS = "";
		
	public Connection executeDBConnection(){			
			Connection conn = null;				
			try {
				Class.forName("com.mysql.jdbc.Driver");				
				conn = DriverManager.getConnection(DB_URL,USER,PASS);				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}return conn;
			
			/*stmt = conn.createStatement();
			String sql = "SELECT username, password FROM user";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				System.out.println(username);
				System.out.println(password);
			}
			
			rs.close();
			stmt.close();
			conn.close();	*/		
			

		
		

}
}
