
public class AccountFactory {
	int accountID;
	public AccountFactory(int lastAccountID) {
		accountID = lastAccountID;
	}
	
	Account createAccount(int usrID, int AccountAmount){
		return new Account(usrID, accountID + 1, AccountAmount);
	}

}
