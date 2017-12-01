package com.cony.security.cache;

import org.springframework.stereotype.Component;

/**
 * Created by wangk-p on 2017/11/29.
 */
@Component
public class PhoneCache {

    private DataCache<String,String> cache = new DataCache<String,String>(300);//缓存300秒

    public void putUsernameAndMsgCode(String username,String msgCode) {
        cache.put(username,msgCode);
    }

    public String getMsgCodeByUsername(String username) {
        return cache.get(username);
    }
}
