package weberichan;

public class MultiRemoteController implements Remoteable {

	@Override
	public void remoteControl(ElectricDevice ed) {
		System.out.println(ed.toString()+"을 제어합니다");
		ed.powerOn();
	}

}
