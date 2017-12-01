package com.cony.log.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.cony.log.constant.LogConstants;

/**
 * Created by wangk-p on 2017/11/30.
 */
public class LogstashFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getMessage().contains(LogConstants.BusinessLog)
                || event.getMessage().contains(LogConstants.ErrorLog)
                || event.getMessage().contains(LogConstants.DeployLog)) {
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }
}