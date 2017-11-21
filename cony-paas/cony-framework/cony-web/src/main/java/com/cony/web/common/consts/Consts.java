package com.cony.web.common.consts;

/**
 * 框架常量，整个框架的常量抽象
 *
 * @author wangkan
 */
public abstract class Consts {
    /**
     * 模型json文件的编码
     */
    public static final String DOMAIN_ENCODING = "utf-8";
    /**
     * 默认的字符编码，用于接收请求参数和响应客户端请求
     */
    public static final String DEFAULT_ENCODING = "utf-8";
    /**
     * 默认页面索引值，查询操作没有指定时使用该值
     */
    public static final int DEFAULT_OFFSET= 0;
    /**
     * 默认页面大小，查询操作没有指定时使用该值
     */
    public static final int DEFAULT_PAGE_SIZE = 15;

}

