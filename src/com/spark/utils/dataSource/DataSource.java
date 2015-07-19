/**  
 * @Title: DataSource.java
 * @Package com.spark.utils.DataSource
 * @Description: TODO
 * @author spark
 * @date 2015年7月17日
 */
package com.spark.utils.dataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;

public class DataSource {

	private  DruidPlugin dp;
	private  ActiveRecordPlugin arp;
	private DataSource ds;
	
	public static void main(String[] args) throws SQLException {
		
		DataSource MySQL = new DataSource("MySQL");
		DataSource Oracle = new DataSource("Oracle");
		DataSource SQLServer = new DataSource("SQLServer");
		
/*		DatabaseMetaData dbMetaData = MySQL.getDs().getDp().getDataSource().getConnection().getMetaData();
		
		//MetaData.getTables(catalog, schemaPattern, tableNamePattern, types);
		String[] types = { "TABLE" };  
		 ResultSet rs = dbMetaData.getTables(null, null, "%", types);  
		
		while (rs.next()){     
			String tableName = rs.getString("TABLE_NAME");  //表名  
            String tableType = rs.getString("TABLE_TYPE");  //表类型  
            String remarks = rs.getString("REMARKS");       //表备注  
            System.out.println(tableName + "-" + tableType + "-" + remarks);  
        }*/
		
		/*for(int i=1;i<10;i++){
			List<Record> d = Db.use("MySQL").find("select * from hzx_attence_user");
			List<Record> d2 = Db.use("Oracle").find("select * from t_hzdrp_sys_user");
			System.out.println("MySQL:"+d.get(0));
			System.out.println("Oracle:"+d2.get(0));
		}*/
		
	}
	

	
	public DataSource(String dbFlag) {
		DBInfo info=new DBInfo();
		String dbUrl=info.getProp().getProperty("dbUrl"+dbFlag);
		String dbUser=info.getProp().getProperty("dbUser"+dbFlag);
		String dbPwd=info.getProp().getProperty("dbPwd"+dbFlag);
		System.out.println(dbUrl);
		System.out.println(dbUser);
		//System.out.println(dbPwd);
		DruidPlugin dp=new DruidPlugin(dbUrl,dbUser,dbPwd);
		ActiveRecordPlugin arp=new ActiveRecordPlugin(dbFlag,dp);
		arp.setDialect(new AnsiSqlDialect());
		
		this.ds = new DataSource(dp, arp);
		boolean f = this.ds.start();//启动
		System.out.println("连接数据库："+dbFlag+":"+f);
	}

	public DataSource(DruidPlugin dp,ActiveRecordPlugin arp) {
		this.dp=dp;
		this.arp=arp;
	}
	
	public DruidPlugin getDp() {
		return dp;
	}

	public void setDp(DruidPlugin dp) {
		this.dp = dp;
	}

	public ActiveRecordPlugin getArp() {
		return arp;
	}

	public void setArp(ActiveRecordPlugin arp) {
		this.arp = arp;
	}
	
	public boolean start(){
		try {
			return this.dp.start()&&this.arp.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(e.getMessage().contains("Config already exists")){				
				return true;
			}else{
				return this.dp.start()&&this.arp.start();
			}
		}		
	}
	
	public boolean stop(){
		return this.dp.stop()&&this.arp.stop();		
	}



	public DataSource getDs() {
		return ds;
	}



	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	
}
