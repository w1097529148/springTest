<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2020/3/20
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% Object message = request.getAttribute("message");%>
<%=message!=null?message:"message为空"%>
</body>
</html>
