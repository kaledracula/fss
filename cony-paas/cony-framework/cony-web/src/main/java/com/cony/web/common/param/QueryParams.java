package com.cony.web.common.param;

import com.cony.web.common.consts.Consts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/3.
 */
public class QueryParams {

    private int offSet = Consts.DEFAULT_OFFSET;

    private int pageSize =  Consts.DEFAULT_PAGE_SIZE;

    private Map<String,Object> params =new HashMap<String,Object>();

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
