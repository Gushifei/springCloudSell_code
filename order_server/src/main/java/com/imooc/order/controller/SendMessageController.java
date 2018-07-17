package com.imooc.order.controller;

import com.imooc.order.dto.OrderDTO;
import com.imooc.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 赵亮
 * @date 2018-07-16 10:04
 */
@RestController
@EnableBinding(StreamClient.class)
public class SendMessageController
{
    @Autowired
    @Output(StreamClient.MY_MQ_OUTPUT)
    private MessageChannel outPut;

//    @GetMapping("/sendMessage")
//    public void process()
//    {
//        String message = "now " + new Date().toString();
//        outPut.send(MessageBuilder.withPayload(message).build());
//    }

    /**
     *发送对象OrderDTO
     *@author：赵亮
     *@date：2018-07-16 13:55
     * http://localhost:8891/sendMessage
    */
    @GetMapping("/sendMessage")
    public void process()
    {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        orderDTO.setBuyerAddress("北京市朝阳区");
        orderDTO.setBuyerName("赵亮字阳明");
        outPut.send(MessageBuilder.withPayload(orderDTO).build());
    }
}
