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
							<a class="btn btn-primary btn-large" href="javascript:setWeiboTail();">设置微博小尾巴</a>
							<a class="btn btn-primary btn-large" href="javascript:setSelfInfo();">补全信息</a>
							<a class="btn btn-primary btn-large" target="_self" href="../game/gobang.html">来盘五子棋</a>
							<c:if test="${sessionScope.get('role') == 2}">
								<a class="btn btn-primary btn-large" target="_self" href="https://api.weibo.com/oauth2/authorize?client_id=1403067685&redirect_uri=http://www.zunyi.me/weibo/redirect&response_type=code">微博授权</a>
							</c:if>
						</p>
					</div>
				</div>
			</div>
		</div>

		<!-- 设置微博小尾巴 -->
		<div class="modal" id="setWeiboTail" style="margin: auto">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h3 >设置微博小尾巴</h3>
					</div>
					<div class="modal-body"  style="width: 560px; height: 320px;">
						<form class="form-horizontal" >
							<div class="form-group ">
								<%--<label for="subCode" class="col-sm-3 control-label">渠道编码：</label>--%>
								<%--<input  id="subCode" type="text" readonly="readonly" class="form-control" style="width: 200px;" placeholder="渠道编码">--%>
								<label for="weiboTailSelect" class="col-sm-3 control-label">选择：</label>
								<select class="form-control" style="width: 150px;" id="weiboTailSelect" >
									<option value="1">同城君007</option>
									<option value="3">同城君007</option>
									<option value="5">同城君008</option>
								</select>
							</div>
							<div class="form-group">
								<label for="weiboTail" class="col-sm-3 control-label">手动填写：</label>
								<input  id="weiboTail" type="text" class="form-control" style="width: 200px;" placeholder="多个','逗号分隔">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<input class="btn btn-primary" type="button"  onclick="saveWeiboTail();" value="确认">
						<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->

	</body>

<script type="text/javascript">

	// 设置微博小尾巴
	function setWeiboTail() {
		$('#setWeiboTail').modal('show');
	}
	function saveWeiboTail() {
		$('#setWeiboTail').modal('hide');
		alert('完善中，敬请期待！');
	}

	function setSelfInfo() {
		alert('完善中，敬请期待！');
	}

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