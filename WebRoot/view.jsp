<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'view.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    数据库信息： <br>
 <hr>
    数据库产品名称:${DBinfo["DatabaseProductName"]} <br>
    驱动程序:${DBinfo["DriverName"]} <br>
    驱动程序版本:${DBinfo["DriverVersion"]} <br>
 <hr>
    所有表： <br>
    <%
    List data=(List)request.getAttribute("TableList");
    for(Object s:data){
    	out.println(s+"<br>");
    }
    %>
    
  </body>
</html>
