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
		<div class="container"  style="padding-top: 70px;">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="jumbotron well">
						<h2>
							欢迎你，${sessionScope.get('username')}
						</h2>
						<p>
							您可以使用以下功能:<br/>
						</p>
						<p>
							<%--<a class="btn btn-primary btn-large" href="${ctx}/cuf/record.do">设置微博小尾巴</a>--%>
							<a class="btn btn-primary btn-large" href="#">设置微博小尾巴</a>
							<a class="btn btn-primary btn-large" href="#">补全信息</a>
							<a class="btn btn-primary btn-large" target="_self" href="../game/gobang.html">来盘五子棋</a>
							<c:if test="${sessionScope.get('role') == 2}">
							<a class="btn btn-primary btn-large" target="_self" href="https://api.weibo.com/oauth2/authorize?client_id=1403067685&redirect_uri=http://www.zunyi.me/weibo/redirect&response_type=code">微博授权</a>
							</c:if>
						</p>
					</div>
				</div>
			</div>
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
		/******** 参数校验 end ******************/
		
		//oscar 项目对接信息
		var oscarType = $('#oscarType').val();
		var phone=1, email=-1, workHouse=-1, house=-1; //状态 -1：不需要 0：需要  1：必填
		var phoneSite=0, emailSite=0, workSite=0, houseSite=0; //状态 0:开始收集  1：选学院时收集
	    var oscarStartDate = $("#oscarStartDate").val();
		var oscarEndDate = $("#oscarEndDate").val();
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