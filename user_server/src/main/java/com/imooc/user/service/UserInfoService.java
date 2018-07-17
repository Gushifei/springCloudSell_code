package com.imooc.user.service;

import com.imooc.user.dataobject.UserInfo;

/**
 * @author 赵亮
 * @date 2018-07-17 16:42
 */
public interface UserInfoService
{
    /**
     *通过openid来查下用户信息
     *@author：赵亮
     *@date：2018-07-17 16:42
    */
    UserInfo findByOpenid(String openid);
}
