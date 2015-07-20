<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'query.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jquery-easyui-1.4.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jquery-easyui-1.4.3/themes/color.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jquery-easyui-1.4.3/themes/icon.css">

<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>

</head>
  
  <body >
  <div  class="easyui-layout" data-options="fit:true">
  	 <div data-options="region:'north',title:'SQL查询',split:true" style="height:100px;">
  	 	<input id="querySQL" class="easyui-textbox"  data-options="multiline:true" style="height: 100%;width: 100%;">
  	 </div>
  	  <div data-options="region:'center',title:'查询结果'" style="padding:5px;background:#eee;">
  	  <table id="queryData"></table>
  	  </div>
  </div>   
    
  </body>
</html>
