
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
    	<style type="text/css">
			body {
				background: #444;
			}
			.loginBox {
				padding: 0 15px;
				border: 1px solid #fff;
				color: #000;
				border-radius: 8px;
				background: white;
				box-shadow: 0 0 15px #222;
  				position: absolute;  
				width: 350px;
				height: 250px;
  				left: 50%;  
  				top: 50%;  
   				margin-left: -225px;   
   				margin-top: -160px;   
			}
			.loginBox h2 {
				height: 40px;
				font-size: 25px;
				font-weight: normal;
			}
		</style>
	</head>
	
	<body>
		<div class="container">
		  <div class="loginBox row">
		  		<h2 class="text-center">后台登录</h2>
				<form id="loginForm" action="javascript:login();"  method="post" class="form-horizontal lg-form">
				  <div class="form-group has-success">
				    <label for="username" class="col-sm-3 col-md-3 control-label">用户名</label>
				    <div class="col-sm-9 col-md-9">
				      <input id="username" type="text" class="form-control" name="username" placeholder="请输入用户名" value="" required="required">
				    </div>
				  </div>
				  <div class="form-group has-success">
				    <label for="password" class="col-sm-3 col-md-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
				    <div class="col-sm-9 col-md-9">
				      <input id ="password" type="password" class="form-control" name="password" placeholder="请输入密码" required="required">
				    </div>
				  </div>
			  	  <div class="form-group">
			  	  	<div id="errMsg" class="col-sm-offset-4 col-sm-10" style="color: #990033;"></div> <!-- 这里是登陆错误信息 -->
				  </div>
				  <div class="form-group container-fluid">
				    <div class="col-sm-offset-2 col-sm-4 col-md-4">
<!-- 				     <div class="col-sm-offset-1 col-sm-10 col-md-10"> -->
				      	<button id="loginBtn" type="submit" class="btn btn-info" data-loading-text="正在登录..."  >登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
<!-- 			      	    <button class="btn btn-info" type="reset">清 空</button> -->
				    </div>
				    <div class="col-sm-6 col-md-6">
				  		<label><input type="checkbox" id="autologin">&nbsp;两周内自动登录</label>
			  		</div>
				  </div>
		  		</form>
			</div>
		</div>
		<!--.content-->
	</body>
	
	<script type="text/javascript">
		if(top.location!=self.location){
			parent.location.reload();
		}
		
   		// 登录方法
        function login() {
			alert('login');
        	var username = $("#username").val();
    		var password = $("#password").val();
    		var autoLogin = -1;
    		if($("#autologin").prop("checked") == true) {
    			autoLogin = 0;
    		}
    		var postData = {"username":username, "password":password, "autoLogin":autoLogin};
            $.ajax({
    			type: "POST",
    			url: "${ctx}/admin/login.do",
    			data: postData,
    			async:false,
    			success : function(msg) {
    				var result = eval('(' + msg + ')');
    				if (result.status == 'success') {
    					window.location.href = "${ctx}/admin/index.jsp";
    				} else {
    					$("#errMsg").html('用户名或密码错误,请重试');
    					var password = $("#password").val('');
    				}
    			}
    		});
        }
   		
   	</script>
</html>