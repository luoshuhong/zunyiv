<%-- 
Title: userQuery.jsp
Description: 用户个人中心
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
	 	<h3>用户个人中心</h3>
		<div class="form-group">
			<label class="col-sm-2 control-label"><a target="_self" href="https://api.weibo.com/oauth2/authorize?client_id=2722456167&redirect_uri=http://www.zunyi.me/weibo/redirect&response_type=code">微博授权</a></label>
		</div>
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