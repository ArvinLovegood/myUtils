package com.spark.utils.random;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

import com.spark.utils.File.AppendToFile;

public class RandomUtils {

	public static void main(String[] args) {
		for(int i=1;i<1000;i++){
			//System.out.println(getUUID());
			System.out.println(getRandomStr(32,getUUID()));
			AppendToFile.appendMethodB("F:/workspace/sparkUtils/demo/random.txt", "\r\t"+getRandomStr(32,getUUID()));
		}

	}

	public static String getUUID() {
		String uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
	
	public static String getRandomStr(int count,String...feed){
		String feeds="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		if(feed!=null&&feed.length>0){
			for(String f:feed){
				feeds+=f;
			}
		}
		String randomStr = RandomStringUtils.random(count, feeds);
		return randomStr;
	}
}
