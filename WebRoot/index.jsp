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

<title>基于web的数据库客户端</title>
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
	        	data: tree,
	        	onClick: function(node){
	        	$('#tableColumns').datagrid({
	        				fit:true,
	        				striped:true,
					        url:'<%=path %>/DbManger',
					        queryParams:{action:'tableInfo',TName:node.text},
					        columns:[[
					            {field:'tableName_',title:'所属表名',width:200},
					            {field:'columnName',title:'列名',width:200},
					            {field:'dataTypeName',title:'数据类型',width:200},
					            {field:'columnSize',title:'长度',width:200},
					            {field:'decimalDigits',title:'小数位数',width:200},
					            {field:'remarks',title:'列描述',width:200}
					            
					        ]],
					        onLoadSuccess:function(data){
					        // console.log(JSON.stringify(data))
						        $("#aa").accordion('select',"数据列表");
						        var columns=new Array();
						        $.each(data.rows,function(i,o){
						        	var column=new Object();
						        	column.field=o.columnName;
						        	column.title=o.columnName;	
						        	column.width=200;	
						        	columns.push(column);				        	
						        })
						        
						     //   console.log(JSON.stringify(columns))
						        
						        $("#tableData").datagrid({
							        fit:true,
							        striped:true,
			        				pageSize:20,
							        url:'<%=path %>/DbManger',
							        queryParams:{action:'tableData',TName:node.text},
							        columns:[columns],
							        rownumbers:true,
							        pagination:true
						        })
					        }
					    });
				}
	        });
	        
	    }
	});
	}
	
	function disConn(){
		$.post("<%=path %>/DbManger",{action:"disConn"},function(data){
			$.messager.show({
				title:'提醒',
				msg:data,
				timeout:5000,
				showType:'slide'
			});
		})
	}
	var i=1;
	function addTab(){
		$('#tabs').tabs('add',{
			id:Math.random(),
		    title:'新建查询'+i++,
		    href:'<%=path%>/query.jsp',
		    closable:true
		});
	}
	
	function querySQL(){
		var sql=$("#querySQL").textbox("getValue");
		$.post("<%=path%>/DbManger",{action:"query",SQL:sql,page:1,rows:10},function(data){
			var OBJ=JSON.parse(data);
			$("#queryData").datagrid({
				fit:true,
				striped:true,
		        url:'<%=path %>/DbManger',
		        queryParams:{action:"query",SQL:sql,f:"datagrid"},
		        columns:[OBJ.columns],
		        //data:OBJ.data,
		        rownumbers:true,
		        pagination:true
			});
		})
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
		<div id="aa" class="easyui-accordion" data-options="fit:true">
			<div title="查询" data-options="iconCls:'icon-ok'"
				style="overflow:auto;">
				<div id="tab-tools">
					 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="querySQL()" style="width:80px">执行SQL</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="false" iconCls="icon-add" onclick="addTab()">新建查询</a>
				</div>			
				<div id="tabs" class="easyui-tabs" data-options="fit:true,tools:'#tab-tools'">
					<div title="欢迎" style="padding:10px">
						<h3 style="color:#0099FF;">一起组队打怪兽！</h3>
						<p>github项目地址：<a title="前方有大Boss！！" href="https://github.com/ArvinLovegood/myUtils">https://github.com/ArvinLovegood/myUtils</a></p>
						<h3 style="color:#0099FF;">项目简介:</h3>
						<p>本项目的目标：提供一个企业项目后期维护的工具集，主要包括远程文件更新，远程数据库操作（暂时就这些吧）</p>
						<h3 style="color:#0099FF;">1.远程数据库操作</h3>
						<p>目前正在开发远程数据库操作工具集，系统大致雏形已设计完毕，加紧开发各个模块中ing...</p>
						<h3 style="color:#0099FF;">2.远程文件更新</h3>
						<p>设计ing...</p>
					</div>
		 		</div>
			</div>
			<div title="数据列表" data-options="" style="padding:10px">
			<table id="tableData" ></table>
			</div>
			<div id="tableINFO" title="列基本信息" data-options="iconCls:'icon-help'"
				style="padding:10px;">
				<table id="tableColumns"></table>
			</div>
		</div>
	</div>
</body>
</html>
