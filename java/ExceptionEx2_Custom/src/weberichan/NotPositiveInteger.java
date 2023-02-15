package weberichan;

public class NotPositiveInteger extends Exception{
	// error code
	private int errorCode = 503;
	
	public NotPositiveInteger(String msg) {
		super(msg);
	}
	@Override
	public String getMessage() {
		return "에러코드 : " + this.errorCode + ", " + super.getMessage();
	}
}
