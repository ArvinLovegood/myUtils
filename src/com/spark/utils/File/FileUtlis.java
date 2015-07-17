/**  
 * @Title: FileUtlis.java
 * @Package com.spark.utils.File
 * @Description: TODO
 * @author spark
 * @date 2015年7月17日
 */
package com.spark.utils.File;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.helper.DataUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Spark
 *
 */
public class FileUtlis {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String[] file={"F:/workspace/sparkUtils/src/com/spark/utils/File/FileUtlis.java",
				"I:/java.txt",
				"F:/建筑大学考勤系统/shenfz.txt",
				"F:/建筑大学考勤系统/141029172018.psc",
				"f:/A/VerifyCodeUtil.java",
				"f:/utf-8.sql"
		};
		
		for(String f:file){			
			//ReadFromFile.readFileByLines(f);
			String F = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			System.out.println(F);
		}
		String f=file[1];
		System.out.println(f+"===encode==="+ReadFromFile.codeString(f));
		//ReadFromFile.readFileByLines(f,"UTF-8");
		//ReadFromFile.readFileByBytes(f);
		//ReadFromFile.readFileByChars(f);
		//ReadFromFile.readFileByRandomAccess(f);
		
		String json = JSONObject.toJSONString(file);
		System.out.println(json);
	}
	
	/**
     * 判断文件的编码格式
     * @param fileName :file
     * @return 文件编码格式
     * @throws Exception
     */
	 public static String codeString(String fileName) throws Exception{
	        BufferedInputStream bin = new BufferedInputStream(
	        new FileInputStream(fileName));
	        int p = (bin.read() << 8) + bin.read();
	        String code = null;
	        //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
	        switch (p) {
	            case 0xefbb:
	                code = "UTF-8";
	                break;
	            case 0xfffe:
	                code = "Unicode";
	                break;
	            case 0xfeff:
	                code = "UTF-16BE";
	                break;
	            case 0x5c75:
	            	code = "ANSI|ASCII" ;
	            	break ;
	            default:
	                code = "GBK";
	        }
	        
	        return code;
	    }
   
	

}
