package com.service.impl;

import com.entity.BaseEntity;
import com.entity.LogAnnotation;
import com.entity.PageBean;
import com.mapper.BaseMapper;
import com.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T extends BaseEntity, ID extends Serializable>
	implements BaseService<T, ID> {
	
	@Autowired
	private BaseMapper<T, ID> baseMapper;

	@LogAnnotation(moduleName = "", operate = "添加")
	@Override
	public int insert(String onlineId, T model) {
		model.setIdForInsert(onlineId);
		return baseMapper.insert(model);
	}

	@LogAnnotation(moduleName = "", operate = "添加")
	@Override
	public int insertSelective(String onlineId, T model) {
		model.setIdForInsert(onlineId);
		return baseMapper.insertSelective(model);
	}

	@LogAnnotation(moduleName = "", operate = "更新")
	@Override
	public int update(String onlineId, T model) {
		model.setCommonFiledsForUpdate(onlineId);
		return baseMapper.updateByPrimaryKey(model);
	}

	@LogAnnotation(moduleName = "", operate = "更新")
	@Override
	public int updateSelective(String onlineId, T model) {
		model.setCommonFiledsForUpdate(onlineId);
		return baseMapper.updateByPrimaryKeySelective(model);
	}

	@LogAnnotation(moduleName = "", operate = "删除")
	@Override
	public int delete(ID id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@LogAnnotation(moduleName = "", operate = "删除")
	@Override
	public int deleteBatch(List<ID> ids) {
		return baseMapper.deleteBatch(ids);
	}


	@Override
	public T selectByPrimaryKey(ID id) {
		return baseMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<T> getList() {
		return baseMapper.getList();
	}


	@Override
	public List<T> getListByMap(Map<String, Object> map) {
		return baseMapper.getListByMap(map);
	}


	@Override
	public Map<String, Object> getListByMapPage(Map<String, Object> map, 
			int currentPage, int pageSize) {
		
		Map<String, Object> body = new HashMap<String, Object> ();
		List<T> list = new ArrayList<T> ();
		int totalRecord = 0;//总记录数
        PageBean pb = new PageBean(currentPage, pageSize, totalRecord);

        //totalRecord = baseMapper.getListByMap(map).size();
        totalRecord = baseMapper.getCountByMap(map).intValue();
        
        if (totalRecord == 0) {  // 当总记录为0时，直接返回
        	body.put("totalPage", pb.getTotalPage());
            body.put("totalRecord", totalRecord);
            body.put("list", list);
            return body;
        }

        map.put("startIndex", pb.getStartIndex());
        map.put("pageSize", pageSize);
         
        list = baseMapper.getListByMap(map);

		pb = new PageBean(currentPage, pageSize, totalRecord);
        body.put("totalPage", pb.getTotalPage());
        body.put("totalRecord", totalRecord);
        body.put("list", list);
        
		return body;
	}

	@Override
	public Long getCount() {
		return baseMapper.getCount();
	}

	@Override
	public Long getCountByMap(Map<String, Object> map) {
		return baseMapper.getCountByMap(map);
	}
	
	
}
