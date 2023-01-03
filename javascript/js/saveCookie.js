function saveCookie(cookieName, cookieVal, expires) {
  //넘겨받은 cookName 이라는 이름으로,
  // 넘겨받은 cookieVal 값을 쿠키에 저장하되,
  // 만료일은 넘겨받은 expires (현재날짜 + expires ) 일로 저장하자.
  let now = new Date();
  now.setDate(now.getDate() + expires);
  let tmpCookie = cookieName + "=" + cookieVal + ";expires=" + now.toUTCString;
  document.cookie = tmpCookie;
}
