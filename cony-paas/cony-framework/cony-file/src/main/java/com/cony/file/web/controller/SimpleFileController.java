package com.cony.file.web.controller;


import com.cony.file.service.ISimpleFileService;
import com.cony.web.common.result.DefaultResult;
import com.cony.web.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangk-p on 2017/6/7.
 */
@RestController
@RequestMapping("/simpleFile")
public class SimpleFileController {

    @Autowired
    private ISimpleFileService simpleFileService;

    private ISimpleFileService getService() {
        return simpleFileService;
    }


    /**
     * 通过ID删除域对象
     *
     * @param id 域对象ID
     * @return Result
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Long id) {
        getService().delete(id);
        return new DefaultResult();
    }

    /**
     * 通过ID数组删除域对象
     *
     * @param ids 域对象ID集合
     * @return Result
     */
    @RequestMapping(value = "/deleteAll/{ids}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Long[] ids) {
        getService().delete(ids);
        return new DefaultResult();
    }
}
