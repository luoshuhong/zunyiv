<%-- 
Title: userFillWeiboTail.jsp
Description: 用户添加
Company: zunyiv
@author: luoshuhong
date 2017
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="shortcut icon" type="image/x-icon" href="../images/head_32.ico" media="screen" />
		<script type="text/javascript" src="${ctx}/js/DatePicker/WdatePicker.js"></script>
		<title>补全微博小尾巴</title>

		<style>
			.col-center-block {
				float: none;
				display: block;
				margin-left: auto;
				margin-right: auto;
				text-align: center;
			}
		</style>

	</head>

	<body>
	<div class="container-fluid col-center-block col-xs-12 col-md-4">
		<form class="form-horizontal" style="margin-top: 20px; margin-left: auto; margin-right: auto;" method="post" action="${ctx}/admin/user/weixin/fillWeiBoTailSave" >
			<div class="form-group">
				<label for="phone" class="col-sm-3 col-xs-4 control-label">手机号：</label>
				<input id="phone" type="text" class="form-control col-sm-9" value="${phone}"  name="phone"  style="width: 200px;" placeholder="请输入(初始密码为手机号)" required="required" >
			</div>
			<div class="form-group">
				<label for="realName" class="col-sm-3 col-xs-4 control-label">姓名：</label>
				<input  id="realName" type="text" class="form-control col-sm-9" value="${realName}" name="realName" style="width: 200px;"  placeholder="请输入真实姓名" required="required">
			</div>

			<div class="form-group">
				<label for="professional" class="col-sm-3 col-xs-4 control-label">职业：</label>
				<div class="col-sm-9">
					<select class="form-control" style="width: 200px;" name="professional" id="professional">
						<option value="0">学生</option>
						<option value="1">工作</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="birthday" class="col-sm-3 col-xs-4 control-label">生日：</label>
				<div class="col-sm-9">
					<input id="birthday" name="birthday"  style="width: 200px;" type="text" class="form-control" onClick="WdatePicker()" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="tail" class="col-sm-3 col-md-3 col-xs-4 control-label">请选择小尾巴：</label>
				<div class="col-sm-9 col-md-9">
					<select class="form-control" style="width: 200px;" id="tail" name="tail">
						<option value="无">无</option>
						<c:forEach var="tail" items="${weiboTail}">
							<option value="${tail}">${tail}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<%--<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>--%>
			<div class="form-group col-center-block" >
				<input class="btn col-xs-offset-3 btn-primary col-xs-6" type="submit"  value="添  加">
			</div>
		</form>
	</div>

	</body>

<script type="text/javascript">
	if ('${resultMsg}' != undefined && '${resultMsg}' !== '') {
		alert('系统异常，请重新填写');
	}
	 function save() {
		var phone = $("#phone").val();
		var role = $("#role").val();

		/******** 参数校验 start ******************/
//		if (isEmpty(phone)  ) {
//			alert('参数不能为空！');
//			return;
//		}
		/******** 参数校验 end ******************/
		
		var postData = {"phone":phone, "role":role };
        $.ajax({
			type: "POST",
			url: "${ctx}/admin/user/weixin/fillWeiBoTailSave",
			data: postData,
			async:false,
			success : function(msg) {
				eval("var json=" + msg);
				if (json.status == 'success') {
					if ('exist' == json.data) {
						alert('手机号已存在！');
						return;
					}
					alert('添加成功！');
					window.location.replace("userQuery.jsp");
				} else {
					alert('添加失败，请重试！');
				}
			}
		});
	}
	
	/**
	 *校验是否为null
	 */
	function isEmpty(value) {
		if ('' == value || undefined == value || null == value || value.trim().length == 0) {
			return true;
		}
		return false;
	}
</script>


</html> 