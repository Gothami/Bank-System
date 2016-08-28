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
			String no2 = s.nextLine();
			switch(no2){
			case "1" :
				System.out.println("Enter your account number :");
				int from = Integer.parseInt(s.nextLine());
				System.out.println("Enter account number of the receiver :");
				int to = Integer.parseInt(s.nextLine());
				System.out.println("Enter amount you want to send :");
				int amount = Integer.parseInt(s.nextLine());
				Transaction tr = new Transaction(from, to);
				tr.transferMoney(amount);
				return;
			case "2" :
				System.out.println("Enter your account number :");
				int accNum = Integer.parseInt(s.nextLine());
				Transaction tra = new Transaction();
				tra.accountInquiries(accNum);
			case "3" :
				System.out.println("Enter your account number :");
				int accNumbr = Integer.parseInt(s.nextLine());
				Transaction tran = new Transaction();
				tran.accHistoryInquiries(accNumbr);
				
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
