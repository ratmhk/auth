package com.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * BaseService 接口：提供常用的数据库操。
 * T 为表对应的实现类，
 * ID 为该表中id的数据类型（一般为String）
 * 
 * 1. 添加
 *   1.1 普通添加 insert(onlineId, model)
 *   1.2 根据key添加  insertByParamKey(onlineId, model)
 * 2. 修改
 *   2.1 普通修改 updateByPrimaryKey(onlineId, model)
 *   2.2 根据key修改 updateByPrimaryKeySelective(onlineId, model)
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
 * 6.更多数据库操作，需要根据各实现层实现                          
 * </pre>
 *

 * 
 */
public interface BaseService<T extends Object, ID extends Serializable> {
	
	int insert(String onlineId, T model);
	
	int insertSelective(String onlineId, T model);
	
	int update(String onlineId, T model);
	
	int updateSelective(String onlineId, T model);
	
	int delete(ID id);
	
	int deleteBatch(List<ID> ids);
	
	T selectByPrimaryKey(ID id);
		
	List<T> getList();
		
	List<T> getListByMap(Map<String, Object> map);
	
	Map<String, Object> getListByMapPage(Map<String, Object> map,
                                         int currentPage, int pageSize);

	Long getCount();

	Long getCountByMap(Map<String, Object> map);
		

}
