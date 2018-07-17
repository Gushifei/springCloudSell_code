package com.imooc.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限拦截（区分买家和卖家）
 * Created by 廖师兄
 * 2018-02-15 15:34
 */
@Component
public class AuthFilter extends ZuulFilter
{
    /**
     * 过滤类型
     *
     * @author：赵亮
     * @date：2018-07-17 13:51
     */
    @Override
    public String filterType()
    {
        return PRE_TYPE;
    }

    /**
     * 过滤级别，越小的优先级越高
     *
     * @author：赵亮
     * @date：2018-07-17 13:51
     */
    @Override
    public int filterOrder()
    {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public Object run()
    {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //这里从url参数里获取, 也可以从cookie, header里获取
        String token = request.getParameter("token");
        /**
         * /order/create 只能买家访问
         * /order/finish 只能卖家访问
         * /product/list 都可访问
        */

        return null;
    }
}
