package weberichan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOTest {

	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9}; // 원본(데이터 출발지)
		byte[] outSrc = null; // 목적지
		
		//입출력 할 스트림 객체 생성
		InputStream input  = null; 
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
		
		int data = 0;
		try { // 원본 데이터의 끝이 아닐동안 1byte씩 읽어서(read) 출력스트림에 1byte씩 write 
			while ((data = input.read())!=-1) { // -1 : EOF(End of File) 데이터가 끝날때 나오는 숫자
				output.write(data);// 출력할 때 output으로
			}
		} catch (IOException e) { // input, output 예외 처리를 한꺼번에 처리
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outSrc = output.toByteArray();
		   for(byte b : outSrc) {
			   System.out.print(b + "\t");
		   }

		   try {
			input.close(); // 입출력 스트림 객체는 입출력이 완료 되면, close  해줘야 한다.(객체 소멸)
			   output.close();
		} catch (IOException e) {
			// 소멸시켜줘야 data 자원이 release 상태가 된다.
			e.printStackTrace();
		}
	}

}
