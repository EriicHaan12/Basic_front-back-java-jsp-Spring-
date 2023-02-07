package weberichan;

import java.util.Iterator;
import java.util.Scanner;

public class ScoreV3 {
//		private StuClass c1;
	
	private static int curSavedStudentCnt = 0;// 현재 저장되어 있는 학생 수
	
	//getter
	int getCurSavedStudentCnt() {
		return curSavedStudentCnt;
	}
	
	private static void outputMenu() {
		System.out.println("=========================================");
		System.out.println("==              성적표V3               ==");
		System.out.println("=========================================");
		System.out.println("==1. 반 생성                           ==");
		System.out.println("==2. 반 생성된 반에 학생 입력(1번부터 수행)");
		System.out.println("==3. 전체 학생 점수 출력               ==");
		System.out.println("==4. 반, 학생 자동 생성                ==");
		System.out.println("==5. 학생 성적 출력(by 학생 번호)      ==");
		System.out.println("==6. 학생 삭제(by 학생 번호)           ==");
		System.out.println("==9. 종료                              ==");
		System.out.println("=========================================");
		System.out.println("메뉴 번호 입력 >>>");

	}

	private StuClass createClass() {
		Scanner nosc = new Scanner(System.in);
		Scanner nameSc = new Scanner(System.in);
		System.out.println("반 번호 입력>>>");
		int classNo = nosc.nextInt();
		System.out.print("과정명 입력>>>");
		String className = nameSc.nextLine();// 
		
		StuClass c1 = new StuClass(classNo, className);
		System.out.println(c1.toString());
		return c1;
	
	}
	
	private void inputStudent(StuClass stuClass) {
		if(stuClass ==null) {
			System.out.println("반 부터 생성하고 오세요");
			return;
		} else {
			if (curSavedStudentCnt <StuClass.STUDENT_COUNT) {
				Scanner stuNumSC = new Scanner(System.in);
				Scanner stuStringSC = new Scanner(System.in);
				System.out.print("학생 번호>>> ");
				String stuNo = stuStringSC.nextLine();
				System.out.print("학생 이름>>> ");
				String stuName = stuStringSC.nextLine();
				System.out.print("국어>>> ");
				int kor = stuNumSC.nextInt();
				System.out.print("영어>>> ");
				int eng = stuNumSC.nextInt();
				System.out.print("수학>>> ");
				int math = stuNumSC.nextInt();
//				Student stu = new Student(stuNo, stuName, kor, eng, math);
//				System.out.println(stu.toString());
				stuClass.addStudent(new Student(stuNo, stuName, kor, eng, math), 
									curSavedStudentCnt);
				
					curSavedStudentCnt++;
			}else {
				System.out.println("학생 수가 초과 됩니다.");
			}
			//return stuClass; // 리턴이 필요 없다. stuClass 가 참조 타입이므로
			// call by reference에 의해 리턴 불필요
		}
	}
	
	
	
	public static void main(String[] args) {
		
		ScoreV3 sc = new ScoreV3();
		StuClass stuClass =null;
		while (true) {

			outputMenu();
			Scanner menuSc = new Scanner(System.in);
			int menu = menuSc.nextInt();

			switch (menu) {

			case 1:
				stuClass = sc.createClass();
				break;
			case 2:
			sc.inputStudent(stuClass);
				break;
			case 3:
				sc.outputEntireStudent(stuClass);
				break;
			case 4:
				stuClass= sc.autoInputStudent(stuClass);
				break;
			case 5:
				sc.findStudentByNo(stuClass);
				break;
			case 6:
				sc.deleteStudentByNo(stuClass);
				break;

			case 9:
				System.exit(menu);//  프로그램 종료
				break;

			default:
				break;
			}

		}
		
	}// main 메서드 끝
	
	private void deleteStudentByNo(StuClass stuClass) {
		Scanner stuStringSC = new Scanner(System.in);
		System.out.print("학생 번호>>> ");
		String stuNo = stuStringSC.nextLine();
		
		stuClass.deleteStudentByNo(stuNo);
	}
	
	
	
	private void findStudentByNo(StuClass stuClass) {
		//학번으로 해당 학번의 학생 성적 출력하기
		//해당 학번의학생 정보가 없으면 "학생 정보 없음!"이라고 출력
		Scanner stuStringSC = new Scanner(System.in);
		System.out.print("학생 번호>>> ");
		String stuNo = stuStringSC.nextLine();
		
	 	stuClass.findStudentByNo(stuNo);
	}
	
	
	private StuClass autoInputStudent(StuClass stuClass) {
		Student[] stuList = {
				new Student("98001", "둘리", 78, 55, 34),
				new Student("98002", "도우너", 88, 25, 94),
				new Student("98003", "도치", 95, 98, 14)
		};// 학생 미리 생성
		curSavedStudentCnt=3; 
	return new StuClass(6, "k디지털", stuList); //반 생성 
	}
	
	
	private void outputEntireStudent(StuClass stuClass) {
		System.out.println("========================================");
		System.out.println("==              성 적 표              ==");
		System.out.println("== 반번호 : "+stuClass.getNo()+
							   "과정명 : " + stuClass.getClassName());
		System.out.println("========================================");
		stuClass.outputEntireStudent();
		System.out.println("========================================");
		System.out.println("전체 총점 : "+ stuClass.calcTotEvery());
		System.out.println("========================================");
		System.out.println("전체 평균 : " + stuClass.calcTotalAvg());
		}
	}
