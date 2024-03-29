<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%--   <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房屋租赁系统</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="wrapper">

	<div class="container">
		<h1>房屋租赁系统</h1>
		<form class="form" id="loginform" action="${pageContext.request.contextPath}/logincheck.action" method="post">
			<div><input type="text" name="username" placeholder="用户名" ></div>
			<div><input type="password" name="password" placeholder="密码" ></div>
			<div>
		<label class="radio inline">
      	  	<input id="admin" type="radio" name="type" value="admin" checked/> 管理员
		</label>
		<label class="radio inline">
			<input id="zuke" type="radio" name="type" value="zuke"/> 租客
		</label>
		<br />
		<%--<label>
			<input type="checkbox" name="remember" value="1" />记住用户名&nbsp;
			<input type="checkbox" name="autologin" value="1" />自动登录
		</label>--%>

		</div>
        
			<button type="submit" id="login-button">登录</button>
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
    src="${pageContext.request.contextPath }/js/localization/messages_zh.js"></script>
<script type="text/javascript">



	 $().ready(function() {
        // 在键盘按下并释放及提交后验证提交表单
        $("#loginform").validate({
        	/*success:function(){
             //label.text('').addClass('valid');
            $('#login-button').click(function(event){
			event.preventDefault();
			$('form').fadeOut(500);
			$('.wrapper').addClass('form-success');

		});

        },*/
            rules : {
                username : {
                    required : true,
                },
               
                password : {
                    required : true,
                  
                },
                
            },
            messages : {
                username : {
                    required : "请输入用户名",
                },
               
                password : {
                    required : "请输入密码",
                    
                },
                
            }
        });
    })

var error="${error}";
if(error=="error"){

alert("帐户名或密码错误");
}



</script>
</body>
</html>