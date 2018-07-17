package com.imooc.user.service.impl;

import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.repository.UserInfoRespostory;
import com.imooc.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 赵亮
 * @date 2018-07-17 16:44
 */
@Service
public class UserInfoServiceImpl implements UserInfoService
{
    @Autowired
    private UserInfoRespostory userInfoRespostory;
    /**
     * 通过openid来查下用户信息
     *
     * @param openid
     * @author：赵亮
     * @date：2018-07-17 16:42
     */
    @Override
    public UserInfo findByOpenid(String openid)
    {
        try
        {
            return  userInfoRespostory.findByOpenid(openid);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
}
