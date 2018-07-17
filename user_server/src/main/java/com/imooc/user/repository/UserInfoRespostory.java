package com.imooc.user.repository;

import com.imooc.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 赵亮
 * @date 2018-07-17 16:40
 */
public interface UserInfoRespostory extends JpaRepository<UserInfo, String>
{
    UserInfo findByOpenid(String openid);
}
