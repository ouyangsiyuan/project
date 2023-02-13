package com.ousy.petadopt.common;

import lombok.Data;

//定义具体的数据返回格式
@Data
public class ResultDto<T> {
//链式编程
    private Boolean success;
    private Integer code;
    private String message;
    private T data ;

    public ResultDto(){}

    //操作成功，调用这个方法，返回成功的数据
    public static ResultDto ok(){
        ResultDto r = new ResultDto();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    //操作失败，调用这个方法，返回失败的数据
    public static ResultDto error(){
        ResultDto r = new ResultDto();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }

    public static ResultDto error(String msg){
        ResultDto r = new ResultDto();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage(msg);
        return r;
    }

    //使用链式编程
    public ResultDto success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResultDto message(String message){
        this.setMessage(message);
        return this;
    }

    public ResultDto code(Integer code){
        this.setCode(code);
        return this;
    }

//    public ResultDto data(String key, Object value){
//        this.data.put(key, value);
//        return this;
//    }

    public ResultDto data(T t){
        this.setSuccess(true);
        this.setCode(ResultCode.SUCCESS);
        this.setMessage("操作成功");
        this.setData(t);
        return this;
    }
}
