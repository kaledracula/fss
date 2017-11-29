package com.cony.projects.fss.security.cache;

import org.springframework.stereotype.Component;

/**
 * Created by wangk-p on 2017/11/29.
 */
@Component
public class PhoneCache {

    private DataCache<Long,String> cache = new DataCache<Long,String>(300);//缓存300秒

    public void putPhoneNumberAndVCode(Long phoneNumber,String validateCode) {
        cache.put(phoneNumber,validateCode);
    }

    public String getVCodeByPhoneNumber(Long phoneNumber) {
        return cache.get(phoneNumber);
    }
}
