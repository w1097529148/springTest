<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2020/3/20
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
index
<%
    Object message = request.getAttribute("message");
    System.out.println("message = " + message);
%>
<%=message%>
</body>
</html>
