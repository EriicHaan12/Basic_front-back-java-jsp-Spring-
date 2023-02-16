package weberichan;

public class Korean {
	private String regNo;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegNo() {
		return regNo;
	}

	Korean(String regNo, String name) {
		super();
		this.regNo = regNo;
		this.name = name;
	}

	// equals를 오버라이딩 하는 법
	// source-> override 에서 쉽게 만들 수 있다.
	// equals는 안에 있는 데이터값을 비교할 때 쓴다.
	// 문자열이 아닐때는 안에 있는 데이터값으로 비교하는 게 아닌
	// 주소 값으로 비교하게 된다.
	@Override
	public boolean equals(Object obj) { // ClassCastException 예외 처리
		// 현재 객체와 넘겨 받은 obj 객체의 주민번호와 이름이 같다면 true,
		// 아니면 false 반환 하도록 설정 해야된다.
		boolean result = false;

		if (obj instanceof Korean) {
			Korean tmp = (Korean) obj; // 넘겨줄 obj객체를 다운캐스팅 해줘야 정상적으로
			// Korean 객체를 불러올 수 있다.

			if (this.regNo.equals(tmp.regNo) && this.name.equals(tmp.name)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Korean [regNo=" + regNo + ", name=" + name + "]";
	}
	// hashCode()를 오버라이딩 하는 법
	//마찬가지로 source-> override 에서 쉽게 만들 수 있다.
	@Override
	public int hashCode() {
		//문자열끼리는 더하기가 가능하기 때문에
		// this.name(문자열)+ this.regNo(문자열) = 문자열(최종적으로 비교해야될 문자열) 이 가능해진다.
		// 결과적으로 이름과 주민번호가 하나의 문자열로 묶여
		// equals()로 비교했을 때 name과 regNo가 모두 같아야 true가 반환되도록 해주는 것과
		// 같은 기능을 가지게 된다.
		return (this.name + this.regNo).hashCode();
	}
	
	

	
}
