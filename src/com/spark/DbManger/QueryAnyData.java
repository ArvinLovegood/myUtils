/**  
 * @Title: QueryAnyData.java
 * @Package com.spark.DbManger
 * @Description: TODO
 * @author spark
 * @date 2015年7月20日
 */
package com.spark.DbManger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @author Spark
 *
 */
public class QueryAnyData {

	public QueryAnyData(HttpServletRequest request, HttpServletResponse response) {
		
		String SQL=request.getParameter("SQL").toUpperCase();
		String pageNumber=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		
		DbPro dao = Db.use(request.getSession().getId());
		//int indexOFfrom=SQL.indexOf("FROM");
		String select="select *  ";
		String from="	from ("+SQL+") WHERE 1=1 ";
		Page<Record> page = dao.paginate(Integer.parseInt(pageNumber), Integer.parseInt(pageSize), select, from);
		int total=page.getTotalRow();
		Iterator<Record> it = page.getList().iterator();
		 List reData=new ArrayList();
		 String[] Columns=null;
		while(it.hasNext()){
			Record re = it.next();
			Columns = re.getColumnNames();
			reData.add(re.getColumns());
		}
		
		//data
		 Map rePage=new HashMap();
		 rePage.put("total", total);
		 rePage.put("rows", reData);
		 
		 Map returnPage=new  HashMap();
		 List ColumnsList=new ArrayList();
		 for(String s:Columns){
			 Map column=new  HashMap();
			 column.put("field", s);
			 column.put("title", s);
			 column.put("width", 100);
			 ColumnsList.add(column);
		 }
		 returnPage.put("columns", ColumnsList);
		 returnPage.put("data", rePage);
		 
		 
		 try {
			 response.setCharacterEncoding("UTF-8");
			 response.setContentType("text/plain;charset=utf-8");
			 if("datagrid".equals(request.getParameter("f"))){
				 response.getWriter().write(JSONObject.toJSONString(rePage));
			 }else{				 
				 response.getWriter().write(JSONObject.toJSONString(returnPage));
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
