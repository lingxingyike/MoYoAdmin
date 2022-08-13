package com.example.demo.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Auther: cch
 * @Date: 2020/9/21 16:12
 * @Description: 相应结果实体类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String code;
    private Object data;
    private String message;

}

