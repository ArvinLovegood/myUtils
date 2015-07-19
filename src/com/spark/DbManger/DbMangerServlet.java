package com.spark.DbManger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.spark.utils.dataSource.DataSource;
import com.spark.utils.dataSource.DatabaseMetaDateApplication;

public class DbMangerServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1173605496344073498L;
	DatabaseMetaDateApplication app;
	Connection conn ;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		/*DataSource MySQL = new DataSource("MySQL");
		try {
			 conn = MySQL.getDs().getDp().getDataSource().getConnection();
			 app=new DatabaseMetaDateApplication(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@Override
	protected void service(HttpServletRequest Request, HttpServletResponse Response)
			throws ServletException, IOException {
		String action=Request.getParameter("action");
		System.out.println("============DbMangerServlet===service===================action:"+action);

		if("newDbConn".equals(action)){
			new DatabaseConnection(Request,Response);
		}
		
		if("disConn".equals(action)){			
			ActiveRecordPlugin arp=(ActiveRecordPlugin) Request.getSession().getAttribute("arp");
			arp.stop();
			Response.setCharacterEncoding("UTF-8");
			Response.setContentType("text/plain;charset=utf-8");
			Response.getWriter().println("已关闭会话！");
			Request.getSession().invalidate();			
		}
		
		if("tableInfo".equals(action)){
			new TableInfo(Request,Response);
		}
		if("tableData".equals(action)){
			new tableData(Request,Response);
		}
		
/*		if("view".equals(action)){
			if(app!=null){
				Response.setContentType("text/json;charset=utf-8");
				Response.getWriter().write(JSONObject.toJSONString(app.getDataBaseInformations()));		
				Request.setAttribute("DBinfo", app.getDataBaseInformations());
				Request.setAttribute("TableList", app.getAllTableList(null));
			}
			Request.getRequestDispatcher(action+".jsp").forward(Request, Response);
		}*/
		
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			if(conn!=null){				
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.destroy();
	}
		
}
