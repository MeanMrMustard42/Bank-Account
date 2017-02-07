import java.util.Scanner;
import java.lang.Thread;
import jpackage.Timer;


public class BankAccount extends Thread {

	static String name;
	static String password;
	static double balance;
	static String securityQuestion;
	static String securityAnswer;
	static Scanner sc = new Scanner(System.in);
	static Timer timer = new Timer();
	static Thread thread = new Thread(timer);
	static boolean isLocked = false;

	public BankAccount(String name1, String password1, String securityQuestion1, double balance1) {
		name = name1;
		password = password1; // the constructor values can be changed in APCSAssignment
		balance = balance1;
		securityQuestion = securityQuestion1;
		// securityAnswer = securityAnswer1;

	}

	public void access() throws InterruptedException {

		System.out.print("Enter your password here: ");
		String userPassword = sc.nextLine();
		if (userPassword.equals(password)) {
			System.out.println("Hello, " + name + ". Thank you for choosing TotallySecureBank(TM).");
			giveAccess();
		} else if (!userPassword.equals(password)) {
			System.out.println("http://tinyurl.com/j4s2erp");
			enforceSecurity(userPassword);
		}

	}

	public void giveAccess() {

		System.out.println("What would you like to do?");
		System.out.println(
				"Press 1 for to retrieve your current balance, press 2 to withdraw money from your account, or press 3 to deposit.");
		int inputTwoPointOh = sc.nextInt();
		sc.nextLine();
		System.out.println("Please enter in the amount you would like to withdraw or deposit."
				+ " If you're not withdrawing or depositing just press 1 again: ");
		double amount = sc.nextInt();

		if (inputTwoPointOh == 1 && amount == 1) {
			System.out.println(getBalance());
		} else if (inputTwoPointOh == 2) {
			System.out.println(withdraw(amount)); // choice menu
		} else if (inputTwoPointOh == 3) {
			System.out.println(deposit(amount));
		} else {
			System.out.println("Stop messing this up");
			giveAccess();

		}

	}
/* Begins a timer that counts down the seconds from 120 (2 minutes). if the timer reaches
 * 0 without you doing anything you'll have a chance to log in again, however if you
 * press f when it tells you to it'll take you to the unlock menu
 */
	private void enforceSecurity(String input) throws InterruptedException {
		Scanner console = new Scanner(System.in);
		if (!input.equals(password)) {
			System.out.println("Our super secure systems have detected a potential hacking attempt on this account.\n"
					+ "Please wait 2 minutes before trying to log in again.\nFeel free to check the status of your locked account by entering 'status'.");
			timer.set(120);
			timer.start();
			if (Thread.interrupted()) { // I don't think this exception is ever thrown now but I'm too lazy to get rid of it
				System.out.println("You dun messed up, boi");
				console.close();
				throw new IllegalThreadStateException();
			}
			isLocked = true;
			String status = console.next();
			if (status.equalsIgnoreCase("status")) {
				System.out.println("Be patient, you still have another " + timer.get() + " seconds to go.");
			}

			if (timer.get() > 0 && timer.get() < 120) { // while the timer is counting down
				System.out.println("If you are the owner of this account, feel free to press f to move to the "
						+ "security interface to unlock your account.\nOtherwise, you will be notified once your two minutes are up.");

				String payRespects = sc.nextLine();
				// sc.nextInt();
				if (payRespects.equalsIgnoreCase("f")) {
					unlockAccount();

				} else {
					System.out.println("http://tinyurl.com/j4s2erp"); // after this point you have to wait the 2 minutes
				}

			}

			if (timer.get() == 0) {
				System.out.println("You are now free to try to log in again.");
				access();
				console.close();
			}
		}
	}

	private double deposit(double x) {
		if (x < 0) {
			System.out.print("What the hell dude? Try again. Enter a positive value to deposit: ");
			int z = sc.nextInt();
			System.out.println("Don't attempt to deposit a negative more than once or else you'll give me a seizure (sorry).");
			deposit(z); // goes through deposit process with new value
			System.out.print("Your current balance is now ");
			sc.close();
			timer.close();
			return balance;
		}
		balance = balance + x;
		thread.interrupt();
		System.out.print("Your current balance is now ");
		return balance;

	}

	private double withdraw(double y) {
		if (y < 0 || balance - y < 0) {
			System.out.print("Looks like you're new at this. Let's try that again. Please enter a new value to withdraw: ");
			int z = sc.nextInt();
			System.out.println("Don't attempt to overdraft more than once or else you'll give me a seizure (sorry).");
			withdraw(z);
			System.out.print("Your current balance is now ");
			sc.close();
			timer.close(); // breaks timer out of countDown() loop, causing timer to stop
			return balance;

		}
		balance = balance - y;
		thread.interrupt();
		System.out.print("Your current balance is now ");
		return balance;

	}

	private double getBalance() {
		thread.interrupt();
		System.out.print("Your current balance is "); 
		sc.close();
		timer.close();
		return balance;

	}

	private void unlockAccount() throws InterruptedException {
		System.out.print("K buddy. Here's a question you might remember: " + securityQuestion);
		String answer = sc.nextLine().toLowerCase();
		if (answer.contains("patriots") || answer.contains("new england")) { // :(
			isLocked = false;
 			giveAccess();
			thread.interrupt(); // stops thread and timer (to be sure of no errors) - we won't need them after this point 
			timer.close();
		} else {
			System.out.println("u dun mesd up");
			unlockAccount(); // takes you back to unlock menu if you somehow manage to mess this up
		}

	}

}
