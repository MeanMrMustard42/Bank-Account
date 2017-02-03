import java.util.Scanner;
import package.Timer;

public class BankAccount {
	static String name;
	static String password;
	static double balance;
	static Scanner sc = new Scanner(System.in);
	static Timer timer = new Timer;
	public BankAccount(String name1, String password1, double balance1) {
        name = name1;
		password = password1;
		balance = balance1;
	 
		
	}	
	public double getBalance() {
		System.out.println("Enter your password here: ");
		String userPassword = sc.nextLine();
		if(userPassword.equals(password)) {
					return balance;

		}
		else {
			timer.start();
			System.out.println("OMGWTF YOU'RE A 1337 HAXXOR!!!! THIS ACCOUNT HAS BEEN LOCKED. YOU WILL NOT BE ABLE TO ACCESS THIS BANK ACCOUNT FOR %0 SECONDS");
			return 0.0;
		}
	}

	

	}


