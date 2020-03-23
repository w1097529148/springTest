<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>Hello World!</h2>
<form action="${request.getContextPath()}/LoginServlet" method="post" enctype="application/x-www-form-urlencoded">
        id:<input type="text" name="id" value="1">
        name:<input type="text" name="username" value="1"/>
        pwd:<input type="text" name="password" value="1"/>
        <input type="submit" value="提交"/>
</form>
<%--<br/>--%>
<%--<form action="/FileController" method="post" enctype="multipart/form-data">--%>
<%--        <input type="text" name="userId">--%>
<%--        <input type="file"/>--%>
<%--        <input type="file"/>--%>
<%--        <input type="file"/>--%>
<%--        <input type="submit" value="提交"/>--%>
<%--</form>--%>

<h3>自定义标签</h3>
<a href="/SecurityServlet">分页</a>
</body>

</html>
