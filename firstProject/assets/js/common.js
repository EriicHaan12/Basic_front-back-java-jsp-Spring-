function getParameter(paramName) {
  let url = location.href;
  let returnVal = null;
  if (url.indexOf("?") != -1) {
    let queryString = url.split("?")[1];
    console.log(queryString);
    let tmpAr = queryString.split("&");
    for (let tmp of tmpAr) {
      if (tmp.split("=")[0] == paramName) {
        returnVal = tmp.split("=")[1];
        break;
      }
    }
  }

  return returnVal;
}
