package com.imooc.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.imooc.apigateway.exception.RateLimitException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流拦截器
 * @author 赵亮
 * @date 2018-07-17 14:01
 */
public class RateLimiterFilter extends ZuulFilter
{
    /**
     *每秒钟往里面放100个令牌
     *@author：赵亮
     *@date：2018-07-17 14:04
    */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType()
    {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder()
    {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public Object run() throws ZuulException
    {
        //如果没有拿到令牌  别人写的更多限流的方法：https://github.com/marcosbarbero/spring-cloud-zuul-ratelimit
        if(!RATE_LIMITER.tryAcquire())
        {
            throw new RateLimitException();
        }
        return null;
    }
}
