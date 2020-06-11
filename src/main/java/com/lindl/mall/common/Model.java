package com.lindl.mall.common;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:42
 */
@Data
public class Model implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        try {
            return JSON.toJSONString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return super.toString();
        }
    }
}
