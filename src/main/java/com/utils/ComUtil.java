/**
 * Copyright(c) Goldeye Science & Technology Ltd. 
 */
package com.utils;

import java.util.UUID;

/**
 * 工具类
 */

public class ComUtil {
	
	
	/**
	 * 生成UUID,返回大写32位字符
	 * @return string
	 */
	public static String getUUID32(){
	    return UUID.randomUUID().toString().replace("-", "").toUpperCase();//大写32位
	    //return UUID.randomUUID().toString().replace("-", "").toLowerCase();//小写32位
	}

}
