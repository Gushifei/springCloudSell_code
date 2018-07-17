package com.imooc.order.message;

import com.imooc.order.utils.JsonUtil;
import com.imooc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 赵亮
 * @date 2018-07-16 14:42
 */
@Component
@Slf4j
public class ProductInfoReceiver
{
    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message)
    {
        //message转成ProductInfoOutput
        List<ProductInfoOutput> productInfoOutputs = JsonUtil.toList(message, ProductInfoOutput.class);
        log.info("从队列【{}】接收到消息：{}", "productInfo", productInfoOutputs);
        //储存到redis里面去
        for (ProductInfoOutput productInfoOutput : productInfoOutputs)
        {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId())
                    , String.valueOf(productInfoOutput.getProductStock()));
        }
    }
}
