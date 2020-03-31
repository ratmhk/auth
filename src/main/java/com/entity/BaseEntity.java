package com.entity;

import java.util.Date;
import java.util.UUID;

/**
 * <pre>
 * BaseEntity 类：
   *     提供数据库表中常用字段id, createUser等抽象的get/set方法。   
   *     提供了   setIdForInsert，setCommonFiledsForInsert，setCommonFiledsForUpdate方法             
 * </pre>
 *

 * 
 */

public abstract class BaseEntity {
		
	public abstract String getId();
	
	public abstract void setId(String id);
	
	public abstract String getCreateUser();
	
	public abstract void setCreateUser(String createUser);

    public abstract Date getCreateDate();

    public abstract void setCreateDate(Date createDate);

    public abstract String getUpdateUser();

    public abstract void setUpdateUser(String updateUser);

    public abstract Date getUpdateDate();

    public abstract void setUpdateDate(Date updateDate);
    
    public void setIdForInsert(String user) {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        this.setId(uuid);
        this.setCommonFiledsForInsert(user);
    }

    public boolean setCommonFiledsForInsert(String user) {
		boolean result = false;
        if (user != null && !"".contentEquals(user)) {
            setCreateUser(user);
            setUpdateUser(user);
			setCreateDate(new Date());
            setUpdateDate(new Date());
			result = true;
        } 
        return result;
    }

    public boolean setCommonFiledsForUpdate(String user) {
		boolean result = false;
        if (user != null && !"".contentEquals(user)) {
            setUpdateUser(user);
            setUpdateDate(new Date());
            result = true;
        }
        return result;
    }		
	
}
