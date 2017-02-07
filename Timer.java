package jpackage;

public class Timer extends Thread {
public static int x = 0;

public void countUp() { //because start() was already taken by the Thread class
	
	while(x > -2147483648 && x < 2147483647) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Error... hopefully you don't see this");
			e.printStackTrace();
		}
		++x;
		if(x == -2147483645) {
			break;
		}
	}
		
}
/* the user will have to set their code to a positive number using timer.set() unless they want
 * timer.get() counting down into the negatives
 */

public void countDown() { 
	while(x > -2147483648 && x < 2147483647) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Error... hopefully you don't see this. If you did I probably screwed up really badly somewhere.");
		///	e.printStackTrace();
		}
		--x;
		if(x == -2147483645) {
			break;
		}
	}
		
}

	



public void reset() {
	x = 0;
	
}

public void close() {
	x = -2147483645; // because why would you ever need to count this low
}

public void set(int y) {
	x = y;
	
}
public int get() {
	return x;
}
public void run() { // normally I would do a thing here that lets you either use countUp() or countDown() but screw that right now
	countDown();
}
}
