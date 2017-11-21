package com.cony.mq.sender;

import com.cony.mq.common.Constants;
import com.cony.mq.common.MQResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangk-p on 2017/4/19.
 */
public class RetryCache {
    private static final Logger logger = LoggerFactory.getLogger(RetryCache.class);

    private MessageSender sender;
    private boolean stop = false;
    private Map<String, MessageHolder> map = new ConcurrentHashMap<String, MessageHolder>();



    private static class MessageHolder {
        long time;
        Object message;
        public MessageHolder(Object message,long time) {
            this.message = message;
            this.time = time;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public Object getMessage() {
            return message;
        }

        public void setMessage(Object message) {
            this.message = message;
        }
    }

    public void setSender(MessageSender sender) {
        this.sender = sender;
        startRetry();
    }

    public String generateId() {
        return "" + UUID.randomUUID();
    }

    public void add(String id, Object message) {
        map.put(id, new MessageHolder(message,System.currentTimeMillis()));
    }

    public void del(String id) {
        map.remove(id);
    }

    private void startRetry() {
        new Thread(() ->{
            while (!stop) {
                try {
                    Thread.sleep(Constants.RETRY_TIME_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long now = System.currentTimeMillis();

                for (String key : map.keySet()) {
                    MessageHolder messageHolder = map.get(key);

                    if (null != messageHolder) {
                        if (messageHolder.getTime() + 3 * Constants.VALID_TIME < now) {
                            logger.info("send message failed after 3 min " + messageHolder);
                            del(key);
                        } else if (messageHolder.getTime() + Constants.VALID_TIME < now) {
                            MQResult detailRes = sender.send(messageHolder.getMessage());
                            if (detailRes.isSuccess()) {
                                del(key);
                            }
                        }
                    }
                }
            }
        }).start();
    }
}
