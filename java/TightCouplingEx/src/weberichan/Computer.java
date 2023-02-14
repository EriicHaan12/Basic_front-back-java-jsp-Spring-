package weberichan;

public class Computer implements ElectricDevice {

	@Override
	public void powerOn() {
			System.out.println(getClass().getName()+"이 켜집니다");


	}

}
