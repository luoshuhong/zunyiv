<%-- 
Title: weiboQuery.jsp
Description: 微博查询
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
	<div class="row" style="margin-left: 20px; text-align: left;">
		<form class="form-inline" action="">
			<h3>微博查询</h3>
		</form>
		<form class="form-inline" action="">
			<label for="startDate">日期：</label>
			<input id="startDate"  type="text" style="width: 110px;" class="form-control" onClick="WdatePicker()" >
			<label for="endDate">~</label>
			<input type="text" id="endDate" style="width: 110px;" class="form-control" onClick="WdatePicker()">
			<label for="tail">小尾巴:</label>
			<input type="text"  class="form-control" id="tail" style="width: 150px;"  placeholder="请输入微博小尾巴">
			<label for="tail">关键字:</label>
			<input type="text"  class="form-control" id="value"  name="value" placeholder="请输入关键字模糊查找">
		</form>
		<form class="form-inline" action="">
			<label for="reposts">转发>=</label>
			<input type="number" id="reposts" style="width: 110px;" value="0"   class="form-control" name="reposts">
			<label for="comments">评论>=</label>
			<input type="number" id="comments" style="width: 110px;" value="0"  class="form-control" name="comments">
			<label for="likes">点赞>=</label>
			<input type="number" id="likes" style="width: 110px;" value="0" class="form-control" name="likes">
			<button type="button" id="queryBtn" onclick="loadData();" class="btn btn-primary">查询</button>
		</form>
	</div>
	<div class="row ">

	</div>

	<div class="table-responsive">
		<table class="table table-bordered table-hover">
			<thead class="thead" >
			<tr>
				<th bgcolor="#87CEEB">日期</th>
				<th bgcolor="#87CEEB" style="width: 45%;">内容</th>
				<th bgcolor="#87CEEB">转发数</th>
				<th bgcolor="#87CEEB">评论数</th>
				<th bgcolor="#87CEEB">点赞数</th>
				<th bgcolor="#87CEEB" style="width: 10%;">来源</th>
				<th bgcolor="#87CEEB">发送人</th>
				<th bgcolor="#87CEEB">是否转发</th>
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
		var value = $("#value").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var reposts = $("#reposts").val();
		var comments = $("#comments").val();
		var likes = $("#likes").val();
		var tail = $("#tail").val();

		var postData = {"startDate": startDate, "endDate": endDate,
			"value": value, "reposts": reposts, "comments": comments, "likes": likes, "tail": tail };

		$.ajax({
			type: "POST",
			url: "${ctx}/admin/weibostat/query",
			data: postData,
			async:false,
			success : function(msg) {
				var json = eval("("+msg+")");;
				if (json.status == 'success') {
					$('#dataListTbody').html('');
					var dataList = json.data;
					$.each(JSON.parse(json.data), function (idx,item) {
						var retweetedStatus = "原创";
						if (1 == item.retweetedStatus) {
							retweetedStatus = "转发";
						}
						$('#dataListTbody').append('<tr>'
								+ '<td>' + item.createDateStr + '</td>'
								+ '<td>' + item.content + '</td>'
								+ '<td>' + item.repostsCount + '</td>'
								+ '<td>' + item.commentsCount + '</td>'
								+ '<td>' + item.likeCount + '</td>'
								+ '<td>' + item.source +  '</td>'
								+ '<td>' + item.userName +  '</td>'
								+ '<td>' + retweetedStatus + '</td>'
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