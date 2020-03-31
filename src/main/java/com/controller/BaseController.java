package com.controller;


import com.entity.PageBean;
import com.entity.ResultMsg;
import com.entity.ResultStatusCode;
import com.exception.ParamException;
import com.service.BaseService;
import com.utils.CheckParamUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController<T extends Object, ID extends Serializable> {

	private final static Logger log = LoggerFactory.getLogger(BaseController.class);
    		
	
	@Autowired
	private BaseService<T, ID> baseService;
	
	public BaseController() {
		/*try {
			// 通过反射获取T的实际类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// System.out.println("model的实际类型为：" + clazz);

			// 通过反射生成对象实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}			*/		
	}
	
	
	/**
	 * @功能: 添加
	 * @参数: onlineId, model
	 * @返回: ResultMsg
	 */

	@ResponseBody
	@RequiresRoles(value = {"onlyInsert","超级管理员"},logical = Logical.OR)
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json")
	public ResultMsg insert(HttpServletRequest request, @RequestBody T model) {
		ResultMsg resultMsg = null;
		try {			
			String onlineId = request.getHeader("id");
			CheckParamUtil.checkParam(model);

			int res = baseService.insert(onlineId, model);
			if (res > 0) {
				resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(),
					    ResultStatusCode.SUCCESS.getErrorMsg(), null);
			} else {
				resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
						ResultStatusCode.SYSTEM_ERR.getErrorMsg(), null);
			}			
		} catch (ParamException e) {
            log.error(String.format("Param error: %s", e.getMessage()));
            resultMsg = new ResultMsg(ResultStatusCode.PARAM_ERR.getErrorCode(),
                    e.getMessage(), null);
        } catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
					e.getMessage(), null);
		}
		return resultMsg;
	}
	
	/**
	 * @功能: 选择性添加
	 * @参数: onlineId, model
	 * @返回: ResultMsg
	 */
    @RequiresRoles(value = {"onlyInsert","超级管理员"},logical = Logical.OR)
    @ResponseBody
	@RequestMapping(value = "/insertSelective", method = RequestMethod.POST, produces = "application/json")
	public ResultMsg insertSelective(HttpServletRequest request, @RequestBody T model) {
		ResultMsg resultMsg = null;
		try {			
			String onlineId = request.getHeader("id");
			CheckParamUtil.checkParam(model);
			int res = baseService.insertSelective(onlineId, model);
			if (res > 0) {
				resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), 
					    ResultStatusCode.SUCCESS.getErrorMsg(), null);
			} else {
				resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
						ResultStatusCode.SYSTEM_ERR.getErrorMsg(), null);
			}	
		} catch (ParamException e) {
            log.error(String.format("Param error: %s", e.getMessage()));
            resultMsg = new ResultMsg(ResultStatusCode.PARAM_ERR.getErrorCode(),
                    e.getMessage(), null);
        } catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
					e.getMessage(), null);
		}
		return resultMsg;
	}
	
	/**
	 * @功能: 修改
	 * @参数: onlineId, model
	 * @返回: ResultMsg
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    @RequiresRoles(value = {"onlyEdit","超级管理员"},logical = Logical.OR)
    public ResultMsg update(HttpServletRequest request, @RequestBody T model) {
		ResultMsg resultMsg = null;
		try {			
			String onlineId = request.getHeader("id");
			int res = baseService.update(onlineId, model);
			if (res > 0) {
				resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), 
					    ResultStatusCode.SUCCESS.getErrorMsg(), null);
			} else {
				resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
						ResultStatusCode.SYSTEM_ERR.getErrorMsg(), null);
			}	
		} catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(),
					e.getMessage(), null);
		}
		return resultMsg;
	}
	
	/**
	 * @功能: 选择性修改
	 * @参数: onlineId, model
	 * @返回: ResultMsg
	 */
	@ResponseBody
	@RequestMapping(value = "/updateSelective", method = RequestMethod.POST, produces = "application/json")
    @RequiresRoles(value = {"onlyEdit","超级管理员"},logical = Logical.OR)
	public ResultMsg updateSelective(HttpServletRequest request, @RequestBody T model) {
		ResultMsg resultMsg = null;
		try {			
			String onlineId = request.getHeader("id");
			int res = baseService.updateSelective(onlineId, model);
			if (res > 0) {
				resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), 
					    ResultStatusCode.SUCCESS.getErrorMsg(), null);
			} else {
				resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
						ResultStatusCode.SYSTEM_ERR.getErrorMsg(), null);
			}	
		} catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(),
					e.getMessage(), null);
		}
		return resultMsg;
	}
	
	/**
	 * @功能: 删除
	 * @参数: model
	 * @返回: ResultMsg
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    @RequiresRoles(value = {"onlyDelete","超级管理员"},logical = Logical.OR)
	public ResultMsg delete(HttpServletRequest request, @RequestBody Map<String, Object> map) {
		ResultMsg resultMsg = null;
		try {	
			ID id = (ID) CheckParamUtil.checkMap(map, "id", true);
			int res = baseService.delete(id);
			if (res > 0) {
				resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), 
					    ResultStatusCode.SUCCESS.getErrorMsg(), null);
			} else {
				resultMsg = new ResultMsg(ResultStatusCode.INVALID_RECORD_NULL.getErrorCode(),
						ResultStatusCode.INVALID_RECORD_NULL.getErrorMsg(), null);
			}	
		} catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(),
					e.getMessage(), null);
		}
		return resultMsg;
	}
	
	/**
	 * @功能: 删除多条记录
	 * @参数: model
	 * @返回: ResultMsg
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST, produces = "application/json")
    @RequiresRoles(value = {"onlyDelete","超级管理员"},logical = Logical.OR)
	public ResultMsg deleteBatch(HttpServletRequest request, @RequestBody Map<String, Object> map) {
		ResultMsg resultMsg = null;
		try {		
			List<ID> ids = (List<ID>) CheckParamUtil.checkMap(map, "ids", true);
			int res = baseService.deleteBatch(ids);
			if (res > 0) {
				resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), 
					    ResultStatusCode.SUCCESS.getErrorMsg(), null);
			} else {
				resultMsg = new ResultMsg(ResultStatusCode.INVALID_RECORD_NULL.getErrorCode(),
						ResultStatusCode.INVALID_RECORD_NULL.getErrorMsg(), null);
			}	
		} catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
					e.getMessage(), null);
		}
		return resultMsg;
	}
	
	/**
	 * @功能: 获取一条记录
	 * @参数: id
	 * @返回: ResultMsg
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/getById", method = RequestMethod.POST, produces = "application/json")
	public ResultMsg getById(HttpServletRequest request, @RequestBody Map<String, Object> map) {
		ResultMsg resultMsg = null;
		try {					
			ID id = (ID) CheckParamUtil.checkMap(map, "id", true);
			T body = baseService.selectByPrimaryKey(id);
			resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode() ,
				ResultStatusCode.SUCCESS.getErrorMsg(), body);
		} catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
					e.getMessage(), null);
		}
		return resultMsg;
	}
	
	
	/**
	 * @功能:     获取全部记录
	 * @参数:    
	 * @返回: ResultMsg
	 */
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json")
	public ResultMsg getList(HttpServletRequest request, @RequestBody Map<String, Object> map) {
		ResultMsg resultMsg = null;
		Map<String, Object> body = new HashMap<String, Object> ();
		try {			
			List<T> list = baseService.getList();
			body.put("list", list);
			resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), 
				ResultStatusCode.SUCCESS.getErrorMsg(), body);
		} catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
					e.getMessage(), null);
		}
		return resultMsg;
	}
	
	/**
	 * @功能:     全字段模糊, 获取全部记录
	 * @参数:    
	 * @返回: ResultMsg
	 */
	@ResponseBody
	@RequestMapping(value = "/getListByMap", method = RequestMethod.POST, produces = "application/json")
	public ResultMsg getListByMap(HttpServletRequest request, @RequestBody Map<String, Object> map) {
		ResultMsg resultMsg = null;
		Map<String, Object> body = new HashMap<String, Object> ();
		try {			
			List<T> list = baseService.getListByMap(map);
			body.put("list", list);
			resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), 
				ResultStatusCode.SUCCESS.getErrorMsg(), body);
		} catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
					e.getMessage(), null);
		}
		return resultMsg;
	}
	
    /**
	 * @功能:     全字段模糊, 获得全部记录, 并分页
	 * @参数: currentPage, pageSize
	 * @返回: resultMsg  
	 *        resultMsg.body = {totalPage: 1, totalRecord: 10, list:[{},{},....]}
	 */
	@ResponseBody
	@RequestMapping(value = "/getListByMapPage", method = RequestMethod.POST, produces = "application/json")
	public ResultMsg getListByMapPage(HttpServletRequest request, @RequestBody Map<String, Object> map){
		
		ResultMsg resultMsg = null;
		Map<String, Object> body = new HashMap<String, Object> ();		
		try {					
			int currentPage = PageBean.getCurrentPage(map);//当前页为第几页
	        int pageSize = PageBean.getPageSize(map);//每页显示的记录数
	        body = baseService.getListByMapPage(map, currentPage, pageSize);
			resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(),
					ResultStatusCode.SUCCESS.getErrorMsg(), body);			
		} catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(),
					e.getMessage(), null);
		}
		return resultMsg;
	}	
	
	/**
	 * @功能: 获取全部记录统计数
	 * @参数: 
	 * @返回: ResultMsg
	 */
	@ResponseBody
	@RequestMapping(value = "/getCount", method = RequestMethod.POST, produces = "application/json")
	public ResultMsg getCount() {
		ResultMsg resultMsg = null;
		Map<String, Object> body = new HashMap<String, Object> ();
		try {					
			Long count = baseService.getCount();
			body.put("count", count);
			resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), 
				ResultStatusCode.SUCCESS.getErrorMsg(), body);
		} catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
					e.getMessage(), null);
		}
		return resultMsg;
	}
	
	/**
	 * @功能: 根据条件，获取全部记录统计数
	 * @参数: map
	 * @返回: ResultMsg
	 */
	@ResponseBody
	@RequestMapping(value = "/getCountByMap", method = RequestMethod.POST, produces = "application/json")
	public ResultMsg getCountByMap(@RequestBody Map<String, Object> map) {
		ResultMsg resultMsg = null;
		Map<String, Object> body = new HashMap<String, Object> ();
		try {					
			Long count = baseService.getCountByMap(map);
			body.put("count", count);
			resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), 
				ResultStatusCode.SUCCESS.getErrorMsg(), body);
		} catch (Exception e) {
			log.error(String.format("System error: %s", e.getMessage()));
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), 
					e.getMessage(), null);
		}
		return resultMsg;
	}
			
}
