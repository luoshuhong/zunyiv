<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>遵义同城会-微博授权成功</title>
</head>

<body>
<c:if test="${success}">
    <h1>微博授权成功！</h1>
</c:if>
<c:if test="${!success}">
    <h1>微博授权失败！</h1>
</c:if>
</body>
</html>