
public aspect accountBalance {
	void around(Account account, int amount) : call(* Account.debit(int)) && target(account) && args(amount){
		if(amount > account.getBalance()){
			System.out.println("Your Account does not have enough money to transfer!");
		}else{
			proceed(account,amount);
			System.out.print("Money transfered Sucessfully");
		}
	}

}
