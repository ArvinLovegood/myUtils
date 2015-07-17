/**  
 * @Title: Demo.java
 * @Package com.spark.utils.demo
 * @Description: TODO
 * @author spark
 * @date 2015年7月17日
 */
package com.spark.utils.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author Spark
 *
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(RandomStringUtils.randomAlphabetic(32));
		System.out.println(RandomStringUtils.randomAlphanumeric(32));
		System.out.println(RandomStringUtils.randomAscii(32));
		System.out.println(RandomStringUtils.randomNumeric(32));
		System.out.println(RandomStringUtils.random(32, "~!@#$%^&*()_+|}{\":?><"));
	}

}
