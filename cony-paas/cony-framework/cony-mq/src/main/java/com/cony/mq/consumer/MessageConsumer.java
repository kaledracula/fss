package com.cony.mq.consumer;


import com.cony.mq.common.MQResult;

/**
 * Created by wangk-p on 2017/4/19.
 */
public interface MessageConsumer {
    MQResult consume();
}
