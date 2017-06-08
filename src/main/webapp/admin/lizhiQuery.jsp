<%-- 
Title: lizhiQuery.jsp
Description: 荔枝资源查询
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
		<script type="text/javascript" src="${ctx}/js/DatePicker/WdatePicker.js"></script>
	</head>

	<body style="width: 80%;">
	<div class="row" style="margin-left: 20px; text-align: left;">
		<form class="form-inline" action="">
			<h3>荔枝资源查询</h3>
		</form>
		<form class="form-inline" action="">
			<label for="isPush">是否推送</label>
			<select class="form-control" style="width: 200px;" id="isPush" >
				<option value="-1">全部</option>
				<option value="0">未推送</option>
				<option value="1">已推送</option>
			</select>
			<%--<input type="number" id="isPush" style="width: 110px;" value="0" class="form-control" name="isPush">--%>
			<button type="button" id="queryBtn" onclick="loadData();" class="btn btn-primary">查询</button>
		</form>
	</div>
	<div class="row ">

	</div>

	<div class="table-responsive">
		<table class="table table-bordered table-hover">
			<thead class="thead" >
			<tr>
				<th bgcolor="#87CEEB">编号</th>
				<th bgcolor="#87CEEB">日期</th>
				<th bgcolor="#87CEEB">作者</th>
				<th bgcolor="#87CEEB">标题</th>
				<th bgcolor="#87CEEB">查看详情</th>
				<th bgcolor="#87CEEB">资源下载</th>
				<%--<th bgcolor="#87CEEB" style="width: 10%;">来源</th>--%>
				<th bgcolor="#87CEEB">是否推送</th>
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
		loadData();
	});

	//加载数据
	function loadData() {
		var isPush = $("#isPush").val();

		var postData = {"isPush": isPush };

		$.ajax({
			type: "POST",
			url: "${ctx}/admin/lizhi/query",
			data: postData,
			async:false,
			success : function(msg) {
				var json = eval("("+msg+")");;
				if (json.status == 'success') {
					$('#dataListTbody').html('');
					var dataList = json.data;
					var index = 1;
					$.each(JSON.parse(json.data), function (idx,item) {
						var pushStr = "未推送";
						var pushHtml = "<a href='javascript:push(" + item.id + ")'>推送</a>";
						if (1 == item.isPush) {
							pushStr = "已推送";
							pushHtml = "";
						}
						$('#dataListTbody').append('<tr>'
								+ '<td>' + index++ + '</td>'
								+ '<td>' + item.releaseTimeStr + '</td>'
								+ '<td>' + item.author + '</td>'
								+ '<td>' + item.title + '</td>'
								+ '<td><a target="_blank" href="' + item.webUrl + '"> 查看</a></td>'
								+ '<td><a target="_blank" href="' + item.dataUrl + '"> 右键链接另存为</a></td>'
								+ '<td>' + pushStr +  '</td>'
								+ '<td>' + pushHtml +  '</td>'
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
	
	function push (id) {
		if(!confirm("确定已经在公众号推送了么！")){
			return;
		}

		var postData = {"id": id };
		$.ajax({
			type: "POST",
			url: "${ctx}/admin/lizhi/push",
			data: postData,
			async:false,
			success : function(msg) {
				var json = eval("("+msg+")");;
				if (json.status == 'success') {
					loadData();
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