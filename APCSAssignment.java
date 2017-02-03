import java.util.Random;
public class APCSAssignment extends BankAccount {
	static BankAccount bankAccount = new BankAccount("Bob", "assduff", 420.69);

public static void main(String[] args) {
	System.out.println(bankAccount.getBalance());
}
}