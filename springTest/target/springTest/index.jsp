<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>Hello World!</h2>
<form action="Servlet" method="post" enctype="application/x-www-form-urlencoded">
        <input type="text" name="username" value="username"/>
        <input type="text" name="password" value="password"/>
        <input type="submit" value="提交"/>
</form>
<form enctype="multipart/form-data" method="get" action="Servlet">
        <input type="text" name="name"/>
        <input type="submit" value="提交"/>
</form> 
<a href="${request.getContextPath()}/PageServlet?method=">head</a>
<a href="${request.getContextPath()}/PageServlet?method=left">left</a>
        <a href="Servlet?method=querySecurityAll">查询全部权限</a>
        <a href="${request.getContextPath()}/Servlet?method=findSecurityById">根据权限id查询权限</a>
        <a href="${request.getContextPath()}/PageServlet?method=insertSecurity">新增权限</a>
        <a href="${request.getContextPath()}/PageServlet?method=updateSecurity">修改权限</a>
        <a href="${request.getContextPath()}/PageServlet?method=deleteSecurity">删除权限</a>
        <a href="${request.getContextPath()}/PageServlet?method=querySecurityPage">分页查询权限</a>
        <a href="${request.getContextPath()}/PageServlet?method=querySecurityTotal">查询权限总数</a>
<br/>
<form action="/FileController" method="post" enctype="multipart/form-data">
        <input type="text" name="userId">
        <input type="file"/>
        <input type="file"/>
        <input type="file"/>
        <input type="submit" value="提交"/>
</form>
</body>

</html>
