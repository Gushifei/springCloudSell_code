package com.imooc.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 赵亮
 * @date 2018-07-13 16:15
 * 接收mq消息的
 */
@Slf4j
@Component
public class MqReceiver
{
    /**
     * 模拟服务一
     *
     * @author：赵亮
     * @date：2018-07-13 16:46
     */
    //@RabbitListener(queues = "myQueue")//1、需要手动去服务端控制台创建队列
    //@RabbitListener(queuesToDeclare = @Queue("myQueue"))  //2、自动创建队列
    //3、自动创建，Exchange和队列绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")))
    public void process(String message)
    {
        log.info("MqReceiver:{}", message);
    }

    /**
     * 数码供应商，接受消息
     *
     * @author：赵亮
     * @date：2018-07-13 16:46
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")))
    public void processComputer(String message)
    {
        log.info("computer MqReceiver:{}", message);
    }

    /**
     * 水果供应商，接受消息
     *
     * @author：赵亮
     * @date：2018-07-13 16:46
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder")))
    public void processFruit(String message)
    {
        log.info("computer MqReceiver:{}", message);
    }
}
