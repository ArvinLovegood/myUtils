package com.spark.utils.dataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBInfo {
	
	private Properties prop ;
	public DBInfo() {
		
		InputStream in = DBInfo.class.getResourceAsStream(
			    "Database_INFO.properties");
		
		Properties prop =  new  Properties(); 
		try {
			prop.load(in);
			in.close(); //关闭流  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setProp(prop);
	}
	public Properties getProp() {
		return prop;
	}
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
	public static void main(String[] args) {
		Object url = new DBInfo().getProp().get("dbUrlB");
		System.out.println(url);
	}
}
