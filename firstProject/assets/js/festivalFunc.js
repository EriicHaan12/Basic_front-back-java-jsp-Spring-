function ajaxCall(baseUrl) {
  $.ajax({
    url: baseUrl,
    type: "GET",
    dataType: "json",
    async: false,
    success: function (data) {
      parsing(data);
      console.log(data);
    },
    error: function () {},
    complete: function () {},
  });
}
