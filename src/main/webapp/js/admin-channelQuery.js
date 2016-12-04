/**
 * Description: 后台渠道查询相关用
 * Company: NewLeader
 * author: luoshuhong
 * date 2015-9-23
 */
$(function() {
	loadData('');
});

/**
 * 查询数据
 */
function query(){
	var value = $("#value").val();
	if (isEmpty(value)) {
		return;
	}
	loadData(value);
}

//加载数据
function loadData(value) {
	var postData = {"value":value};
	$.ajax({
		type: "POST",
		url: "../admin/channel/query",
		data: postData,
		async:false,
		success : function(msg) {
			var json = eval("("+msg+")");;
			if (json.status == 'success') {
				$('#dataListTbody').html('');
				var dataList = json.data;
				 $.each(JSON.parse(json.data), function (idx,item) {
					 var freeTypeDes = "无免费";
					 if (0 == item.freeType) {
						 freeTypeDes = "限时免费";
					 } else if (1 == item.freeType) {
						 freeTypeDes = "渠道免费";
					 } else if (2 == item.freeType) {
						 freeTypeDes = "赠优惠卷";
					 }  
					 var oscarDes = "否";
					 if (0 == item.oscarType) {
						 oscarDes = "是";
					 }
					 
					 $('#dataListTbody').append('<tr>' 
						+ '<td>' + item.name + '</td>'
						+ '<td>' + item.code + '</td>'
						+ '<td>' + item.totalSubscribe + '</td>'
//						+ '<td>' + item.currSubscribe + '</td>'
//						+ '<td>' + item.unSubscribe + '</td>'
						+ '<td>' + item.unSubscribeRate + '%</td>'
//						+ '<td>' + item.shareCount + '</td>'
//						+ '<td>' + item.virualCount + '</td>'
						+ '<td>' + (item.virualCount + item.currSubscribe) +  '</td>'
						
						+ '<td>' + item.lshareCount + '</td>'
						+ '<td>' + item.lvirualCount + '</td>'
						+ '<td>' + item.lrate + '</td>'
						+ '<td>' + item.pshareCount + '</td>'
						+ '<td>' + item.pvirualCount + '</td>'
						+ '<td>' + item.prate + '</td>'
						
						+ '<td>' + freeTypeDes + '</td>'
						+ '<td>' + oscarDes + '</td>'
						+ '<td>' + item.createTimeStr.substr(0,10) + '</td>'
						+ '<td><a href=\'javascript:qrCodeCreate(\"' + item.code + '\",\"' + item.id +'\")\'>二维码</a> | ' 
						+ ' <a href=\'javascript:cancel(\"' + item.id + '\",\"' + (item.virualCount + item.currSubscribe) + '\")\'>删除</a>  | '
						+ ' <a href=\'javascript:update(\"' + item.id + '\",\"' + item.name  + '\",\"' + item.code + '\",\"' 
							+ item.freeType + '\",\"' + item.freeDes + '\",\"' + item.freeStartDate + '\",\"' 
							+ item.freeEndDate + '\",\"' + item.freeCount + '\",\"' + item.oscarType + '\",\"' + item.oscarPhoneStates
							+ '\",\"' + item.oscarEmailStates + '\",\"' + item.oscarWorkHouseStates + '\",\"' + item.oscarHouseStates 
							+ '\",\"' + item.oscarPhoneSite + '\",\"' + item.oscarEmailSite 
							+ '\",\"' + item.oscarWorkHouseSite + '\",\"' + item.oscarHouseSite 
							+ '\",\"' + item.oscarStartDate + '\",\"' + item.oscarEndDate+'\",\"' + item.buyIntroduce+ '\")\'>更新</a>|'
							+ '<a href=\'javascript:subscribeMsgModalShow(\"' + item.code + '\")\'>关注文案</a>'
						+ '</td></tr>' 
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

//二维码生成
function qrCodeCreate(code,id) {
	if (isEmpty(code) || isEmpty(id)) {
		return;
	}
	$("#img_code").attr('src',''); //清除上一次图片
	var postData = {"id":id, "channelCode":code};
    $.ajax({
		type: "POST",
		url: "../admin/qrCode/create",
		data: postData,
		async:false,
		success : function(msg) {
			eval("var json=" + msg);
			if (json.status == 'success') {
				if (isEmpty(json.data)) {
					alert('生成二维码失败，请重试 ！');
					return;
				}
				$("#img_code").attr('src', json.data); 
				$('#imgModal').modal('show');  
			} else {
				$("#errMsg").val('添加失败，请重试！');
			}
		}
	});
}

//逻辑删除
function cancel(id, count) {
	if (count > 5) {
		alert('当前渠道关注用户大于5，不能删除！');
		return;
	}
	if (isEmpty(id)) {
		return;
	}
	if(!confirm("是否确定删除！！")){
		return;
	}
	var postData = {"id":id};
    $.ajax({
		type: "POST",
		url: "../admin/channel/cancel",
		data: postData,
		async:false,
		success : function(msg) {
			eval("var json=" + msg);
			if (json.status == 'success') {
				$('#dataListTbody').html('');
				loadData();
			} else {
				alert('删除失败，请重试！');
			}
		}
	});
}

//private int oscarType= 1;    //1：不是  0：是
//private int oscarPhoneStates = 1;		   //手机 -1：不需要  0：需要  1：必填
//private int oscarEmailStates = -1;
//private int oscarWorkHouseStates = -1;    //居住地
//private int oscarHouseStates = -1;	      //户籍
//private String oscarStartDate;	//开始时间
//private String oscarEndDate;	    //结束时间
//更新name:渠道名 
//code:编码  freeType：类型   freeDes：前台文案描述   freeStartDate：开始时间   freeEndDate：结束时间 freeCount：免费份数
//oscarType, oscarPhoneStates, oscarEmailStates, oscarWorkHouseStates, oscarHouseStates,oscarStartDate,oscarEndDate
function update(id,name,code, freeType, freeDes, freeStartDate, freeEndDate, freeCount, 
		oscarType, oscarPhoneStates, oscarEmailStates, oscarWorkHouseStates, oscarHouseStates,
		oscarPhoneSite,oscarEmailSite,oscarWorkHouseSite,oscarHouseSite,oscarStartDate,oscarEndDate,buyIntroduce ) {
	if (isEmpty(id) || isEmpty(name) || isEmpty(code)) {
		return;
	}
	//初始化 所以为非选中
	$('input:checkbox').each(function () {
        $(this).attr('checked',false);
	});
	
	$('#uid').val(id);
	$('#uchannelName').val(name);
	$('#uchannelCode').val(code);
	$("#errMsg").html('');
	$('#updateModal').modal('show');  
	
	$('#freeType').val(freeType);
	$('#freeDes').val(freeDes);
	$("#buyIntroduce").val(buyIntroduce);
	$('#freeStartDate').val(freeStartDate);
	$('#freeEndDate').val(freeEndDate);
	
	//处理免费信息展示
	choseFreeType();
	$('#freeCount').val(freeCount);
	
	//奥斯卡信息处理
	$('#oscarType').val(oscarType);
	if (0 == oscarType) {
		$("#oscar").show();
		//oscarPhoneStates, oscarEmailStates, oscarWorkHouseStates, oscarHouseStates
		if (1 == oscarPhoneStates) { //必填
			$("#phone1").prop("checked", 'checked'); 
			$("#phone2").prop("checked", 'checked'); 
		} else if (0 == oscarPhoneStates) {	//只收集 非必填
			$("#phone1").prop("checked", 'checked'); 
		}
		if (1 == oscarEmailStates) { 
			$("#email1").prop("checked", 'checked'); 
			$("#email2").prop("checked", 'checked'); 
		} else if (0 == oscarEmailStates) {
			$("#email1").prop("checked", 'checked'); 
		}
		if (1 == oscarWorkHouseStates) { 
			$("#workHouse1").prop("checked", 'checked'); 
			$("#workHouse2").prop("checked", 'checked'); 
		} else if (0 == oscarWorkHouseStates) {
			$("#workHouse1").prop("checked", 'checked'); 
		}
		if (1 == oscarHouseStates) { 
			$("#house1").prop("checked", 'checked'); 
			$("#house2").prop("checked", 'checked'); 
		} else if (0 == oscarHouseStates) {
			$("#house1").prop("checked", 'checked'); 
		}
		
		//显示位置 oscarPhoneSite,oscarEmailSite,oscarWorkHouseSite,oscarHouseSite
		if (1 == oscarPhoneSite) { 
			$("#phone3").prop("checked", 'checked'); 
		}
		if (1 == oscarEmailSite) { 
			$("#email3").prop("checked", 'checked');
		}
		if (1 == oscarWorkHouseSite) { 
			$("#workHouse3").prop("checked", 'checked');
		}
		if (1 == oscarHouseSite) { 
			$("#house3").prop("checked", 'checked');
		}
	} else {
		$("#oscar").hide();
	}
	$('#oscarStartDate').val(oscarStartDate);
	$('#oscarEndDate').val(oscarEndDate);

//	//处理免费信息展示
//	choseFreeType();
}


//更新保存
function updateComfirm() {
	var id = $('#uid').val();
	var name = $('#uchannelName').val();
	var code = $('#uchannelCode').val();
	
	//下面是渠道免费信息
	var freeDes = $("#freeDes").val();
	var freeStartDate = $("#freeStartDate").val();
	var freeEndDate = $("#freeEndDate").val();
	var freeType = $("#freeType").val();
	var freeCount = $("#freeCount").val();
	
	/******** 参数校验 start ******************/
	if (isEmpty(name) || isEmpty(code)) {
		$("#errMsg").html('参数不能为空！');
		$("#errMsg").show();
		return;
	}
	if (0 == freeType) {	//限免(有时间限制)
		freeDes = "";
		if (isEmpty(freeStartDate) || isEmpty(freeEndDate)) {
			$("#errMsg").html('参数不能为空！');
			$("#errMsg").show();
			return;
		}
	}
	//渠道免费（有文案描述）
	if (1 == freeType ) {
		if (isEmpty(freeDes) || isEmpty(freeStartDate) || isEmpty(freeEndDate)) {
			$("#errMsg").html('参数不能为空！');
			$("#errMsg").show();
			return;
		}
	}
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
	var buyIntroduce = $("#buyIntroduce").val();
    if (0 == oscarType) {
    	if (isEmpty(oscarStartDate) || isEmpty(oscarEndDate)) {
			alert('项目开始或结束时间不能为空！');
			return;
		}
    }
	if (!isEmpty(buyIntroduce) && buyIntroduce.length > 17) {
		alert("购买提示长度不能大于17");
		return;
	}
    
	var postData = {"id":id,"channelName":name, "channelCode":code, "freeType":freeType, "freeDes":freeDes, "freeStartDate":freeStartDate, "freeEndDate":freeEndDate,"freeCount":freeCount,
			"oscarType":oscarType, "phone":phone, "email":email, "workHouse":workHouse, "house":house,
			"oscarEndDate":oscarEndDate, "oscarStartDate":oscarStartDate,
			"phoneSite":phoneSite,"emailSite":emailSite,"workSite":workSite,"houseSite":houseSite, "buyIntroduce":buyIntroduce};
    $.ajax({
		type: "POST",
		url: "../admin/channel/update",
		data: postData,
		async:false,
		success : function(msg) {
			eval("var json=" + msg);
			if (json.status == 'success') {
				$('#dataListTbody').html('');
				$('#updateModal').modal('hide'); 
				loadData('');
			} else {
				$("#errMsg").html('修改失败，请重试！');
				$("#errMsg").show();
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
		$("#freeDesDiv").hide();  	 //免费描述
		$("#buyIntroduceDiv").hide();
		$("#freeDateDiv").hide();	 //免费时间
		$("#freeCountDiv").hide();    //免费份数
		return;
	}
	
	var freeCountStr = "<option value='1'>免费1份68元</option><option value='3'>免费3份168</option><option value='5'>免费5份268元</option>";
	if (0 == freeType) { 		  //限时免费
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
	
	
	$("#freeDateDiv").show();
	$("#freeCountDiv").show();       //免费份数
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

/************* 渠道关注后推送文案 start ******************/
//弹框设置
function subscribeMsgModalShow(code) {
	$('#subCode').val(code);
	$('#subMsgSow').val(""); 
	
	var postData = {"code":code};
    $.ajax({
		type: "POST",
		url: "../admin/channelShowConf/query",
		data: postData,
		dataType: "json",
		async:false,
		success : function(json) {
			if (json.status == 'success') {
				eval("var data=" + json.data);
				$('#subMsgSow').val(data.subscribeMsg); 
				$('#SubscribeMsgModal').modal('show'); 
			} else {
				$('#SubscribeMsgModal').modal('show'); 
			}
		}
	});
}
//保存
function subscribeMsgSave() {
	var code = $('#subCode').val();
	var subscribeMsg = $('#subMsgSow').val();
	
	var postData = {"code":code, "subscribeMsg":subscribeMsg};
    $.ajax({
		type: "POST",
		url: "../admin/channelShowConf/add",
		data: postData,
		dataType: "json",
		async:false,
		success : function(json) {
			if (json.status == 'success') {
				$('#SubscribeMsgModal').modal('hide'); 
				alert('设置成功！');
			} else {
			}
		}
	});
}
/************* 渠道关注后推送文案 end ******************/


/**
 *校验是否为null
 */
function isEmpty(value) {
	if ('' == value || undefined == value || null == value) {
		return true;
	}
	return false;
}