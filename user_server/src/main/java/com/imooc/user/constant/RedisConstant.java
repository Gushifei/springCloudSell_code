package com.imooc.user.constant;

/**
 * @author 赵亮
 * @date 2018-07-17 17:52
 */
public interface RedisConstant
{
    String TOKEN_TEMPLATE = "token_%s";

    /**
     *过期时间（单位:s）
     *@author：赵亮
     *@date：2018-07-17 17:21
     */
    Integer expire = 7200;
}
