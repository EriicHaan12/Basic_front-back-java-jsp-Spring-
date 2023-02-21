package weberichan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutputStreamTest {

	public static void main(String[] args) {
		String filePath ="D:\\lecture\\java\\ioex.txt";
		//복사해보기
		String copyFile = "D:\\lecture\\java\\ioex.txt";
		FileInputStream fis =null;
		FileOutputStream fos = null;
		
		
		try { // 파일이 없거나 못 불러 올수 있어서 예외 처리를 해줘야함
			fis = new FileInputStream(filePath);
			fos = new FileOutputStream(copyFile);
				int data = 0; // input 클래스 자체가 int 타입을 반환해서 문자열은 아스키 코드로 다시 반환 시켜주는 속성을 가짐
				while ((data =fis.read())!=-1) {
					System.out.print((char)data); // 아스키 코드 값을 다시 문자열로 반환 시켜주고 싶다면
					//한글은 기본 한글자에 2byte라 1byte 씩 
					// 읽어오는 FileInputStream 의 read() 메소드로는 제대로 불러 올수 없다.
					fos.write(data);
				}
				fis.close();
				fos.close();
			}  catch (FileNotFoundException e) { 
				 // 파일이 존재하지 않을 때 나는 에러에 대한 예외 처리
				e.printStackTrace();
			}catch (IOException e) {
				// 파일을 읽어오다 발생한 에러에 대한 예외 처리
				e.printStackTrace();
			}
	}
}
