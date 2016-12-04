<%-- 
Title: admin-left-menu.jsp
Description: 后台左侧菜单页
Company:
@author: luoshuhong
date 2016
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		#main-nav {
            margin-left: 1px;
        }
        #main-nav.nav-tabs.nav-stacked > li > a {
            padding: 10px 8px;
            font-size: 12px;
            font-weight: 600;
            color: #4A515B;
            background: #E9E9E9;
            background: -moz-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FAFAFA), color-stop(100%,#E9E9E9));
            background: -webkit-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
            background: -o-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
            background: -ms-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
            background: linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9');
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9')";
            border: 1px solid #D5D5D5;
            border-radius: 4px;
        }
        #main-nav.nav-tabs.nav-stacked > li > a > span {
            color: #4A515B;
        }
        #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover {
            color: #FFF;
            background: #3C4049;
            background: -moz-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#4A515B), color-stop(100%,#3C4049));
            background: -webkit-linear-gradient(top, #4A515B 0%,#3C4049 100%);
            background: -o-linear-gradient(top, #4A515B 0%,#3C4049 100%);
            background: -ms-linear-gradient(top, #4A515B 0%,#3C4049 100%);
            background: linear-gradient(top, #4A515B 0%,#3C4049 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049');
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049')";
            border-color: #2B2E33;
        }
        #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover > span {
            color: #FFF;
        }
        #main-nav.nav-tabs.nav-stacked > li {
            margin-bottom: 4px;
        }
        /*定义二级菜单样式*/
        .secondmenu a {
            font-size: 10px;
            color: #4A515B;
            text-align: center;
        }
        .navbar-static-top {
            background-color: #212121;
            margin-bottom: 5px;
        }
        .navbar-brand {
            background: url('') no-repeat 10px 8px;
            display: inline-block;
            vertical-align: middle;
            padding-left: 50px;
            color: #fff;
        }
        .secondmenu a {
		    font-size: 12px;
		    color: #4A515B;
		    text-align: center;
		}
		.secondmenu li.active {
		    background-color: #eee;
		    border-color: #428bca;
		}
		/*控制菜单箭头*/
		.nav-header.collapsed > span.glyphicon-chevron-toggle:before {
		    content: "\e114";
		}
		.nav-header > span.glyphicon-chevron-toggle:before {
		    content: "\e113";
		}
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
					<li class="active">
						<a href="#" >  <i class="glyphicon glyphicon-th-large"></i> 首页</a>
					</li>
					<li>
						<a href="#channelManage" class="nav-header collapsed" data-toggle="collapse"> 
							<i class="glyphicon glyphicon-credit-card" ></i> 用户管理 <span
							class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="channelManage" class="nav nav-list collapse secondmenu" style="height: 0px; ">
							<li><a href="#" onclick="fillDataArea('userAdd');"><i class="glyphicon glyphicon-plus"></i>用户添加</a></li>
							<li><a href="#" onclick="fillDataArea('userQuery');"><i class="glyphicon glyphicon-search"></i>用户查询</a></li>
						</ul>
					</li>
					<!--
					<li>
						<a href="#virualManage" class="nav-header collapsed" data-toggle="collapse"> 
							<i class="glyphicon glyphicon-credit-card" ></i> 运营统计 <span class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="virualManage" class="nav nav-list collapse secondmenu" style="height: 0px; ">
							<li><a href="#" onclick="fillDataArea('viralGroupByDate');"><i class="glyphicon glyphicon-th-list"></i>viral统计-时间维度</a></li>
							<li><a href="#" onclick="fillDataArea('viralGroupByChannel');"><i class="glyphicon glyphicon-th-list"></i>viral统计-渠道维度</a></li>
							<li><a href="#" onclick="fillDataArea('statLoveMessage');"><i class="glyphicon glyphicon-th-list"></i>爱情数据统计</a></li>
							<li><a href="#" onclick="fillDataArea('myPage');"><i class="glyphicon glyphicon-th-list"></i>MyPage统计</a></li>
							<li><a href="#" onclick="fillDataArea('statMajor');"><i class="glyphicon glyphicon-th-list"></i>总表统计</a></li>
							<li><a href="#" onclick="fillDataArea('userActive');"><i class="glyphicon glyphicon-th-list"></i>活跃用户访问统计(UV/PV)</a></li>
							<li><a href="#" onclick="fillDataArea('userActiveSh');"><i class="glyphicon glyphicon-th-list"></i>活跃用户分析</a></li>
							<li><a href="#" onclick="fillDataArea('userActiveShCount');"><i class="glyphicon glyphicon-th-list"></i>用户活跃天数统计</a></li>
							<li><a href="#" onclick="fillDataArea('statProNewProcess');"><i class="glyphicon glyphicon-th-list"></i>新版职业测评统计</a></li>
							<li><a href="#" onclick="fillDataArea('userNewActiveSh');"><i class="glyphicon glyphicon-th-list"></i>新版活跃用户分析</a></li>
						</ul>
					</li>
					<li>
						<a href="#ugcManage" class="nav-header collapsed" data-toggle="collapse"> 
							<i class="glyphicon glyphicon-credit-card" ></i> UGC管理 <span class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="ugcManage" class="nav nav-list collapse secondmenu" style="height: 0px; ">
							<li><a href="#" onclick="fillDataArea('ugcHotChoose');"><i class="glyphicon glyphicon-th-list"></i>热门筛选</a></li>
						</ul>
					</li>
					<li>
						<a href="#cufManage" class="nav-header collapsed" data-toggle="collapse"> 
							<i class="glyphicon glyphicon-credit-card" ></i> CUF数据统计 <span class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="cufManage" class="nav nav-list collapse secondmenu" style="height: 0px; ">
							<li><a href="#" onclick="fillDataArea('cufSummary');"><i class="glyphicon glyphicon-th-list"></i>整体数据统计</a></li>
							<li><a href="#" onclick="fillDataArea('cufActive');"><i class="glyphicon glyphicon-th-list"></i>活跃数据统计</a></li>
							<li><a href="#" onclick="fillDataArea('cufQuestion');"><i class="glyphicon glyphicon-th-list"></i>问卷统计</a></li>
							<li><a href="#" onclick="fillDataArea('cufRegion');"><i class="glyphicon glyphicon-th-list"></i>报名地区统计</a></li>
							<li><a href="#" onclick="fillDataArea('cufRole');"><i class="glyphicon glyphicon-th-list"></i>角色统计</a></li>
							<li><a href="#" onclick="fillDataArea('cufTemplateMessage');"><i class="glyphicon glyphicon-th-list"></i>发送消息模板统计</a></li>
						</ul> 
					</li>

					<li>
						<a href="#pushRecordManage" class="nav-header collapsed" data-toggle="collapse">
							<i class="glyphicon glyphicon-credit-card" ></i> 幸福职业推送统计 <span class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="pushRecordManage" class="nav nav-list collapse secondmenu" style="height: 0px; ">
							<li><a href="#" onclick="fillDataArea('pushRecordAdd');"><i class="glyphicon glyphicon-th-list"></i>添加推送</a></li>
							<li><a href="#" onclick="fillDataArea('pushRecordQuery');"><i class="glyphicon glyphicon-th-list"></i>记录查询</a></li>
						</ul>
					</li>
					-->

					
				</ul>
			</div>
 			<div id="data"  class="col-md-10" > <h3>这里还没有想好放神马...  Orz </h3></div>
			<!--<div id="data"  class="col-md-10" > <iframe src='../game/gobang.html' id='iframepage1' frameBorder=0 scrolling='auto' width='100%' height='90%' ></iframe></div>-->
			
		</div>
	</div>
</body>
<script type="text/javascript">
	function fillDataArea(type){
		var iframe = "";
		if (type == 'userAdd') {
			iframe = "<iframe src='../admin/userAdd.jsp' id='iframepage1' frameBorder=0 scrolling='auto' width='100%' height='90%' ></iframe>";
		}  else {
			 iframe = "<iframe src='test.jsp' frameBorder=0  width='100%' height='90%' scrolling='auto' ></iframe>";
		}

		
		$("#data").html(iframe);
	}  
	
	function iFrameHeight(id) {
		var ifm = document.getElementById(id);
		var subWeb = document.frames ? document.frames[id].document
				: ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight;
		}
	}
	
// 	function iFrameHeight() {
// 		var ifm = document.getElementById("iframepage");
// 		var subWeb = document.frames ? document.frames["iframepage"].document
// 				: ifm.contentDocument;
// 		if (ifm != null && subWeb != null) {
// 			ifm.height = subWeb.body.scrollHeight;
// 		}
// 	}
</script>

</html>