package com.entity;

import java.util.Date;

public class RolePermission extends BaseEntity{
    /**
     * 没有实际意义的，为了符合mybatis通用框架的要求
     */
    private String id;

    private String roleId;

    private String permisionId;

    /**
     * 没有实际意义的，为了符合mybatis通用框架的要求
     */

    private String createUser;
    private String updateUser;
    private Date createDate;
    private Date updateDate;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPermisionId() {
        return permisionId;
    }

    public void setPermisionId(String permisionId) {
        this.permisionId = permisionId == null ? null : permisionId.trim();
    }
}