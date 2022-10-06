package com.itheima.reggie.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wu-yuezhou
 * @Email wyz18405583620@163.com
 * @date 2022/9/25
 * @param <T> 通用返回结果类
 */
@Data
public class R<T> implements Serializable {

    /**
     * 编码：1成功，0和其它数字为失败
     */
    private Integer code;

    private String msg; //错误信息

    /**
     * 数据
     */
    private T data;

    /**
     * 动态数据
     */
    private Map map = new HashMap();

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
