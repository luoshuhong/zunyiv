<%-- 
Title: userQuery.jsp
Description: 用户查询
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
	<div class="row">
		<div class="col-xs-3">
			<h2>用户查询</h2>
		</div>
		<%--<div class="col-xs-9" style="margin-top: 20px; text-align: right;">--%>
			<%--<form class="form-inline" action="">--%>
				<%--<label for="startDate">日期：</label>--%>
				<%--<input id="startDate"  type="text" class="form-control" onClick="WdatePicker()" >--%>
				<%--<label for="endDate">~</label>--%>
				<%--<input type="text" id="endDate" class="form-control" onClick="WdatePicker()">--%>
				<%--OR--%>
				<%--<input type="text"  class="form-control" id="value" onblur="loadData();" name="value" placeholder="请输入关键字">--%>
				<%--<button type="button" id="queryBtn" onclick="loadData();" class="btn btn-primary">模糊查找</button>--%>
			<%--</form>--%>
		<%--</div>--%>
	</div>

	<div class="table-responsive">
		<table class="table table-bordered table-hover">
			<thead class="thead" >
			<tr>
				<th bgcolor="#87CEEB">编号</th>
				<th bgcolor="#87CEEB">姓名</th>
				<th bgcolor="#87CEEB">电话</th>
				<th bgcolor="#87CEEB">职业</th>
				<%--<th bgcolor="#87CEEB">生日</th>--%>
				<th bgcolor="#87CEEB">角色</th>
				<th bgcolor="#87CEEB">微博小尾巴</th>
				<th bgcolor="#87CEEB">操作</th>
			</tr>
			</thead>
			<!-- 数据域  -->
			<tbody id="dataListTbody"> </tbody>
		</table>
	</div>
	<div style="margin-top: 50;"></div>

	</body>

	<script type="text/javascript">
		$(function() {
			initQueryDate();  //默认初始化查询时间范围
			loadData();
		});

		//加载数据
		function loadData() {
			var postData = {};
			$.ajax({
				type: "POST",
				url: "${ctx}/admin/userQuery",
				data: postData,
				async:false,
				success : function(msg) {
					var json = eval("("+msg+")");;
					if (json.status == 'success') {
						$('#dataListTbody').html('');
						var dataList = json.data;
						$.each(JSON.parse(json.data), function (idx,item) {
							var role = "管理员";
							var professional = "学生";
							if (2 == item.role) {
								role = "超级管理员";
							}
							if (1 == item.professional) {
								professional = "上班族";
							}
							$('#dataListTbody').append('<tr>'
								+ '<td>' + (idx + 1) + '</td>'
								+ '<td>' + item.realName + '</td>'
								+ '<td>' + item.phone + '</td>'
								+ '<td>' + professional + '</td>'
								+ '<td>' + role +  '</td>'
								+ '<td>' + item.weiboTail +  '</td>'
								+ '<td><a href=\'javascript:update(\"' + item.id +'\")\'>更新</a> ' + '</td>'
//									+ '<td><a href=\'javascript:update(\"' + item.code + '\",\"' + item.id +'\")\'>二维码</a> | ' + '</td>'
								+ '</tr>'
							);
						});
					} else {
						alert('错误，请重试！');
					}
				},
				error : function(msg) {
					alert(msg);
				}
			});
		}

		/**
		 * 更新
		 */
		function update(id) {
			alert('功能正在完善中，敬请期待！');
		}

		//默认初始化查询时间范围
		function initQueryDate() {
			var now = new Date();
			var starDate = new Date(now.getTime() - 86400000 * 7);
			var endDate = new Date(now.getTime() + 86400000);
			var startDay = starDate.getDate();
			var endDay = endDate.getDate();
			if (startDay <= 9) {
				startDay = '0' + startDay;
			}
			if (endDay <= 9) {
				endDay = '0' + endDay;
			}

			var startMonth = starDate.getMonth() + 1;
			var endMonth = endDate.getMonth() + 1;
			if (startMonth <= 9) {
				startMonth = '0' + startMonth;
			}
			if (endMonth <= 9) {
				endMonth = '0' + endMonth;
			}
			var start = starDate.getFullYear() + "-" + startMonth + "-" + startDay;
			var end = endDate.getFullYear() + "-" + endMonth + "-" + endDay;

			$("#startDate").val(start);
			$("#endDate").val(end);
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