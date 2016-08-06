 // 更换验证码
 $('#captchaImage').click(function() 
 {
     $('#captchaImage').attr("src", "captcha?timestamp=" + (new Date()).valueOf());
 });
 
 /**
  * 修改当前页码，调用后台重新查询
  */
 function changeCurrentPage(currentPage) 
 {
 	$("#currentPage").val(currentPage);
 	$("#mainForm").submit();
 }