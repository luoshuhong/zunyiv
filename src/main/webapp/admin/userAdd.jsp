<%-- 
Title: userAdd.jsp
Description: 用户添加
Company: zunyiv
@author: luoshuhong
date 2016
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<script type="text/javascript" src="${ctx}/js/DatePicker/WdatePicker.js"></script>
	</head>

	<body>
	<form class="form-horizontal" style="margin-top: 20px;" action="javascript:save();" >
		<div class="form-group">
			<label for="phone" class="col-sm-2 control-label">手机号：</label>
			<div class="col-sm-10">
				<input id="phone" type="text" class="form-control"  name="phone"  style="width: 260px;" placeholder="请输入手机号(初始密码为手机号)" required="required" >
			</div>
		</div>
		<%--<div class="form-group">--%>
			<%--<label for="channelName" class="col-sm-2 control-label">名字：</label>--%>
			<%--<div class="col-sm-10">--%>
				<%--<input  id="channelName" type="text" class="form-control" name="realName" style="width: 200px;"  placeholder="请输入名称" required="required">--%>
			<%--</div>--%>
		<%--</div>--%>

		<div class="form-group">
			<label for="role" class="col-sm-2 control-label">角色：</label>
			<div class="col-sm-10">
				<select class="form-control" style="width: 260px;" id="role">
				  <option value="0">普通</option>
				  <option value="1">管理员</option>
				  <option value="2">超级管理员</option>
				</select>
			</div>
		</div>
		<%--<div id="freeCountDiv" class="form-group" style="display: none;">--%>
			<%--<label for="freeCount" class="col-sm-2 control-label">免费份数：</label> --%>
			<%--<div class="col-sm-10">--%>
				<%--<select class="form-control" style="width: 200px;" id="freeCount" >--%>
				  <%--<option value="1">免费1份68元</option>--%>
				  <%--<option value="3">免费3份168</option>--%>
				  <%--<option value="5">免费5份268元</option> --%>
				<%--</select>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div id="buyIntroduceDiv" class="form-group" style="display: none;">--%>
			<%--<label for="freeDes" class="col-sm-2 control-label">购买提示：</label>--%>
			<%--<div class="col-sm-10">--%>
				<%--<input id="buyIntroduce"  type="text" class="form-control"   name="freeDes"  style="width: 200px; display: inline;" placeholder="请输入">--%>
				 <%--最长17个字。--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div id="freeDesDiv" class="form-group" style="display: none;">--%>
			<%--<label for="freeDes" class="col-sm-2 control-label">前台文案：</label> --%>
			<%--<div class="col-sm-10">--%>
				<%--<input id="freeDes"  type="text" class="form-control"   name="freeDes"  style="width: 200px; display: inline;" placeholder="请输入前台展示文案"> --%>
				<%--<!-- <label style="color: red;">XXX</label>已为你免单！ -->最长10个字。--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div id="freeStartDateDiv"  class="form-group"  style="display: none;">--%>
			<%--<label for="freeStartDate" class="col-sm-2 control-label">开始时间：</label> --%>
			<%--<div class="col-sm-10">--%>
				<%--<input id="freeStartDate"  type="text" class="form-control" style="width: 200px;" onClick="WdatePicker()" >--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div id="freeEndDateDiv" class="form-group" style="display: none;">--%>
			<%--<label for="freeEndDate" class="col-sm-2 control-label">结束时间：</label> --%>
			<%--<div class="col-sm-10">--%>
				<%--<input type="text" id="freeEndDate" class="form-control" style="width: 200px;" onClick="WdatePicker()">--%>
			<%--</div>--%>
		<%--</div>--%>
		
		<!---------- 奥斯卡对接需要熟悉  start ------------------>
		<%--<div class="form-group">--%>
			<%--<label for="oscarType" class="col-sm-2 control-label">奥斯卡对接：</label> --%>
			<%--<div class="col-sm-10">--%>
				<%--<select class="form-control" style="width: 200px;" id="oscarType" onchange="oscarChose();">--%>
				  <%--<option value="1">否</option>--%>
				  <%--<option value="0">创建奥斯卡项目</option>--%>
				<%--</select>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div id="oscar" style="display: none;">--%>
			<%--<div id="form" class="form-group" >--%>
				<%--<label class="col-sm-2 control-label">手机</label> --%>
				<%--<div class="col-sm-10">--%>
					<%--<input type="checkbox" name="phone1" id="phone1" checked="checked" disabled>&nbsp; 是否收集&nbsp; --%>
					<%--<input type="checkbox" name="phone2" id="phone2" checked="checked" disabled>&nbsp; 是否必填&nbsp; --%>
					<%--<input type="checkbox" name="phone3" id="phone3" >&nbsp; 学院搜集时显示&nbsp; --%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div id="form" class="form-group" >--%>
				<%--<label class="col-sm-2 control-label">邮件</label> --%>
				<%--<div class="col-sm-10">--%>
					<%--<input type="checkbox" name="email1" id="email1" >&nbsp; 是否收集&nbsp; --%>
					<%--<input type="checkbox" name="email2" id="email2" >&nbsp; 是否必填&nbsp; --%>
					<%--<input type="checkbox" name="email3" id="email3" >&nbsp; 学院搜集时显示&nbsp; --%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div id="form" class="form-group" >--%>
				<%--<label class="col-sm-2 control-label">居住地</label> --%>
				<%--<div class="col-sm-10">--%>
					<%--<input type="checkbox" name="workHouse1" id="workHouse1" >&nbsp; 是否收集&nbsp; --%>
					<%--<input type="checkbox" name="workHouse2" id="workHouse2" >&nbsp; 是否必填&nbsp;--%>
					<%--<input type="checkbox" name="workHouse3" id="workHouse3" >&nbsp; 学院搜集时显示&nbsp; --%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div id="form" class="form-group" > --%>
				<%--<label  class="col-sm-2 control-label">户籍</label> --%>
				<%--<div class="col-sm-10"> --%>
					<%--<input type="checkbox" name="house1" id="house1" >&nbsp; 是否收集&nbsp; --%>
					<%--<input type="checkbox" name="house2" id="house2" >&nbsp; 是否必填&nbsp; --%>
					<%--<input type="checkbox" name="house3" id="house3" >&nbsp; 学院搜集时显示&nbsp; --%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div   class="form-group" >--%>
				<%--<label for="oscarStartDate" class="col-sm-2 control-label">项目开始时间：</label> --%>
				<%--<div class="col-sm-10">--%>
					<%--<input id="oscarStartDate"  type="text" class="form-control" style="width: 200px;" onClick="WdatePicker()" >--%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div  class="form-group" >--%>
				<%--<label for="oscarEndDate" class="col-sm-2 control-label">项目结束时间：</label> --%>
				<%--<div class="col-sm-10">--%>
					<%--<input type="text" id="oscarEndDate" class="form-control" style="width: 200px;" onClick="WdatePicker()">--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
		<!---------- 奥斯卡对接需要熟悉  end ------------------>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input class="btn btn-primary" type="submit"  value="添加">
			</div>
		</div>
	</form>
</body>

<script type="text/javascript">
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
			url: "${ctx}/admin/userAdd",
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