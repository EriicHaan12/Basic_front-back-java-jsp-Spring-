package weberichan;

public class TV implements ElectricDevice {

	


	@Override
	public void powerOn() {
		System.out.println(getClass().getName()+"이 켜집니다");

	}

}
