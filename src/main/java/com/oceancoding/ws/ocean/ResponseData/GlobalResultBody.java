package com.oceancoding.ws.ocean.ResponseData;

import com.alibaba.fastjson.JSONObject;
import com.oceancoding.ws.ocean.ExceptionHandler.BaseErrorInfoInterface;
import com.oceancoding.ws.ocean.ExceptionHandler.Enum.CommonEnum;

public class GlobalResultBody {
    private String code;//响应代码

    private String message;//响应消息

    private Object result;//响应结果

    private GlobalResultBody(){

    }

    public GlobalResultBody(BaseErrorInfoInterface errorInfoInterface){
        this.code = errorInfoInterface.getResultCode();
        this.message = errorInfoInterface.getResultMsg();
    }

    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }

    public Object getResult(){
        return result;
    }

    public void setResult(Object result){
        this.result = result;
    }

    /*
    * 成功
    * */
    public static GlobalResultBody success(Object data){
        GlobalResultBody globalResultBody = new GlobalResultBody();
        globalResultBody.setCode(CommonEnum.SUCCESS.getResultCode());
        globalResultBody.setMessage(CommonEnum.SUCCESS.getResultMsg());
        globalResultBody.setResult(data);
        return globalResultBody;
    }

    /*
     * 失败
     * */
    public static GlobalResultBody error(BaseErrorInfoInterface errorInfoInterface){
        GlobalResultBody globalResultBody = new GlobalResultBody();
        globalResultBody.setCode(errorInfoInterface.getResultCode());
        globalResultBody.setMessage(errorInfoInterface.getResultMsg());
        globalResultBody.setResult(null);
        return globalResultBody;
    }

    /*
     * 自定义失败消息
     * */
    public static GlobalResultBody error(String code, String message){
        GlobalResultBody globalResultBody = new GlobalResultBody();
        globalResultBody.setCode(code);
        globalResultBody.setMessage(message);
        globalResultBody.setResult(null);
        return globalResultBody;
    }

    /*
     * 失败
     * */
    public static GlobalResultBody error(String message){
        GlobalResultBody globalResultBody = new GlobalResultBody();
        globalResultBody.setCode("-1");
        globalResultBody.setMessage(message);
        globalResultBody.setResult(null);
        return globalResultBody;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
