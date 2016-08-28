
public aspect loginValidate {
	
	void around(Bank bank) : call(boolean Bank.login()) && target(bank){
		if(bank.login()){
			System.out.print("Incorrect Username or Password");
		}else{
			System.out.println("\t\t\tYou have successfully logged in\n");
		}
	}

}
