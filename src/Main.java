import java.util.Date;
import java.util.Scanner;

public class Main {	
	

	public static void main(String[] args) {		
		Bank bank = new Bank();
		boolean loginSuccess;
		System.out.println("\t\t\tWelcome to Lanka Bank (PVT) Ltd\n");
		System.out.println("Press 1 to : Register as an User");
		System.out.println("Press 2 to : Login");
		/*System.out.println("Press 3 to : Money Transfer");
		System.out.println("Press 4 to : Account Inquiries");
		System.out.println("Press 5 to : Account History Inquiries");*/
		Scanner s = new Scanner(System.in);
		String no = s.nextLine();
		switch(no){
		case "1" : bank.userReg();
		case "2" : if(bank.login()){			
			System.out.println("Press 1 to : Money Transfer");
			System.out.println("Press 2 to : Account Inquiries");
			System.out.println("Press 3 to : Account History Inquiries");
			System.out.println("Press 4 to : Update Personal Profile");
			System.out.println("Press 5 to : Contact Bank");
			String no2 = s.nextLine();
			switch(no2){
			case "1" :
				System.out.println("Enter your account number :");
				int from = s.nextInt();
				System.out.println("Enter account number of the receiver :");
				int to = s.nextInt();
				System.out.println("Enter amount you want to send :");
				int amount = s.nextInt();
				Transaction tr = new Transaction(from, to);
				tr.transferMoney(amount);
				return;
			case "2" :
				System.out.println("Enter your account number :");
				int accNum = Integer.parseInt(s.nextLine());
				Transaction tra = new Transaction();
				tra.accountInquiries(accNum);
				return;
			case "3" :
				System.out.println("Enter your account number :");
				int accNumbr = Integer.parseInt(s.nextLine());
				Transaction tran = new Transaction();
				tran.accHistoryInquiries(accNumbr);
				return;
			case "4" :
				System.out.println("Enter User ID : ");
				int userID = s.nextInt();
				bank.personalProfile(userID);
				return;
			case "5" :
				System.out.println("Your User ID : ");
				int userID1 = s.nextInt();
				//System.out.println("Enter Your Message Here : ");
				//String message = s.nextLine();
				bank.sendMessage(userID1);
			}
		}



		
		
		
		
		
		/*bank.createUser("Nimal Perera", null);
		UserFactory userFac = new UserFactory();
		userFac.createUser("nimal", "nimal", "NimalPerera");*/
		
		//System.out.println(new Date());
		
		/*Transaction tr = new Transaction();
		tr.accountInquiries(1);;*/
		
		/*Transaction tr = new Transaction(1,2);
		tr.transferMoney(2500);*/
		
		
	
	}

}
}
