const readCookie = (cookieName) => {
  // 쿠키 이름을 매개변수로 받아
  // 넘겨받은 쿠키 이름의 값을 반환하자.
  // 만약 넘겨받은 쿠키의 이름에 해당하는 쿠키가 존재하지 않으면, null 을 받완 하기로 하자.
  // 쿠키가 여러개 일 경우 '쿠키이름 = value' 들은 세미콜론(;) 기준으로 나눠진다.
  //ex) EH=value ; EricHan=value; notice=value ....

  let returnVal = null;

  let cookies = document.cookie;
  if (cookies !== "") {
    // 쿠키가 있다.
    let cookAr = cookies.split(";");
    for (let cookie of cookAr) {
        //배열로 나누고 나선 각 배열마다 앞자리에 공백이 있기 때문에 공백도 제거 해줘야 한다.
      if (cookie.split("=")[0].trim() === cookieName) {
        // 매개변수 cookieName가 실제로 있다.
        returnVal = cookie.split("=")[1]; // 해당하는 쿠키의 값을 returnVal에 대입
        return returnVal;
      }
    }
  }
  return returnVal; // 찾는 쿠키 없다.
};
