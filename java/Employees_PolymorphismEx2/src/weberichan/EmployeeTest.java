package weberichan;

public abstract class EmployeeTest {
	
	public static void main(String[] args) {
			Department 총무부 = new Department(10,"총무부");
		
			//이렇게 쓰면 Department 객체가 소멸되면 Permanent 객체도 같이 사라진다.
			//composition 관계
//			총무부.addEmployee(new Permanent("100011001", "정대만", 10, 1000000));

			
			
			// 사원을 먼저 만들고 permanent 객체에 넣어줘야 Department 객체가 사라지더라도 
			// 사원 정보는 남아있게 된다. 
			//Aggregation
			Employee 채치수 = new Permanent("100011001", "채치수", 10, 1000000);		
			총무부.addEmployee(채치수);
			

			총무부.addEmployee(new PartTimer("100011002", "강백호", 10, 5,10000));
		
			총무부.outputEntireEmployees();
	}
	


}
