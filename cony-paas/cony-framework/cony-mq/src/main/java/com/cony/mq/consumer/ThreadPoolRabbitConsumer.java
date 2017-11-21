package com.cony.mq.consumer;

import com.cony.mq.common.MQResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangk-p on 2017/4/19.
 */
public class ThreadPoolRabbitConsumer<T> {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolRabbitConsumer.class);
    private ExecutorService executor;
    private volatile boolean stop = false;
    private final ThreadPoolConsumerBuilder<T> infoHolder;

    //构造器
    public static class ThreadPoolConsumerBuilder<T> {
        int threadCount;
        long intervalMils;
        RabbitConsumerBuilder rabbitConsumerBuilder;
        String exchange;
        String routingKey;
        String queue;
        String type = "direct";
        MessageProcessor<T> messageProcessor;

        public ThreadPoolConsumerBuilder<T> setThreadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setIntervalMils(long intervalMils) {
            this.intervalMils = intervalMils;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setConsumerBuilder(RabbitConsumerBuilder rabbitConsumerBuilder) {
            this.rabbitConsumerBuilder = rabbitConsumerBuilder;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setExchange(String exchange) {
            this.exchange = exchange;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setRoutingKey(String routingKey) {
            this.routingKey = routingKey;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setQueue(String queue) {
            this.queue = queue;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setType(String type) {
            this.type = type;
            return this;
        }

        public ThreadPoolConsumerBuilder<T> setMessageProcessor(MessageProcessor<T> messageProcessor) {
            this.messageProcessor = messageProcessor;
            return this;
        }

        public ThreadPoolRabbitConsumer<T> build() {
            return new ThreadPoolRabbitConsumer<T>(this);
        }
    }

    private ThreadPoolRabbitConsumer(ThreadPoolConsumerBuilder<T> threadPoolConsumerBuilder) {
        this.infoHolder = threadPoolConsumerBuilder;
        executor = Executors.newFixedThreadPool(threadPoolConsumerBuilder.threadCount);
    }

    //1 构造messageConsumer
    //2 执行consume
    public void start() throws IOException {
        for (int i = 0; i < infoHolder.threadCount; i++) {
            //1
            final MessageConsumer messageConsumer = infoHolder.rabbitConsumerBuilder.buildMessageConsumer(infoHolder.exchange,
                    infoHolder.routingKey, infoHolder.queue, infoHolder.messageProcessor);

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while (!stop) {
                        try {
                            //2
                            MQResult result = messageConsumer.consume();
                            if (infoHolder.intervalMils > 0) {
                                try {
                                    Thread.sleep(infoHolder.intervalMils);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    logger.info("interrupt " + e);
                                }
                            }

                            if (!result.isSuccess()) {
                                logger.info("run error " + result.getErrors());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.info("run exception " + e);
                        }
                    }
                }
            });
        }
    }
    public void stop() {
        this.stop = true;
    }
}
