<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib prefix="el" uri="http://my.el.function" %>
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
<button value="修改权限" onclick="update(${el:FunctionsEl('1','3','5','9')})"> 修改权限</button>
<%--<script type="text/javascript">--%>
<%--        function update() {--%>
<%--                var id=1;--%>
<%--                var name="1";--%>
<%--                var uri="2";--%>
<%--                var desc="update";--%>
<%--                var s = "${el:FunctionsEl(1,'3','5','8')}";--%>
<%--                alert(s)--%>
<%--        }--%>
<%--</script>--%>
${el:FunctionsEl('1','3',5,9)}
</body>

</html>
