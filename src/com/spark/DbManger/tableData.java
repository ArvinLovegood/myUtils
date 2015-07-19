/**  
 * @Title: tableData.java
 * @Package com.spark.DbManger
 * @Description: TODO
 * @author spark
 * @date 2015年7月19日
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
 * @author arvinlovegood
 *
 */
public class tableData {

	public tableData(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated constructor stub
		String tableName=request.getParameter("TName");
		String pageNumber=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		DbPro dao = Db.use(request.getSession().getId());
		Page<Record> page = dao.paginate(Integer.parseInt(pageNumber), Integer.parseInt(pageSize), "select * ", " from "+tableName +"  where 1=1 ");
		int total = page.getTotalRow();
		 List<Record> data = page.getList();
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/plain;charset=utf-8");
		 Iterator<Record> it = data.iterator();
		 List reData=new ArrayList();
		 while(it.hasNext()){
			 Record re = it.next();
			 reData.add(re.getColumns());
		 }
		 Map rePage=new HashMap();
		 rePage.put("total", total);
		 rePage.put("rows", reData);
		 try {
			response.getWriter().write(JSONObject.toJSONString(rePage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
