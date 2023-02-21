package weberichan;

public class Family implements Runnable {
private String name;
private Account acc = new Account();

Family(String name){
	super();
	this.name = name;
}

@Override
public void run() { // 가족이 acc에서 돈을 인출
	while (this.acc.getBalance()>0) {
	int money = (int)Math.round((Math.random()*1000000+1)/10);// 1~10000000원까지 랜덤하게 인출
	if(	this.acc.withdraw(money)) {
		System.out.println(this.name+"이" + money + "원을 인출했습니다. 잔액 : "
				+ this.acc.getBalance());
	}
	}
}

}
