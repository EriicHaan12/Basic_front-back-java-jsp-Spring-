const getParameter = (paraName) => {
  // 쿼리스트링에서 넘겨받은 paraName을 찾아 그 변수의 값을 return
  //만약 쿼리스트링에 paraName 없다면 null을 return
  let returnVal = null;
  let url = location.href;

  if (!url.indexOf("?") != -1) {
    // 쿼리스트링이 있을때
    let queryStr = url.split("?")[1];
    let tmpAr = queryStr.split("&");

    for (let tmp of tmpAr) {
      if (tmp.split("=")[0] == paraName) {
        returnVal = tmp.split("=")[1];
        break; // 해당 반복문 블럭 빠져나감.
      }
    }
  }

  return returnVal;
};
