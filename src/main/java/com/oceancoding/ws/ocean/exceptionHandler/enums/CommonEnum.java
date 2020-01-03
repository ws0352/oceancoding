package com.oceancoding.ws.ocean.exceptionHandler.enums;

import com.oceancoding.ws.ocean.exceptionHandler.BaseErrorInfoInterface;

public enum CommonEnum implements BaseErrorInfoInterface {
    //数据操作错误定义
    SUCCESS("200", "接口响应成功"),
    BODY_NOT_MATCH("400", "请求数据格式不符"),
    SIGNATURE_NOT_MATCH("401", "请求的数字签名不匹配"),
    SQL_HANDLE_ERROR("402", "数据库异常"),
    NOT_FOUNT("404", "未找到该资源"),
    METHOD_NOT_ALLOWED("405","请求方式不正确"),
    UNSUPPORTED_MEDIA_TYPE("415", "不支持当前媒体类型"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误"),
    SERVER_BUSY("503", "服务器正忙，请稍后再试"),
    ;

    private String resultCode;//错误描述
    private String resultMsg;//错误码

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

    CommonEnum(String resultCode, String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
