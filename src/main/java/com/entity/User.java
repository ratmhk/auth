package com.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author
 * 
 * @date 2019-10-20
 */

public class User extends BaseEntity{

    public User() {
    }
    private String img;

    private String id;

    /**
     * 登录的账号
     */
    private String acc;

    private String password;

    private Integer age;

    private String sex;

    /**
     * 登录后显示的名字
     */
    private String nickname;

    private String phone;

    private String email;

    private String idcard;

    private String introduce;

    private Date createDate;

    private String createUser;

    private Date updateDate;

    private String updateUser;

    private String status;

    /**
     * 状态1正常 0冻结
     */
    private String schoolId;

    /**
     * 0游客账号 1自己注册 2校级账号分发 3管理员分发
     */
    private String accType;

    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc == null ? null : acc.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Override
    public String toString() {
        return "User{" +
                "img='" + img + '\'' +
                ", id='" + id + '\'' +
                ", acc='" + acc + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", idcard='" + idcard + '\'' +
                ", introduce='" + introduce + '\'' +
                ", createDate=" + createDate +
                ", createUser='" + createUser + '\'' +
                ", updateDate=" + updateDate +
                ", updateUser='" + updateUser + '\'' +
                ", status='" + status + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", accType='" + accType + '\'' +
                ", roles=" + roles +
                '}';
    }
}