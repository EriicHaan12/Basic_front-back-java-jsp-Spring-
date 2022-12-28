

function Account(owner, accNum, balance) {
    // 객체에 속성 부여
    this.owner = owner; // this. 쓰는 이유 지금 만들어 지는 객체의 owner 에 owner 속성을 부여하겠다는 뜻.
    this.accNum = accNum;
    this.balance = balance;
    this.bankName = "우리은행";
    this.name = "직장인 비과세 예금";

    // 객체에 기능부여
    this.inquiry = function () {
      return this.balance;
    };
    

    this.deposit = function (money) {
      // 예금하기
      this.balance += money;
    };
    this.withDraw = function (money) {
      // 인출하기
      if (this.balance >= money) {
        this.balance -= money;
      } else {
        window.alert("잔액이 부족합니다!");
      }
    };
  }
