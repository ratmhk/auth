
package com.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)  
@Retention(RetentionPolicy.RUNTIME)  //指定了注解保留的周期 
public @interface CheckString {

	/**
	 * 字段是否需要填写
	 */
	boolean required() default false;
	

}
