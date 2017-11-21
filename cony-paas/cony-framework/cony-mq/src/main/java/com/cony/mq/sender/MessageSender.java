package com.cony.mq.sender;


import com.cony.mq.common.MQResult;

/**
 * Created by wangk-p on 2017/4/19.
 */
public interface MessageSender {

    MQResult send(Object message);
}
