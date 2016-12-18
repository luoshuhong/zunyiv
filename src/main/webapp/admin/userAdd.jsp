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
	<form class="form-horizontal" style="margin-top: 20px;">
		<div class="form-group">
			<label for="channelCode" class="col-sm-2 control-label">手机号：</label>
			<div class="col-sm-10">
				<input id="channelCode" type="text" class="form-control"  name="channelCode"  style="width: 200px;" placeholder="请输入手机号">
			</div>
		</div>
		<div class="form-group">
			<label for="channelName" class="col-sm-2 control-label">名字：</label>
			<div class="col-sm-10">
				<input  id="channelName" type="text" class="form-control" name="realName" style="width: 200px;"  placeholder="请输入名称" required="required">
			</div>
		</div>

		<div class="form-group">
			<label for="freeType" class="col-sm-2 control-label">职业：</label>
			<div class="col-sm-10">
				<select class="form-control" style="width: 200px;" id="freeType" onchange="choseFreeType();">
				  <option value="-1">请选择</option>
				  <option value="0">学生</option>
				  <option value="1">工作</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="freeType" class="col-sm-2 control-label"><a target="_self" href="https://api.weibo.com/oauth2/authorize?client_id=2953793759&redirect_uri=http://www.zunyi.me/weibo/redirect&response_type=code">微博授权</a></label>
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
				<input class="btn btn-primary" type="submit"   value="添加">
			</div>
		</div>
	</form>
</body>

<script type="text/javascript">
	 function save() {
		var name = $("#channelName").val();
		var code = $("#channelCode").val();
		var freeDes = $("#freeDes").val();
		var freeStartDate = $("#freeStartDate").val();
		var freeEndDate = $("#freeEndDate").val();
		var freeType = $("#freeType").val();
		var freeCount = $("#freeCount").val();
		 var buyIntroduce = $("#buyIntroduce").val();

		/******** 参数校验 start ******************/
		if (name.length > 15 || code.length > 15) {
			alert('渠道名或编码长度需小于15！');
			return;
		}
		if (isEmpty(name)  ) {
			alert('参数不能为空！');
			return;
		}

		
		if (0 == freeType) {	//限免(有时间限制)
			freeDes = "";
			if (isEmpty(freeStartDate) || isEmpty(freeEndDate)) {
				alert('参数不能为空！');
				return;
			}
		}
		//渠道免费（有文案描述）
		if (1 == freeType ) {
			if (isEmpty(freeDes) || isEmpty(freeStartDate) || isEmpty(freeEndDate)) {
				alert('参数不能为空！');
				return;
			}
		}
		/******** 参数校验 end ******************/
		
		//oscar 项目对接信息
		var oscarType = $('#oscarType').val();
		var phone=1, email=-1, workHouse=-1, house=-1; //状态 -1：不需要 0：需要  1：必填
		var phoneSite=0, emailSite=0, workSite=0, houseSite=0; //状态 0:开始收集  1：选学院时收集
	    if($("#email1").prop("checked")==true) {
			email = 0;
	    }
	    if($("#email2").prop("checked")==true) {
	    	email = 1;
	    }
	    if($("#workHouse1").prop("checked")==true) {
			workHouse = 0;
	    }
	    if($("#workHouse2").prop("checked")==true) {
	    	workHouse = 1;
	    }
	    if($("#house1").prop("checked")==true) {
	    	house = 0;
	    }
	    if($("#house2").prop("checked")==true) {
			house = 1;
	    }
	    //显示位置  phoneSite=0, emailSite=0, workSite=0, houseSite=0; //状态 0:开始收集  1：选学院时收集
	    if($("#phone3").prop("checked")==true) {
	    	phoneSite = 1;
	    }
	    if($("#email3").prop("checked")==true) {
	    	emailSite = 1;
	    }
	    if($("#workHouse3").prop("checked")==true) {
	    	workSite = 1;
	    }
	    if($("#house3").prop("checked")==true) {
	    	houseSite = 1;
	    }
	    var oscarStartDate = $("#oscarStartDate").val();
		var oscarEndDate = $("#oscarEndDate").val();
	    if (0 == oscarType) {
	    	if (isEmpty(oscarStartDate) || isEmpty(oscarEndDate)) {
				alert('项目开始或结束时间不能为空！');
				return;
			}
	    }
	    //phoneSite=0, emailSite=0, workSite=0, houseSite=0; //状态 0:开始收集  1：选学院时收集
		var postData = {"channelName":name, "channelCode":code, "freeType":freeType, "freeDes":freeDes, 
				"freeStartDate":freeStartDate, "freeEndDate":freeEndDate, "freeCount":freeCount,
				"oscarType":oscarType, "phone":phone, "email":email, "workHouse":workHouse, "house":house,
				"oscarEndDate":oscarEndDate, "oscarStartDate":oscarStartDate,
				"phoneSite":phoneSite,"emailSite":emailSite,"workSite":workSite,"houseSite":houseSite, "buyIntroduce":buyIntroduce
				};
        $.ajax({
			type: "POST",
			url: "${ctx}/admin/channel/add",
			data: postData,
			async:false,
			success : function(msg) {
				eval("var json=" + msg);
				if (json.status == 'success') {
					alert('添加成功！');
					window.location.replace("channelQuery.jsp");
				} else {
					alert('添加失败，请重试！');
				}
			}
		});
	}
	
	/**
	 * 选中免费类型  处理事件
	 */
	function choseFreeType() {
		var freeType = $("#freeType").val();
		if (-1 == freeType) {
			$("#freeDesDiv").hide();				 //免费描述
			$("#buyIntroduceDiv").hide();
			$("#freeStartDateDiv").hide();     //免费开始时间
			$("#freeEndDateDiv").hide();      //免费结束时间
			$("#freeCountDiv").hide();    //免费份数
			return;
		}
		var freeCountStr = "<option value='1'>免费1份68元</option><option value='3'>免费3份168</option><option value='5'>免费5份268元</option>";
		if (0 == freeType) { //限时免费
			$("#freeDesDiv").hide();  //免费描述
			$("#buyIntroduceDiv").hide();
		} else if (1 == freeType) {   //渠道免费
			$("#freeDesDiv").show();
			$("#buyIntroduceDiv").show();
		} else if (2 == freeType) {    //优惠卷赠送
			$("#freeDesDiv").show();
			$("#buyIntroduceDiv").show();
			freeCountStr = "<option value='1'>赠送1张</option><option value='2'>赠送2张</option><option value='3'>赠送3张</option><option value='4'>赠送4张</option><option value='5'>赠送5张</option>";
		}
		$("#freeCount").html(freeCountStr);
		
		$("#freeStartDateDiv").show();
		$("#freeEndDateDiv").show();
		$("#freeCountDiv").show();    //免费份数
	}
	
	/**
	 * 奥斯卡 对接选择  处理事件
	 */ 
	function oscarChose() {
		var oscarType = $('#oscarType').val();
		if (1 == oscarType){
			$("#oscar").hide();
			$('input:checkbox').each(function () {
		        $(this).attr('checked',false);
			});
			return;
		}
		if (0 == oscarType) {
			$("#oscar").show();
		}  
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