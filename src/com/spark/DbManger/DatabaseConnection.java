/**  
 * @Title: DatabaseConnection.java
 * @Package com.spark.DbManger
 * @Description: TODO
 * @author spark
 * @date 2015年7月18日
 */
package com.spark.DbManger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.spark.utils.dataSource.DataSource;
import com.spark.utils.dataSource.DatabaseMetaDateApplication;

/**
 * @author arvin
 *
 */
public class DatabaseConnection {

	public DatabaseConnection(HttpServletRequest request,
			HttpServletResponse response) {
		String dbUrl=request.getParameter("dbUrl");
		String dbUser=request.getParameter("dbUser");
		String dbPwd=request.getParameter("dbPwd");
		
		getConn(dbUrl,dbUser,dbPwd,request,response);
		
	}

	private void getConn(String dbUrl, String dbUser, String dbPwd, HttpServletRequest request, HttpServletResponse response) {
		
		DruidPlugin dp=new DruidPlugin(dbUrl,dbUser,dbPwd);
		String dbFlag=request.getSession().getId();
		ActiveRecordPlugin arp=new ActiveRecordPlugin(dbFlag,dp);
		arp.setDialect(new AnsiSqlDialect());
		DataSource db = new DataSource(dp, arp);	
		boolean f=db.start();
		response.setCharacterEncoding("UTF-8");
		if(f){
			Connection conn = null;
			try {
				conn = db.getDp().getDataSource().getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 DatabaseMetaDateApplication app = new DatabaseMetaDateApplication(conn);
				if(app!=null){
					response.setContentType("text/plain;charset=utf-8");
					try {
						Map returnData=new HashMap();
						ArrayList tables = app.getAllTableList(null);
						returnData.put("tables", tables);
						Map dbinfo = app.getDataBaseInformations();
						returnData.put("baseinfo", dbinfo);
						response.getWriter().write(JSONObject.toJSONString(returnData));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}
		}else{
			try {
				response.getWriter().println("获取连接失败");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}



	

}
