package com.imooc.user.controller;

import com.imooc.base.utils.CookieUtil;
import com.imooc.base.utils.ResultVOUtil;
import com.imooc.base.vo.ResultVO;
import com.imooc.user.constant.CookieConstant;
import com.imooc.user.constant.RedisConstant;
import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.enums.ResultEnum;
import com.imooc.user.enums.RoleEnum;
import com.imooc.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 赵亮
 * @date 2018-07-17 16:47
 */
@RestController
@RequestMapping("/login")

public class LoginController
{
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登陆
     *
     * @author：赵亮
     * @date：2018-07-17 16:52
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response)
    {
        //1、openid和数据库里面的数据匹配
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        if (userInfo == null)
        {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL.getCode(), ResultEnum.LOGIN_FAIL.getMessage());
        }
        //2、判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole())
        {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR.getCode(), ResultEnum.ROLE_ERROR.getMessage());
        }
        //3、这只cookies
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);
        return ResultVOUtil.success();
    }

    /**
     * 买家登陆
     *
     * @author：赵亮
     * @date：2018-07-17 16:52
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletResponse response, HttpServletRequest request)
    {
        //判断是否已登陆
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().
                        get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue()))))
        {
            return ResultVOUtil.success();
        }
        //1、openid和数据库里面的数据匹配
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        if (userInfo == null)
        {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL.getCode(), ResultEnum.LOGIN_FAIL.getMessage());
        }
        //2、判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole())
        {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR.getCode(), ResultEnum.ROLE_ERROR.getMessage());
        }
        //3、往redis里面写key=UUID,value=xyz
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token)
                , openid, RedisConstant.expire, TimeUnit.SECONDS);
        //4、cookie里面这是openid=xyz
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);
        return ResultVOUtil.success();
    }
}
