
package com.utils;


import com.exception.ParamException;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;




public class CheckParamUtil {
	public static void checkParam(Object o) throws Exception {
		int flag = 0;
		out: {
			final Class<?> objClass = o.getClass();
			Field[] farray = objClass.getDeclaredFields();
			Class<CheckString> chkString = CheckString.class;
			for (int i = 0; i < farray.length; i++) {
				Field field = farray[i];
				String fieldName = field.getName();
				if (field.isAnnotationPresent(chkString)) {
					CheckString chk = field.getAnnotation(chkString);
					boolean required = chk.required(); // 获取字段是否需要填写
					field.setAccessible(true);
					String fileType = field.getType().getTypeName();
					//System.out.println(fileType);
					if (fileType.contains("String")) {
						if (required) {
							try {
								String fValue = (String) field.get(o);// 转换异常
								if (fValue == null || "".equals(fValue)) {
									flag = -1;
								}
							} catch (Exception e) {
								flag = -2;
							}
						}
					} else if (fileType.contains("Double")) {
						if (required) {
							try {
								Double fValue = (Double) field.get(o);
								if (fValue == null|| "".equals(fValue)) {
									flag = -1;
								}
							} catch (Exception e) {
								flag = -2;
							}
						}
					}
					else if (fileType.contains("Integer")) {
						if (required) {
							try {
								Integer fValue = (Integer) field.get(o);
								if (fValue == null|| "".equals(fValue)) {
									flag = -1;
								}
							} catch (Exception e) {
								flag = -2;
							}
						}
					}else if (fileType.contains("Date")) {
						if (required) {
							try {
								Date fValue = (Date) field.get(o);
								//System.out.println(fValue);
								if (fValue == null|| "".equals(fValue)) {
									flag = -1;
								}
							} catch (Exception e) {
								//e.printStackTrace();
								flag = -2;
							}
						}
					}else if (fileType.contains("List")) {
						if (required) {
							try {
								List fValue = (List) field.get(o);
								//System.out.println(fValue);
								if (fValue == null|| "".equals(fValue)) {
									flag = -1;
								}
							} catch (Exception e) {
								//e.printStackTrace();
								flag = -2;
							}
						}
					}
				}
				if (flag == -1) {
					throw new ParamException( fieldName + "必须填写");
				} else if (flag == -2) {
					// 目标double类型，请求Srtring,强转失败
					throw new ParamException(fieldName+"输入格式不正确");
				}
			}
		}
	}

	/**
	 * @author Lai
	 * @param map 需校验的集合
	 * @param field map中的某个字段
	 *   defaultVal 默认值
	 * @param required 根据接口需求是否必须填写
	 */
	public static Object checkMap(Map<String, Object> map,String field,boolean required) throws Exception{
		if (required) {
			if (map.containsKey(field)) {
				//必须  且有Key
				//请求过来如果存在 则判断是否为空为空
				if (map.get(field)!=null&&!"".equals(map.get(field))) {
					return map.get(field);
				}else {
					//必须填写,key存在,value为空
					throw new ParamException(field+"的值不能为空");
				}
			}else {
				//必须  但没有Key
				throw new ParamException("请求字段"+field+"不存在");
			}
		}else {
			if (map.containsKey(field)) {
				//不必须,但是也有Key
				return map.get(field);
			}else {
				//不必须  什么都没有
				return null;
			}
		}
	}
}
