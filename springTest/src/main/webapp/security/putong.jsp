<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2020/3/21
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
putong权限
<a href="PageServlet?method=querySecurityAll">查询全部权限</a>
<a href="${request.getContextPath()}/PageServlet?method=findSecurityById">根据权限id查询权限</a>
<a href="${request.getContextPath()}/SecurityServlet">分页查询权限</a>
</body>
</html>
