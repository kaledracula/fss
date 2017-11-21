package com.cony.mq.common;

/**
 * 代表某个方法的处理结果
 *
 * @author wangkan
 *
 *         序列化的方式： { "success":true/false,
 *         "errors":["error1","error2","error3"],
 *         "messages":["msg1","msg2","msg3"] }
 */
public interface MQResult {
           /**
         * 增加一个error信息
         *
         * @param error
         *            error信息
         * @return 当前对象
         */
        MQResult addError(String error);

        /**
         * 增加一个error信息
         *
         * @param code
         *            错误码
         * @param args
         *            格式参数
         * @param defaultError
         *            默认的错误信息
         * @return 当前对象
         */
        MQResult addErrorCode(String code, Object[] args, String defaultError);

        /**
         * 增加提示信息消息
         *
         * @param message
         *            消息，例如保存成功。
         * @return 当前对象
         */
        MQResult addMessage(String message);

        /**
         * 增加一个消息信息
         *
         * @param code
         *            消息码，对应到i18n参数文件中的key值
         * @param args
         *            格式参数
         * @param defaultMessage
         *            默认的消息信息，如果i18n文件中不存在时使用
         * @return 当前对象
         */
        MQResult addMessageCode(String code, Object[] args, String defaultMessage);

        /**
         * 清除所有的错误消息
         *
         * @return
         */
        MQResult clearError();

        /**
         * 清除所有的信息消息
         *
         * @return
         */
        MQResult clearMessage();

        /**
         * 清除全部
         *
         * @return
         */
        MQResult clearAll();

        /**
         * 是否成功，检查错误信息是否为空
         *
         * @return
         */
        boolean isSuccess();

        /**
         * 获取错误信息
         *
         * @return
         */
        String getErrors();

        /**
         * 获取信息
         *
         * @return
         */
        String getMessages();

}

