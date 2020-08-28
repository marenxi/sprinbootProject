package com.itszt.manager.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @Description: 返回数据通用实体
 * @author: marenxi@jiuxian.com
 * @date: 2018-12-15 20:17/星期六
 */
@JsonInclude(Include.NON_NULL)
public class DataResponse {
    private  Integer code;
    private String msg;
    private Integer count;
    private Object data;
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
