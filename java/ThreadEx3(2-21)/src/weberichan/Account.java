package weberichan;

public class Account {
private int balance = 1000000;

public int getBalance() {
	return balance;
}

public boolean withdraw(int money) {
	boolean result = false;
	synchronized (this) {
		if (this.balance >= money) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.balance -= money;
			result = true;
		}
	}
	return result;
	}
}
