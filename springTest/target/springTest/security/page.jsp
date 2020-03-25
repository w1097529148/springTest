<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.xxx.tld" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><title></title></head>
index.jsp
<% Object page1 = request.getAttribute("message");
    %>
<%=page1%>
<body>
<h2>小说目录</h2>
<br>

<table border="1" width="100%">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>uri</td>
        <td>描述</td>
    </tr>
    <c:forEach items="${list}" var="b">
        <tr>
            <td>${b.id}</td>
            <td>${b.name }</td>
            <td>${b.uri }</td>
            <td>${b.description}</td>
        </tr>
    </c:forEach>
</table>
<h3>自定义分页标签</h3>
<my:page pageBean="${pagebean}" ></my:page>
</body>

</body>
</html>