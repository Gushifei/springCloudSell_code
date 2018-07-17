package com.imooc.order.message;

import com.imooc.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author 赵亮
 * @date 2018-07-16 10:01
 * 消息接收端
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver
{
    //    @StreamListener(StreamClient.MY_MQ_INPUT)
    //    public void process(Message<String> message)
    //    {
    //        log.info("StreamReceiver：{}", message.getPayload());
    //    }

    /**
     * 接受对象OrderDTO消息
     *
     * @author：赵亮
     * @date：2018-07-16 13:55
     */
    @StreamListener(StreamClient.MY_MQ_INPUT)
    @SendTo(StreamClient.MY_MQ_INPUT_2)  //处理完成后会给这个消息队列发消息
    public String process(OrderDTO message)
    {
        log.info("StreamReceiver：{}", message);
        return "Receiver";
    }

    @StreamListener(StreamClient.MY_MQ_INPUT_2)
    public void process2(String message)
    {
        log.info("StreamReceiver2：{}", message);
    }
}
