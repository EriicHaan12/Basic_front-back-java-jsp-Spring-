package weberichan;

public class JSONParser implements Paserable {

	@Override
	public void parse(String extension) {

		if(extension.toLowerCase().equals("josn")) {
			System.out.println("파싱  가능한 json 파일입니다... 파싱시작");
		}
		
	}

}
