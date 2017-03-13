<%--
Title: userAdd.jsp
Description: 用户添加
Company: zunyiv
@author: luoshuhong
date 2016
@version 1.0
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <script type="text/javascript" src="${ctx}/js/DatePicker/WdatePicker.js"></script>
    <title>遵义同城印象</title>
</head>

<body >
<form class="form-horizontal" style="margin-top: 20px;" action="javascript:create();" class="container" >
    <div class="form-group" style="text-align: center;">
        <label for="name" class=" control-label">姓名：</label>
        <input id="name" type="text" name="name"  style="width: 200px;" placeholder="最多三个字" maxlength="3" required="required" >
    </div>
    <div class="form-group" style="text-align: center;">
        <label for="street" class="control-label">在那点混：</label>
        <input id="street" type="text"  name="street"  style="width: 200px;" placeholder="大连路/火车站.." maxlength="8" required="required" >
    </div>
    <div class="form-group" style="text-align: center;">
        <div class="col-sm-offset-2 col-sm-10">
            <input class="btn btn-primary" type="submit"  value="生成">
        </div>
    </div>

    <div id="img_code_div" display="none" style="text-align: center;">
        <img id="img_code" style="width: 300px; height: 300px;" src="" alt="长按保存图片" title="鼠标右键保存图片">
        <br>
        <label style="font-size: 20px; align-items: center;">生成后长按保存图片</label>
    </div>

</form>
</body>

<script type="text/javascript">
    function create() {
        var name = $("#name").val();
        var street = $("#street").val();

        /******** 参数校验 end ******************/
        var postData = {"name":name, "street":street };
        $.ajax({
            type: "POST",
            url: "${ctx}/friend/create",
            data: postData,
            async:false,
            success : function(msg) {
                $("#img_code").attr('src', msg);
                $("#img_code_div").attr('display', '');
            }
        });
    }
</script>


</html>