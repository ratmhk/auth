package com.entity;

public enum ResultStatusCode {  
	
	SUCCESS(0, "success"),  //成功
    SYSTEM_ERR(4001, "System error"),	//异常处理错误
    
    CREATE_PAYMENT_FAILED(4038, "Failed to create payment, please try again later"),//创建付款失败，请稍后重试！
    INVALID_WECHAT_CODE(4039, "WeChat code is invalid"),//微信网页授权code失效
    
    
    INVALID_SAVE_TOKEN(5001, "Invalid save token"),	//保存token不成功
    INVALID_VALIDATE_TOKEN(5002, "Validate token error"),	//校验token失效
    INVALID_DELETE_TOKEN(5003, "Token delete failed"),	//token删除失败

	PARAM_ERR(6001, "Parameter error"),//参数入参错误
	SAVE_ERR(6002, "保存失败"), // 保存失败
	INVALID_SERIALNO_NULL(6003, "设备序列号不存在"),//设备序列号不存在

    INVALID_USER_EMPTY(7001, "登录用户名不能为空"),	//登录用户不能为空
    INVALID_USER_NOT(7002, "用户不存在"),	//用户不存在
    INVALID_LOGIN_PASSWORD(7003, "登录密码不正确"),	//登录密码不正确

    INVALID_USER_DISABLE(7004, "该用户账号状态被禁用"),	//该用户账号状态被禁用

    INVALID_LOGINNAME_EXIST(7008, "登录名已存在"),	//登录名已存在
    INVALID_PHONE_EXISTS(7009, "该手机号已被注册"),       //该手机号已被注册
    INVALID_USER_LEVEL(7010, "登陆用户权限不足"),	//登陆用户权限不足

    INVALID_UPDATE_PASSWORD(7011, "只能修改自己的密码"),	//只能修改自己的密码
    INVALID_ORIGINAL_PASSWORD(7012, "原密码不正确"),	//原密码不正确
    
    INVALID_ID_NUMBER_ERROR(7013, "身份证号格式不正确"),	//身份证号格式不正确
    INVALID_ID_NUMBER_EXIST(7014, "身份证号已存在"),	//身份证号格式已存在

    INVALID_MOBILE_ERROR(7005, "手机号码格式不正确"),	//手机号码格式不正确
    INVALID_PHONE_ERROR(7006, "电话格式不正确"),	//电话格式不正确
    INVALID_EMAIL_ERROR(7007, "邮箱格式不正确"),	//邮箱格式不正确
    INVALID_APPLY_ENTRY(7008, "重复申请加入"),	//工人重复申请加入
    INVALID_USER_BALANCE(7009, "账号余额不足"),	//账号余额不足

    INVALID_NAME_EXIST(8001, "名称已存在"),	//名称已存在

    INVALID_RECORD_NULL(11001, "记录不存在");//该记录不存在


    private int errorCode;
    private String errorMsg;  
    
    private ResultStatusCode(int ErrorCode, String ErrorMsg) {  
        this.errorCode = ErrorCode;  
        this.errorMsg = ErrorMsg;  
    }  
    
    public int getErrorCode() {  
        return errorCode;  
    }  
  
    public void setErrorCode(int errorCode) {  
        this.errorCode = errorCode;  
    }  
  
    public String getErrorMsg() {  
        return errorMsg;  
    }  
  
    public void setErrorMsg(String errorMsg) {  
        this.errorMsg = errorMsg;  
    } 
    
    
}  
