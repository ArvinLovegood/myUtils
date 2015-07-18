<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
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
	
	<script type="text/javascript">
	function getConn(){
		
	$('#ff').form('submit', {
	    url:"<%=path %>/DbManger",
	    ajax:true,
	    queryParams:{action:'newDbConn'},
	    onSubmit: function(){
	        // do some check
	        // return false to prevent submit;
	    },
	    success:function(data){	    	
	        $.each(JSON.parse(data).baseinfo,function(v,k){
	        	$("#DBINFO").append(v+"："+k+"<br>");
	        })
	        
	        var treeData=new Array();
	         $.each(JSON.parse(data).tables,function(v,k){
	        	var obj=new Object();
	        	obj.id=v;
	        	obj.text=k;
	        	treeData.push(obj);
	        })	        
	        var root=new Object();
	         root.id="root";
	         root.text="连接-<%=session.getId()%>";
	         root.state="open";
	         root.children=treeData;	        
	         var tree=new Array();
	         tree.push(root)
	        
	        $('#tt').tree({
	        	animate:true,
	        	lines:true,
	        	data: tree
	        });
	        
	    }
	});
	}
	</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north'" style="height:100px;">
	<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'" style="width:50%;">
		<form id="ff" >
		  <div id="NEWDB" class="easyui-panel" data-options="fit:true,border:false" title="获取数据库连接" style="width:50%;height:100%;left: 0px">
		  <div>
		  <input class="easyui-textbox" data-options="prompt:'请输入数据库JDBC连接'" name="dbUrl" style="width:200px;height:32px">
		  <input class="easyui-textbox" data-options="prompt:'请输入数据库用户名'"  name="dbUser"   style="width:200px;height:32px">
		  <input class="easyui-textbox" data-options="prompt:'请输入数据库密码'"  name="dbPwd"    type="password" style="width:200px;height:32px"><br>
		  </div>
		  <div style="text-align: right;vertical-align: text-bottom;">
		  <a href="javascript:void(0)" class="easyui-linkbutton" onclick="getConn()">连接</a>
		  <a href="javascript:void(0)" class="easyui-linkbutton" onclick="disConn()">断开连接</a>
		  </div>		  
		  </div>
		</form>
	</div>
	<div data-options="region:'east'" style="width:50%;">	
		  <div id="DBINFO" class="easyui-panel" data-options="fit:true,border:false" title="当前数据库信息" style="width:50%;height:100%;padding:10px;right: 0px"></div>
	</div>	
	</div>
	</div>
	<div data-options="region:'west',split:true" title="导航"  style="width:200px;">
	<ul id="tt"></ul>
	</div>
	<div data-options="region:'center',iconCls:'icon-ok'">
		<div class="easyui-accordion" data-options="fit:true">
			<div title="About" data-options="iconCls:'icon-ok'"
				style="overflow:auto;padding:10px;">
				<h3 style="color:#0099FF;">Accordion for jQuery</h3>
				<p>Accordion is a part of easyui framework for jQuery. It lets
					you define your accordion component on web page more easily.</p>
			</div>
			<div title="Help" data-options="iconCls:'icon-help'"
				style="padding:10px;">
				<p>The accordion allows you to provide multiple panels and
					display one or more at a time. Each panel has built-in support for
					expanding and collapsing. Clicking on a panel header to expand or
					collapse that panel body. The panel content can be loaded via ajax
					by specifying a 'href' property. Users can define a panel to be
					selected. If it is not specified, then the first panel is taken by
					default.</p>
			</div>
			<div title="Ajax" data-options="href:'http://localhost:6666/sparkUtlis/DbManger?action=view'"
				style="padding:10px"></div>
		</div>
	</div>
</body>
</html>
