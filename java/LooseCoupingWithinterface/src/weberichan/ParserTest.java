package weberichan;

public class ParserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Paserable parser = ParseManager.getParser("data.json");
		parser.parse("json");
	
	}

}
