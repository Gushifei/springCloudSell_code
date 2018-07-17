package com.imooc.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author 赵亮
 * @date 2018-07-16 10:00
 */
public interface StreamClient
{
    // BOSS生产者
    String MY_MQ_OUTPUT = "my_mq_output";
    // ECM消费者
    String MY_MQ_INPUT = "my_mq_input";

    @Input(StreamClient.MY_MQ_INPUT)
    SubscribableChannel input();

    @Output(StreamClient.MY_MQ_OUTPUT)
    MessageChannel output();

    // BOSS生产者
    String MY_MQ_OUTPUT_2 = "my_mq_output2";
    // ECM消费者
    String MY_MQ_INPUT_2 = "my_mq_input2";

    @Input(StreamClient.MY_MQ_INPUT_2)
    SubscribableChannel input2();

    @Output(StreamClient.MY_MQ_OUTPUT_2)
    MessageChannel output2();
}
