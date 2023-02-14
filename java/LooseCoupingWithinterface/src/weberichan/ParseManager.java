package weberichan;

public class ParseManager {
	public static Paserable getParser(String fileName) {
		Paserable result = null;
		String ext = fileName.substring(fileName.indexOf(".")+1);
		if(ext.toLowerCase().equals("xml")) {
			return new XMLParser();
			} else if(ext.toLowerCase().equals("json")){
			result = new JSONParser();
			}
			return result;
	}
}
