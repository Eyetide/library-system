 // 更换验证码
 $('#captchaImage').click(function() 
 {
     $('#captchaImage').attr("src", "captcha?timestamp=" + (new Date()).valueOf());
 });