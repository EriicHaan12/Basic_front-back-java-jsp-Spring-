package weberichan;

public class XMLParser implements Paserable {

	@Override
	public void parse(String extension) {
	if(extension.toLowerCase().equals("xml")) {
		System.out.println("파싱  가능한 xml 파일입니다... 파싱중");
	}

	}

}
