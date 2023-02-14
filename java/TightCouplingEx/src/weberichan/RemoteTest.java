package weberichan;

public class RemoteTest {

	public static void main(String[] args) {
		TV 티비 = new TV();
		MultiRemoteController mc = new MultiRemoteController();
		mc.remoteControl(티비);
		
		Computer 콤퓨타 = new Computer();
		mc.remoteControl(콤퓨타);
	
	}

}
