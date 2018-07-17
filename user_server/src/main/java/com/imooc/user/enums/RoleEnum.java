package com.imooc.user.enums;

import lombok.Getter;

/**
 * @author 赵亮
 * @date 2018-07-17 17:13
 */
@Getter
public enum RoleEnum
{
    BUYER(1, "买家"),
    SELLER(2, "卖家"),;
    private Integer code;

    private String message;

    RoleEnum(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
