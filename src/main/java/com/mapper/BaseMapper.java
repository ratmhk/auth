package com.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * <pre>
 * BaseMapper 接口：提供常用的数据库操。
 * T 为表对应的实现类，
 * ID 为该表中id的数据类型（一般为String）
 * 
 * 1. 添加
 *   1.1 普通添加 insert(model)
 *   1.2 选择性添加  insertSelective(model)
 * 2. 修改
 *   2.1 普通修改 updateByPrimaryKey(model)
 *   2.2 选择性修改 updateByPrimaryKeySelective(model)
 * 3. 删除
 *   3.1 根据ID删除一条记录 deleteByPrimaryKey(id)
 *   3.2 根据IDS删除多条记录  deleteBatch(ids)
 * 4. 查询
 *   4.1 根据ID查询一条记录 selectByPrimaryKey(id)
 *   4.2 获取全部记录 getList()
 *   4.3 模糊查询获取全部记录 getListByMap(map)
 * 5. 统计
 *   5.1 获取表中总记录数 getCount()
 *   5.2 模糊查询获取总记录数 getList()
 * 6. 更多数据库操作，需要根据各实现层实现                          
 * </pre>
 *

 * 
 */

public interface BaseMapper<T extends Object, ID extends Serializable> {    	
			
	int insert(T model);
	
	int insertSelective(T model);
	
	int updateByPrimaryKey(T model);
	
	int updateByPrimaryKeySelective(T model);
	
	int deleteByPrimaryKey(@Param("id") ID id);
	
	int deleteBatch(@Param("ids") List<ID> ids);
	
	T selectByPrimaryKey(@Param("id") ID id);
	
	List<T> getList();
		
	List<T> getListByMap(Map<String, Object> map);

	Long getCount();

	Long getCountByMap(Map<String, Object> map);

	List<Map<String,Object> > getSchoolList(Map<String,Object> map);



}
