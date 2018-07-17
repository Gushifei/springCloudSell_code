package com.imooc.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 赵亮
 * @date 2018-07-17 16:38
 */
@Data
@Entity
public class UserInfo
{
    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
    private Integer role;
}
