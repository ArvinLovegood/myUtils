/**  
 * @Title: TableInfo.java
 * @Package com.spark.DbManger
 * @Description: TODO
 * @author spark
 * @date 2015年7月19日
 */
package com.spark.DbManger;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.Db;
import com.spark.utils.dataSource.DatabaseMetaDateApplication;

/**
 * @author arvinlovegood
 *
 */
public class TableInfo {

	public TableInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated constructor stub
		String tableName=request.getParameter("TName");
		Connection conn = (Connection)request.getSession().getAttribute(request.getSession().getId());
		DatabaseMetaDateApplication app = new DatabaseMetaDateApplication(conn);
		response.setCharacterEncoding("UTF-8");
		if(app!=null){
			List TableColumns = app.getTableColumns(null, tableName);
			response.setContentType("text/plain;charset=utf-8");
			try {
				response.getWriter().write(JSONObject.toJSONString(TableColumns));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
