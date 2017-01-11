<%-- 
Title: weiboQuery.jsp
Description: 微博统计
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

	<body style="width: 50%;">
	<div class="row">
		<div class="col-xs-3">
			<h2>微博统计</h2>
		</div>
		<div class="col-xs-9" style="margin-top: 20px; text-align: right;">
			<form class="form-inline" action="">
				<label for="startDate">日期：</label>
				<input id="startDate"  type="text" class="form-control" onClick="WdatePicker()" >
				<label for="endDate">~</label>
				<input type="text" id="endDate" class="form-control" onClick="WdatePicker()">
				<button type="button" id="queryBtn" onclick="loadData();" class="btn btn-primary">查找</button>
			</form>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table table-bordered table-hover">
			<thead class="thead" >
			<tr>
				<th bgcolor="#87CEEB">编号</th>
				<th bgcolor="#87CEEB">小尾巴</th>
				<th bgcolor="#87CEEB">发博数量</th>
				<%--<th bgcolor="#87CEEB">person</th>--%>
			</tr>
			</thead>
			<!-- 数据域  -->
			<tbody id="dataListTbody"> </tbody>
		</table>
	</div>
	<div style="margin-top: 50;"></div>

	</body>

	<script type="text/javascript" src="${ctx}/js/channelStat.js"></script>
<script type="text/javascript">
	$(function() {
		initQueryDate();  //默认初始化查询时间范围
		loadData();
	});

	//加载数据
	function loadData() {
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var postData = {"startDate":startDate, "endDate":endDate};
		$.ajax({
			type: "POST",
			url: "${ctx}/admin/weibostat/stat",
			data: postData,
			async:false,
			success : function(msg) {
				var json = eval("("+msg+")");;
				if (json.status == 'success') {
					$('#dataListTbody').html('');
					var dataList = json.data;
					var index = 1;
					$.each(JSON.parse(json.data), function (idx,item) {
						$('#dataListTbody').append('<tr>'
								+ '<td>' + index + '</td>'
								+ '<td>' + item.source + '</td>'
								+ '<td>' + item.weiboCount + '</td>'
								+ '</tr>'
						);
						index = index + 1;
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

	//默认初始化查询时间范围
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