package weberichan;
//깊은 복사, 얕은 복사


public class OOPEx2_InstanceCopy {

	public static void main(String[] args) {
		Student s1 = new Student("980011", "홍찰찰", 56, 73, 98);
		System.out.println(s1.toString());
		
		//얕은 복사
		//원본을 수정하게 되면
		Student s2 =s1;
		s2.setKor(0);
		System.out.println(s1.toString());// 복사본도 영향 받는다.
		System.out.println(s2.toString());
	
	//깊은 복사(생성자를 통한 깊은 복사)
	Student s3 = new Student("980012", "박해피", 0, 100, 96); 
		
		Student deepCopyS3= new Student(s3);
		
		
		System.out.println(s3.hashCode());
		System.out.println(s3.toString());
		
	
		
		System.out.println(deepCopyS3.toString());
		System.out.println(deepCopyS3.hashCode());
		
		
		s3.setStuName("도우너");
		System.out.println(s3.toString());
		System.out.println(deepCopyS3.toString());
		
	}

}
