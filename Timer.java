package Package;

public class Timer {
public static int x = 0;
public boolean timerIsZero = true;

public void start() throws InterruptedException {
	if (x == 0) {
		timerIsZero = true;
	}
	while (x<2147483647 && x > -2147483647) {
		Thread.sleep(1000);
		++x;
	}
	
}

public void reset() {
	x = 0;
	timerIsZero = true;
	
	
	
}

public void set(int y) {
	x = y;
	if (x == 0) {
		timerIsZero = true;
	}
	
}
public int get() {
	return x;
}
}
