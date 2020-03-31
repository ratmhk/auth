package com.entity;

import lombok.Data;

@Data
public class ResultMsg {

    private Integer errorCode ;
    private String errorMsg = "";
    private Object body ;

    public ResultMsg(Integer errorCode, String errMsg, Object body) {
        this.errorCode = errorCode;
        this.errorMsg = errMsg;
        this.body = body;
    }

    public ResultMsg() {
    }
}
