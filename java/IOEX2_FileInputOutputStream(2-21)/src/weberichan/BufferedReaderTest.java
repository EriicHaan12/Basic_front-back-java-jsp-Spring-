package weberichan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class BufferedReaderTest {

	public static void main(String[] args) {
		//BufferedReader 사용 해서 객체 생성
	

		String filePath = "D:\\lecture\\java\\ioex.txt";
		String copyPath = "D:\\lecture\\java\\CopyBufferedWriter.txt";
		
		BufferedReader brF  = null ; 
		BufferedWriter bw = null;
		FileReader fr = null;
	
		int copyText = 0;
	
	try {
		fr = new FileReader(filePath);
		brF = new BufferedReader(fr);
		
		int data = 0;
		try {
			while((data = fr.read())!=-1) {
				System.out.print((char)data);
				copyText =(char)data;
		
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		bw = new BufferedWriter(new FileWriter(copyPath, true));
//		bw.write();
		// 파일 생성은 했으나 , 안에 데이터를 원하는 텍스트를 제대로 
		// 입력 하지 못하고 있음...
		System.out.print(fr.hashCode());
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
	
	}

}
